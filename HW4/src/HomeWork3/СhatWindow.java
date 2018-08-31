package HomeWork3;

import javax.swing.*;
import java.awt.*;

public class СhatWindow extends JFrame {
    final static int WIDTH = 400;
    final static int HEIGHT = 600;
    public СhatWindow(){
        setTitle("Home work chat");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(Toolkit.getDefaultToolkit ().getScreenSize ().width/2-WIDTH/2,
     Toolkit.getDefaultToolkit ().getScreenSize ().height/2-HEIGHT/2, WIDTH, HEIGHT);
        setLayout(new BorderLayout());
        JTextArea textArea = new JTextArea();
        add(textArea, BorderLayout.CENTER);
        setVisible(true);

    }


}
