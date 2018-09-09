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
            while (true) {
                String text = scanner.nextLine();
                if (text.equals("end"))break;
                printWriter.println((new Date().toString()) + "\n" + text);
            }

        }catch (IOException e){
            System.out.println("Server isn't started");
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e){
                e.printStackTrace();
            }

        }





    }
}
