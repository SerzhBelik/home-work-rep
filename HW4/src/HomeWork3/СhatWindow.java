package HomeWork3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class СhatWindow extends JFrame {
    final static int WIDTH = 400;
    final static int HEIGHT = 600;
    public СhatWindow(){
        setTitle("Home work chat");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(Toolkit.getDefaultToolkit ().getScreenSize ().width/2-WIDTH/2,
     Toolkit.getDefaultToolkit ().getScreenSize ().height/2-HEIGHT/2, WIDTH, HEIGHT);
        setResizable(false);


        setLayout(new BorderLayout());
        JTextArea textArea = new JTextArea();
        JPanel panel = new JPanel();
        JTextArea inputText = new JTextArea(2, 30);
        JButton enter = new JButton("Enter");
        JScrollPane jsp = new JScrollPane(inputText);
        ActionListener listner = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setText(inputText.getText());
            }
        };

        textArea.setEditable(false);
        textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        inputText.setLineWrap(true);
        inputText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
            }
        });
        jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        enter.addActionListener(listner);


        add(textArea, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        panel.setLayout(new BorderLayout());
        panel.add(jsp, BorderLayout.CENTER);
        panel.add(enter, BorderLayout.EAST);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setVisible(true);

    }

    private void setText(String text) {

       System.out.println(text);
    }


}
