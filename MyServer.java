package HomeWork5;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;


public class MyServer {

    private static Logger log = Logger.getLogger(MyServer.class.getName());
    private ServerSocket serverSocket;
    private List<ClientHandler> clients = new ArrayList<>();
    private ExecutorService executorService;

    public MyServer() {
        executorService = Executors.newCachedThreadPool();
        try {
            serverSocket = new ServerSocket(8189);
//            System.out.println("Server is started");
            log.info("Server is started");
            ArrChanger arrChanger = new ArrChanger();
            int[] arr = {1, 2, 3, 4, 5, 6, 7};
//            arrChanger.cutOff(arr, 4);
            boolean b = arrChanger.findValue(arr, 3, 4);
            System.out.println(b);
        } catch (IOException e) {
//            System.out.println("Server isn't started");
            log.warning("Server isn't started");
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
        MyServer myServer = new MyServer();
        myServer.start();
        myServer.getExecutorService().shutdown();
    }

    public void start() {
        while (true) {

            try {
                Socket accept = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(accept, this);
                clients.add(clientHandler);
//                System.out.println("client connect");
                log.info("client connect");

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    public void sendBroadcastMessage(String str) {
        for (ClientHandler client : clients) {
            client.sendMessage((new Date().toString())
                    + System.lineSeparator() + str + System.lineSeparator());
        }
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
                    findAndSend(senderName, "Why do you write to yourself?" + System.lineSeparator());
                    return;
                }

                if (nick.equals(client.getNick())) {
                    findAndSend(client.getNick(), (new Date().toString()) + System.lineSeparator()
                            + "PM from " + senderName + ": " + commands[2] + System.lineSeparator());
                }

                if (senderName.equals(client.getNick())) {
                    findAndSend(senderName, (new Date().toString()) + System.lineSeparator()
                            + "PM to " + nick + ": " + commands[2] + System.lineSeparator());
                }
                log.info(senderName + " отправил личное сообщение для " + nick);
            }

        } else {
            findAndSend(senderName, "User not found!" + System.lineSeparator());
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

    public ExecutorService getExecutorService (){
        return this.executorService;
    }


    public  void writeLog(String msg){
        log.info(msg);
    }
}
