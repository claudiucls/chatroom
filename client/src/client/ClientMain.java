package client;

import javax.swing.*;
import java.io.IOException;

public class ClientMain {

    public static void main(String[] args) throws IOException {
        String username = JOptionPane.showInputDialog("Username:");

        ChatPanel chat = new ChatPanel(username);

        JFrame frame = new JFrame();

        frame.setContentPane(chat.getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
