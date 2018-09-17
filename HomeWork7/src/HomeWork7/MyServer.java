package HomeWork7;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;


public class MyServer {

    private ServerSocket serverSocket;
   Map<String, ClientHandler> clients = new HashMap<>();
    private AuthService authService;

    public MyServer(AuthService authService) {
        this.authService = authService;
        try {
            serverSocket = new ServerSocket(8189);
            System.out.println("Server is started");
        } catch (IOException e) {
            System.out.println("Server isn't started");
            this.close();
        }
    }

    private void close() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }


    public static void main(String[] args) {

        AuthService baseAuthService = new BaseAuthService();
        new MyServer(baseAuthService).start();

    }

    public void start() {
        while (true) {

            try {
                Socket accept = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(accept, this);
                clients.put(clientHandler.getNick(), clientHandler);
                System.out.println("client connect");

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    public void sendBroadcastMessage(String str) {
        for (ClientHandler client : clients.values()) {
            client.sendMessage((new Date().toString())
                    + "\n" + str + "\n");
        }
    }

    public AuthService getAuthService() {
        return this.authService;
    }

    public boolean isNikTacken(String nick) {
        for (ClientHandler client : clients.values()) {
            if (nick.equals(client.getNick())) return true;
        }
        return false;
    }

    public void sendPrivetMessage(String str, String senderName) {

        String[] commands = str.split(" ", 3);
        String nick = commands[1];

        if (isUserFound(nick)) {
            for (ClientHandler client : clients.values()) {

                if (nick.equals(senderName)) {
                    findAndSend(senderName, "Why do you write to yourself?" + "\n");
                    return;
                }

                if (nick.equals(client.getNick())) {
                    findAndSend(client.getNick(), (new Date().toString()) + "\n"
                            + "PM from " + senderName + ": " + commands[2] + "\n");
                }

                if (senderName.equals(client.getNick())) {
                    findAndSend(senderName, (new Date().toString()) + "\n"
                            + "PM to " + nick + ": " + commands[2] + "\n");
                }

            }

        } else {
            findAndSend(senderName, "User not found!" + "\n");
        }
    }

    public boolean isUserFound(String nick) {
        return clients.containsKey(nick);
    }

    public void findAndSend(String addressee, String msg) {
        for (ClientHandler client : clients.values()) {
            if (addressee.equals(client.getNick())) {
                client.sendMessage(msg);
            }
        }
    }

    public void subscribe(ClientHandler clientHandler){
        String msg = clientHandler.getNick()  + " connect to chat!";
        System.out.println(msg);
        sendBroadcastMessage(msg);
        clients.put(clientHandler.getNick(), clientHandler);
    }
    public void unsubscribe(ClientHandler clientHandler){
        String msg = clientHandler.getNick()  + " disconnected from chat!";
        System.out.println(msg);
        sendBroadcastMessage(msg);
        clients.remove(clientHandler.getNick());
    }
}


