package client;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class ClientController implements Controller{
    private final static String SERVER_ADR = "localhost";
    private final static int SERVER_PORT = 8189;
    private ClientUI ui;
//    private SocketChannel channel;

    private Socket socket;
    private DataOutputStream oos;
    private DataInputStream ois;
    private SocketChannel channel;
    private Selector selector;
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

            channel = SocketChannel.open();
            channel.configureBlocking(false);
            channel.connect(new InetSocketAddress(SERVER_ADR, SERVER_PORT));

            selector = Selector.open();
            channel.register(selector, SelectionKey.OP_CONNECT);
//            SocketChannel socket = this.initiateConnection();
//            selector = Selector.open();
//            channel = SocketChannel.open();
//            channel.configureBlocking(false);
//            channel.register(selector, SelectionKey.OP_CONNECT);
//            channel.connect(new InetSocketAddress(SERVER_ADR, SERVER_PORT));
//            socket = new Socket(SERVER_ADR, SERVER_PORT);
//            oos = new DataOutputStream(socket.getOutputStream());
//            ois = new DataInputStream(socket.getInputStream());
//            in = new Scanner(sock.getInputStream());
//            out = new PrintWriter(sock.getOutputStream(), true);
        } catch (IOException e){
            e.printStackTrace();
        }


        new Thread(new Runnable() {
            @Override
            public void run() {
//                try{
                    System.out.println("!!!");
                    Iterator<SelectionKey> iterator;
                    SelectionKey key;
                    try {


                        while (true) {
                            if (selector.isOpen()) {
                                int keys = selector.select();
                                if (keys > 0) {
                                    Set<SelectionKey> selectedKeys = selector.selectedKeys();
                                    for (SelectionKey sk : selectedKeys) {
                                        if (!sk.isValid()) {
                                            break;
                                        }
                                        if (sk.isConnectable()) {
                                            System.out.println("accepting");
                                            channel.finishConnect();
                                            channel.register(selector, SelectionKey.OP_WRITE);
                                            sk.interestOps(SelectionKey.OP_WRITE);
                                        }

                                            if (sk.isWritable()) {
                                                SocketChannel sc = (SocketChannel) sk.channel();
                                                System.out.println("writing");
                                                ByteBuffer buffer = ByteBuffer.allocate(256);
                                                StringBuilder sb = new StringBuilder();

                                                buffer.clear();
                                                int read = 0;
                                                while ((read = sc.read(buffer)) > 0){
                                                    buffer.flip();
                                                    byte[] bytes = new byte[buffer.limit()];
                                                    buffer.get(bytes);
                                                    sb.append(new String(bytes));
                                                    buffer.clear();
                                                }
                                                String msg = sb.toString();;
                                                System.out.println(msg);


                                            }
                                        }
                                    }
                                }
                            }

                    }catch (IOException ioe){
                        ioe.printStackTrace();
                    }

            }
        }).start();

    }


    @Override
    public void sendMessage(String msg){
        ByteBuffer byteBuffer =  ByteBuffer.wrap(msg.getBytes());
        for (SelectionKey key : selector.keys()){
            if (key.isValid() && key.channel() instanceof SocketChannel){
                SocketChannel sc = (SocketChannel)key.channel();
                try {
                    sc.write(byteBuffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                byteBuffer.rewind();
            }
        }


//        try {
//            oos.write(msg.getBytes());
//        }catch (IOException e){
//            e.printStackTrace();
//        }


    }

    @Override
    public void closeConnection() {
        try {
            out.println("end");
            socket.close();
            out.close();
            in.close();
        } catch (IOException exc) {
        }
    }



}
