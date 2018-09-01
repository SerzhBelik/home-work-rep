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

    JTextArea textArea = new JTextArea();
    JPanel panel = new JPanel();
    JTextArea inputText = new JTextArea(1, 30);
    JButton enter = new JButton("Enter");
    JScrollPane jsp = new JScrollPane(inputText);

    public СhatWindow(){
        setTitle("Home work chat");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(Toolkit.getDefaultToolkit ().getScreenSize ().width/2-WIDTH/2,
     Toolkit.getDefaultToolkit ().getScreenSize ().height/2-HEIGHT/2, WIDTH, HEIGHT);
        setResizable(false);

        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setBorder(BorderFactory.createEtchedBorder(Color.WHITE, new Color(150, 200, 150)));
        textArea.setBackground(new Color(250, 255, 250));
        inputText.setLineWrap(true);
        inputText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    e.consume();
                    setText(inputText.getText());
                }
            }
        });

        jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setText(inputText.getText());
            }
        });

        setLayout(new BorderLayout());
        add(textArea, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        panel.setLayout(new BorderLayout());
        panel.add(jsp, BorderLayout.CENTER);
        panel.add(enter, BorderLayout.EAST);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setVisible(true);

    }

    private void setText(String text) {
        textArea.append("Name: " + text+ "\n"+ "\n");
        inputText.setText(null);
        inputText.setCaretPosition(0);

    }


}
