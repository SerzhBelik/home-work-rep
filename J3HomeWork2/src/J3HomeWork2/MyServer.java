package J3HomeWork2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class MyServer {

    private ServerSocket serverSocket;
    private List<ClientHandler> clients = new ArrayList<>();
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
                clients.add(clientHandler);
                System.out.println("client connect");

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    public void sendBroadcastMessage(String str) {
        for (ClientHandler client : clients) {
            client.sendMessage((new Date().toString())
                    + "\n" + str + "\n");
        }

    }

    public AuthService getAuthService() {
        return this.authService;
    }

    public boolean isNikTacken(String nick) {
        for (ClientHandler client : clients) {
            if (nick.equals(client.getNick())) return true;
        }
        return false;
    }

    public void sendPriveteMessage(String str, String senderName) {

        String[] commands = str.split(" ", 3);
        String nick = commands[1];

        if (isUserFound(nick)) {
            for (ClientHandler client : clients) {

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

    private boolean isUserFound(String nick) {
        for (ClientHandler client : clients) {
            if (nick.equals(client.getNick())) return true;
        }
        return false;
    }

    public void findAndSend(String addressee, String msg) {
        for (ClientHandler client : clients) {
            if (addressee.equals(client.getNick())) {
                client.sendMessage(msg);
            }
        }
    }
}
