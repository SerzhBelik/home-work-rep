package server;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


public class MyServer implements Runnable {

    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private ByteBuffer buf = ByteBuffer.allocate(256);
    private int acceptedClientIndex = 1;
    private ServerSocket serverSocket;
    private final ByteBuffer welcomBuf = ByteBuffer.wrap("Welcom to chat!\n".getBytes());

//    private ServerSocket serverSocket;
    private List<ClientHandler> clients = new ArrayList<>();
    private AuthService authService;

    public MyServer() throws IOException {
        this.authService = new BaseAuthService();
        this.serverSocketChannel = ServerSocketChannel.open();
        this.serverSocketChannel.socket().bind(new InetSocketAddress(8189));
        this.selector = Selector.open();
        this.serverSocketChannel.configureBlocking(false);
        this.serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        this.serverSocket = serverSocketChannel.socket();
//        try {
//            serverSocket = new ServerSocket(8189);
//            System.out.println("Server is started");
//        } catch (IOException e) {
//            System.out.println("Server isn't started");
//            this.close();
//        }
    }

    @Override
    public void run(){
        try{
            System.out.println("Server started!");
            Iterator<SelectionKey> iterator;
            SelectionKey key;
            while (this.serverSocketChannel.isOpen()){
                selector.select();
                iterator = this.selector.selectedKeys().iterator();
                while (iterator.hasNext()){
                    key = iterator.next();
                    iterator.remove();
                    if (key.isAcceptable()) this.handleAccept(key);
                    if (key.isReadable()) this.handleRead(key);
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    private void handleRead(SelectionKey key) throws IOException {
        SocketChannel sc = (SocketChannel)key.channel();
        StringBuilder sb = new StringBuilder();

        buf.clear();
        int read = 0;
        while ((read = sc.read(buf)) > 0){
            buf.flip();
            byte[] bytes = new byte[buf.limit()];
            buf.get(bytes);
            sb.append(new String(bytes));
            buf.clear();
        }
        String msg;



        msg = sb.toString();
        System.out.println(msg);

        sendBroadcastMessage(msg);
    }

    private void handleAccept(SelectionKey key) throws IOException {
        SocketChannel sc = ((ServerSocketChannel)key.channel()).accept();
        sc.configureBlocking(false);
//        ClientHandler clientHandler = new ClientHandler(serverSocket, this);
//        clients.add(clientHandler);
        String clientName = "Client #" + acceptedClientIndex++;
        sc.register(selector, SelectionKey.OP_READ, clientName);
        System.out.println(clientName + " accept!");
        sc.write(welcomBuf);
        welcomBuf.rewind();
    }


    private void close() {
//        try {
//            serverSocket.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        System.exit(0);
    }


    public static void main(String[] args) throws IOException {

//        AuthService baseAuthService = new BaseAuthService();
        new Thread(new MyServer()).start();
//        new MyServer(baseAuthService).start();

    }

    public void start() {
        while (true) {

//            try {
//                Socket accept = serverSocket.accept();
//                ClientHandler clientHandler = new ClientHandler(accept, this);
//                clients.add(clientHandler);
//                System.out.println("client connect");
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }


        }
    }

    public void sendBroadcastMessage(String msg) throws IOException {
//        System.out.println(msg + " sbm");
        ByteBuffer byteBuffer =  ByteBuffer.wrap(msg.getBytes());
        for (SelectionKey key : selector.keys()){
            if (key.isValid() && key.channel() instanceof SocketChannel){
                SocketChannel sc = (SocketChannel)key.channel();
                System.out.println(byteBuffer.getChar(0));
                sc.write(byteBuffer);
                byteBuffer.rewind();
            }
        }
//        for (ClientHandler client : clients) {
//            client.sendMessage((new Date().toString())
//                    + "\n" + str + "\n");
//        }
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

    private void findAndSend(String addressee, String msg) {
        for (ClientHandler client : clients) {
            if (addressee.equals(client.getNick())) {
                client.sendMessage(msg);
            }
        }
    }
}

