package J3HomeWork2;

import java.io.*;
import java.util.Date;

public class ChatLog {

    private static String path = "D:/chatlog.txt";

    public static void writeToLog(String msg){

        String s = (new Date().toString())
                + "\r\n" + msg+ "\r\n" + "" + "\r\n";
        System.out.println(s);
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(path, true));
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        } catch (IOException e){
            e.printStackTrace();
        }
        try {
            bw.write(s);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getStory(PrintWriter printWriter, int storySize) {

        BufferedReader br = null;
        String s;
        int stringCount = getStringCount();

        try {
            br = new BufferedReader(new FileReader(path));
            int i = 0;
            while ((s=br.readLine()) != null){

                if (i >= stringCount-storySize - 1) printWriter.println(s);
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getStringCount(){
        BufferedReader br = null;
        int stringCount = 0;
        try {
            br = new BufferedReader(new FileReader(path));
            while ((br.readLine()) != null){
                stringCount++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringCount;
    }

}
