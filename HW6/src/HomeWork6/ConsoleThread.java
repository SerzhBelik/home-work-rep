package HomeWork6;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class ConsoleThread extends Thread {
    String incomingText;
    String myText;
    Scanner cs ;
    Scanner is;
    PrintWriter pw;
    Socket s= null;

    public ConsoleThread(Socket s){
        this.s =s;
    }

    public void run(){
        try{
            cs = new Scanner(System.in);
            is = new Scanner(s.getInputStream());
            pw = new PrintWriter(s.getOutputStream());
            System.out.println("asdfsdf");

//            while (true) {
//                if (cs.hasNextLine()) {
//                    myText = cs.nextLine();
//                    if (myText.equals("end")) break;
//                    pw.println((new Date().toString()) + "\n" + "Server: " + myText + "\n");
//                    System.out.println(myText);
//                }

//            }
        }catch (IOException e){
            System.out.println("Error in console thread!");
        }
    }

}
