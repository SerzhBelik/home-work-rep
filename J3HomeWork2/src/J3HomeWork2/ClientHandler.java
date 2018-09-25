package J3HomeWork2;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler {

    private Socket socket;
    private MyServer server;
    private PrintWriter printWriter;
    private Scanner scanner;
    private String userName;
    DBConnector dbc = new DBConnector();

    public ClientHandler(Socket socket, MyServer server){
        this.server = server;
        this.socket = socket;

        try {
            scanner = new Scanner(socket.getInputStream());
            printWriter = new PrintWriter(socket.getOutputStream(), true);

            new Thread(()-> {
                auth();
                while (true){
                    String str = scanner.nextLine();
                    if(str != null && !str.isEmpty() && !str.startsWith("/")) server.sendBroadcastMessage(userName + ": " + str);
                    if (str.startsWith("/w")) server.sendPriveteMessage(str, this.userName);
                    if (str.startsWith("/rename")){
                        String newName = dbc.renameUser(this.userName, str.split(" ")[1]);
                        if (newName == null) {
                            printWriter.println("This name is already taken!");
                            continue;
                        }
                        server.sendBroadcastMessage(userName + " renamed to " + newName);
                        userName = newName;

                        //FIXME
                    }
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
//                    String nick = server.getAuthService().authByloginAndPassword(login, password);
                    String nick = dbc.getName(login, password);
//                    System.out.println(nick);

                    if (nick == null){
                        printWriter.println("Invalid login or password!");
                    } else if (server.isNikTacken(nick)){
                        printWriter.println("Nick already taken!");
                    } else {
                        this.userName = nick;
                        server.sendBroadcastMessage(userName + " connect to chat!");
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

