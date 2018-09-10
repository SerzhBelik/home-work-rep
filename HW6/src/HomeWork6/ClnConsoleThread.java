package HomeWork6;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class ClnConsoleThread extends Thread {
    Socket s= null;
    Scanner cs;
    Scanner is;
    PrintWriter pw;
    String myText;
    public ClnConsoleThread (Socket s){
        this.s =s;
    }

    public void run(){
        try{
            cs = new Scanner(System.in);
            is = new Scanner(s.getInputStream());
            pw = new PrintWriter(s.getOutputStream());


            while (true) {
                if (cs.hasNextLine()) {
                    myText = cs.nextLine();
                    if (myText.equals("end")) break;
                    pw.println("@Client console: " + (new Date().toString()) + " " + myText);
                    pw.flush();
                }

            }
        }catch (IOException e){
            System.out.println("Error in console thread!");
        }
    }

}

