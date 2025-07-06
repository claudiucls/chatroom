package client;

import dto.Mesaj;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;

public class ChatPanel {
    private JTextArea textArea1;
    private JPanel panel1;
    private JTextArea textArea2;
    private JButton trimiteButton;

    private String nume;

    private final static String HOST = "localhost";

    public ChatPanel(String nume) throws IOException {
        this.nume = nume;

        connectToServer();

    }

    private void connectToServer() throws IOException {
        Socket server = new Socket(HOST,4545);

        ObjectOutputStream out = new ObjectOutputStream(
                server.getOutputStream()
        );

        ObjectInputStream in = new ObjectInputStream(
                server.getInputStream()
        );


        new Thread(()->{
            while(true) {
                try {
                    Mesaj mesaj = (Mesaj) in.readObject();
                    SwingUtilities.invokeAndWait(()->
                        textArea1.append(mesaj+"\n")
                    );
                } catch (IOException | ClassNotFoundException | InterruptedException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        trimiteButton.addActionListener(e-> {
            String continut = textArea2.getText();
            Mesaj mesaj = Mesaj.of(nume,continut);

            try {
                out.writeObject(mesaj);
                out.flush();
                textArea2.setText("");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

    }

    public JPanel getPanel(){
        return panel1;
    }

}
