package J3HomeWork2;

import java.io.*;
import java.util.Date;

public class ChatLog {
//private static FileOutputStream fis = null;
//private static    FileWriter fw = null;

    public static void writeToLog(String msg){

        String s = (new Date().toString())
                + "\r\n" + msg+ "\r\n" + "" + "\r\n";
        System.out.println(s);
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
//            FileOutputStream fis = new FileOutputStream("chatlog.txt");
            fw = new FileWriter("D:/chatlog.txt", true);
            bw = new BufferedWriter(fw);

        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        } catch (IOException e){
            e.printStackTrace();
        }
        try {
            bw.write(s);
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
