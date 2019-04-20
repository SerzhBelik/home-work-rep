package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler {

    private Socket socket;
    private ServerSocket serverSocket;
    private MyServer server;
    private PrintWriter printWriter;
    private Scanner scanner;
    private String userName;

    public ClientHandler(ServerSocket serverSocket, MyServer server){
        this.server = server;
        this.serverSocket = serverSocket;


        try {
            DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
            DataInputStream ois = new DataInputStream(socket.getInputStream());
//            scanner = new Scanner(socket.getInputStream());
//            printWriter = new PrintWriter(socket.getOutputStream(), true);

            new Thread(()-> {
                auth();
                while (true){
                    String str = scanner.nextLine();
//                    if(str != null && !str.isEmpty() && !str.startsWith("/w")) server.sendBroadcastMessage(userName + ": " + str);
                    if (str.startsWith("/w")) server.sendPrivetMessage(str, this.userName);
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void auth() {
        while (true){
            String str = scanner.nextLine();

            if(str.startsWith("/auth")){
                String[] commands = str.split(" ");

                if (commands.length >= 3){
                    String login = commands[1];
                    String password = commands[2];
                    String nick = server.getAuthService().authByloginAndPassword(login, password);

                    if (nick == null){
                        printWriter.println("Invalid login or password!");
                    } else if (server.isNikTacken(nick)){
                        printWriter.println("Nick already taken!");
                    } else {
                        this.userName = nick;
//                        server.sendBroadcastMessage(userName + " connect to chat!");
                        break;
                    }
                }
            } else {
                printWriter.println("Invalid command!");
            }
        }
    }

    public void sendMessage(String str) {
        printWriter.println(str);
    }

    public String getNick() {
        return this.userName;
    }
}
