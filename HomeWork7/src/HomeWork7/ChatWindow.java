package HomeWork7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class ChatWindow extends JFrame implements ClientUI {
    final static int WIDTH = 450;
    final static int HEIGHT = 600;
    private Controller controller;

    JTextArea textArea = new JTextArea();
    JPanel panel = new JPanel();
    JTextArea inputText = new JTextArea(1, 30);
    JButton enter = new JButton("Enter");
    JScrollPane jsp = new JScrollPane(inputText);
    JScrollPane jspTextArea = new JScrollPane(textArea);

    public ChatWindow(Controller controller){
        this.controller = controller;

        setTitle("Home work chat");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(Toolkit.getDefaultToolkit ().getScreenSize ().width/2-WIDTH/2,
                Toolkit.getDefaultToolkit ().getScreenSize ().height/2-HEIGHT/2, WIDTH, HEIGHT);
        setResizable(false);

        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setBorder(BorderFactory.createEtchedBorder(Color.WHITE, new Color(150, 200, 150)));
        textArea.setBackground(new Color(250, 255, 250));

        jspTextArea.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jspTextArea.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        inputText.setLineWrap(true);
        inputText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    e.consume();
                    sendText();
                }
            }
        });

        jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendText();
            }
        });

        setLayout(new BorderLayout());
        add(jspTextArea, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        panel.setLayout(new BorderLayout());
        panel.add(jsp, BorderLayout.CENTER);
        panel.add(enter, BorderLayout.EAST);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setVisible(true);

    }

    private void sendText() {
        controller.sendMessage(inputText.getText());
        inputText.setText(null);
        inputText.setCaretPosition(0);

    }

    @Override
    public void addMessage(String w){
        textArea.append(w);
        textArea.append("\n");
    }

    @Override
    public void showUI(){
        setVisible(true);
    }

}
