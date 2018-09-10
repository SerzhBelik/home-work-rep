package HomeWork6;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class ClientController implements Controller{
    private final static String SERVER_ADR = "localhost";
    private final static int SERVER_PORT = 8189;
    private ClientUI ui;

    private Socket sock;
    private Scanner in;
    private PrintWriter out;

    public ClientController(){
        initConnection();

    }

    public void showUI(ClientUI ui) {
        this.ui = ui;
        ui.showUI();
    }

    private void initConnection() {
        try {
            sock = new Socket(SERVER_ADR, SERVER_PORT);
            in = new Scanner(sock.getInputStream());
            out = new PrintWriter(sock.getOutputStream(), true);
        } catch (IOException e){
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        if (in.hasNext()) {
                            String w = in.nextLine();
                            if (w.equals("end session")) break;
                            ui.addMessage(w);
//                            System.out.println(w);
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                consoleSend();
//            }
//        }).start();
    }


    @Override
    public void sendMessage(String msg){

        out.println(msg);
    }

    @Override
    public void closeConnection() {
        try {
            out.println("end");
            sock.close();
            out.close();
            in.close();
        } catch (IOException exc) {
        }
    }

//    public static void consoleSend(){
//        Socket consoleSock;
//        try {
//            consoleSock = new Socket(SERVER_ADR, 8189);
//            Scanner ClnConsoleScanner = new Scanner(System.in);
//            Scanner ServConsoleScanner = new Scanner(consoleSock.getInputStream());
//            PrintWriter consolePrintWriter = new PrintWriter(consoleSock.getOutputStream(), true);
//
//            while (true) {
//                String consoleText = ClnConsoleScanner.nextLine();
//                consolePrintWriter.println(consoleText);
//
//                String incomingText = ServConsoleScanner.nextLine();
//                System.out.println(incomingText);
//
//            }
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//    }

}
