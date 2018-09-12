package HomeWork7;

import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class MyServer {
//    private Scanner cs = new Scanner(System.in);
    private ServerSocket serverSocket;
    private List<ClientHandler> clients = new ArrayList<>();
    private  AuthService authService;

    public MyServer(AuthService authService){
        this.authService = authService;
        try {
            serverSocket = new ServerSocket(8189);

            System.out.println("Server is started");
//            socket = serverSocket.accept();
//            System.out.println("client connect");

//            new Thread(()-> scanConsole()).start();

//            ConsoleThread ct = new ConsoleThread(socket); // поток для консоли
//            ct.start();

//            while (true) {
//                if (scanner.hasNextLine()) {
//                    String text = scanner.nextLine();
//                    if (text.equals("end")) break;

//                    if (text.length() > 6 && text.substring(0, 7).equals("@Client")) {
////
//                        System.out.println(text);
//                        continue;
//                    }
//                    printWriter.println((new Date().toString()) + "\n" + "Name: " + text + "\n");
//
//                }
//            }



        }catch (IOException e){
            System.out.println("Server isn't started");
        }
    }


    public static void main(String[] args) {

//        ServerSocket serverSocket = null;
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

    public void sendPrivetMessage(String str, String senderName) {

        String[] commands = str.split(" ", 3);
        String nick = commands[1];
        System.out.println(commands[2]);
        for (ClientHandler client : clients) {
            if (nick.equals(client.getNick())) {
                client.sendMessage((new Date().toString()) + "\n"
                        + "PM from " + senderName + ": " + commands[2] + "\n");
            }
            if (senderName.equals(client.getNick())) {
                client.sendMessage((new Date().toString())+ "\n"
                        + "PM to " + nick + ": " + commands[2] + "\n");
            }

        }
    }

//        private void scanConsole() {
//            while (true) {
//                String msg = cs.nextLine();
//                if (msg != null && !msg.isEmpty()) {
//                    printWriter.println("User: " + msg);
//                    if (msg.equals("end")) break;
//                }
//            }
//        }
    }


