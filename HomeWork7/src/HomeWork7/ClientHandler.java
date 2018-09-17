package HomeWork7;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

public class ClientHandler {

    private Socket socket;
    private MyServer server;
    private String userName;
    private Channel channel;

    public ClientHandler(Socket socket, MyServer server){
        this.server = server;
        this.socket = socket;

        try {
            channel = ChannelBase.of(socket);


            new Thread(()-> {
                auth();
                while (socket.isConnected()){
                    Message msg = channel.getMessage();
                    if(msg == null)continue;
                    switch (msg.getType()){

                        case AUTH_MESSAGE:
                            sendMessage(msg.getBody());
                            break;
                        case EXIT_COMMAND:
                            server.unsubscribe(this);
                            break;
                        case PRIVATE_MESSAGE:
                            sendPrivetMessage(msg.getBody());
                            break;
                        case BROADCAST_CHAT:
                            server.sendBroadcastMessage(userName + ": " + msg.getBody());
                            break;
                        default:
                            System.out.println("Invalid message type");
                    }

                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void sendPrivetMessage(String body) {
        {

            String[] commands = body.split(" ", 3);
            String nick = commands[1];

            if (server.isUserFound(nick)) {
                for (ClientHandler client : server.clients.values()) {

                    if (nick.equals(this.userName)) {
                        server.findAndSend(this.userName, "Why do you write to yourself?" + "\n");
                        return;
                    }

                    if (nick.equals(client.getNick())) {
                        server.findAndSend(client.getNick(), (new Date().toString()) + "\n"
                                + "PM from " + this.userName + ": " + commands[2] + "\n");
                    }

                    if (this.userName.equals(client.getNick())) {
                        server.findAndSend(this.userName, (new Date().toString()) + "\n"
                                + "PM to " + nick + ": " + commands[2] + "\n");
                    }

                }

            } else {
                server.findAndSend(this.userName, "User not found!" + "\n");
            }
        }
    }

    private void auth() {
        long time = System.nanoTime();
        while (true){

            if ((System.nanoTime()-time)/1000000000 >= 120){
                System.out.println("timeout");
                channel.sendMessage("you are disabled by timeout");

                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }

            if (!channel.hasNextLine()) continue;
            Message message = channel.getMessage();
            if(message.getType().equals(MessageType.AUTH_MESSAGE)){
                String[] commands = message.getBody().split(" ");

                if (commands.length >= 3){
                    String login = commands[1];
                    String password = commands[2];
                    String nick = server.getAuthService().authByloginAndPassword(login, password);

                    if (nick == null){
                        channel.sendMessage("Invalid login or password!");
                    } else if (server.isNikTacken(nick)){
                        channel.sendMessage("Nick already taken!");
                    } else {
                        this.userName = nick;
                        server.sendBroadcastMessage(userName + " connect to chat!");
                        server.subscribe(this);
                        break;
                    }
                }
            } else {
                channel.sendMessage("Invalid command!");
            }

        }

    }

    public void sendMessage(String str) {
        channel.sendMessage(str);
    }

    public String getNick() {
        return this.userName;
    }
}
