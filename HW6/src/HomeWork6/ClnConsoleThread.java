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
            System.out.println("asdfsdf");//FIXME

            while (true) {
                if (cs.hasNextLine()) {
                    myText = cs.nextLine();
                    if (myText.equals("end")) break;
                    pw.println("@Client console: " + (new Date().toString()) + " " + myText);
                    pw.flush();
//                    System.out.println(myText);
                }
//
//                if(is.hasNextLine()){
//                    if(is.nextLine().length()> 7 && is.nextLine().substring(0, 7).equals("@Server")){
//                        System.out.println(is.nextLine());
//                    }
//                }

            }
        }catch (IOException e){
            System.out.println("Error in console thread!");
        }
    }

}

