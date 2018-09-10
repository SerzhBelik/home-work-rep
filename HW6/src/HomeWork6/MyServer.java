package HomeWork6;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class MyServer {


    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket;

        try {
            serverSocket = new ServerSocket(8189);
            System.out.println("Server is started");
            socket = serverSocket.accept();
            System.out.println("client connect");
            Scanner scanner = new Scanner(socket.getInputStream());
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);


//            ConsoleThread ct = new ConsoleThread(socket);
//            ct.start();

            while (true) {
//                if (scanner.nextLine()==null) continue;
                String text = scanner.nextLine();
                if (text.equals("end"))break;
                printWriter.println((new Date().toString()) + "\n" + "Name: " + text+ "\n");
//                ConsoleThread ct = new ConsoleThread(socket);
//                ct.start();
            }
//            ConsoleThread ct = new ConsoleThread(socket);
//            ct.start();

//            new Thread(new Runnable() {  // поток для обмена сообщениями через консоль
//                @Override
//                public void run() {
//                    consoleSend(serverSocket);
//                }
//            }).start();
//
        }catch (IOException e){
            System.out.println("Server isn't started");
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//
//        }

//    }

//    public static void consoleSend(ServerSocket serverSocket){
//
//        try{
//
//            System.out.println("Server is started");
//            Socket consoleSocket = serverSocket.accept();
//            System.out.println("client connect");
//            Scanner ServConsoleScanner = new Scanner(System.in);
//            Scanner ClnConsoleScanner = new Scanner(consoleSocket.getInputStream());
//            PrintWriter consolePrintWriter = new PrintWriter(consoleSocket.getOutputStream(), true);
//
//
//
//            while (true) {
//                String consoleText = ServConsoleScanner.nextLine();
//                consolePrintWriter.println(consoleText);
//
//                String incomingText = ClnConsoleScanner.nextLine();
//                System.out.println(incomingText);
//
//            }
//
//        } catch (IOException e){
//            System.out.println("Server isn't started");
//        } finally {
//            try {
//                consoleServerSocket.close();
//            } catch (IOException e){
//                e.printStackTrace();
//            }
//
//        }
//
//
    }
}
