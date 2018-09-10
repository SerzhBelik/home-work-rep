package HomeWork6;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class ConsoleThread extends Thread {

    String myText;
    String call;
    Scanner cs ;
    Scanner is;
    PrintWriter pw;
    Socket s= null;

    public ConsoleThread(Socket s, String call){
        this.s =s;
        this.call = call;
    }

    public void run(){
        try{
            cs = new Scanner(System.in);
            is = new Scanner(s.getInputStream());
            pw = new PrintWriter(s.getOutputStream(), true);


            while (true) {
                if (cs.hasNextLine()) {
                    myText = cs.nextLine();
                    if (myText.equals("end")) break;
                    pw.println(call + ": " + (new Date().toString())+ " " + myText);

                    if(is.hasNextLine()&&is.nextLine().length()> 7){
                        if(is.nextLine().substring(0, 7).equals(call)){
                            System.out.println(is.nextLine());
                        }
                    }

                }

            }
        }catch (IOException e){
            System.out.println("Error in console thread!");
        }
    }

}
