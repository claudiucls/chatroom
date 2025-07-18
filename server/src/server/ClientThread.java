package server;

import dto.Mesaj;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientThread extends Thread {

    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public ClientThread(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new ObjectInputStream(
                socket.getInputStream()
        );
        this.out = new ObjectOutputStream(
                socket.getOutputStream()
        );
    }
    @Override
    public void run(){
        while(true){
            try {
                Mesaj mesaj = (Mesaj) in.readObject();
                MainServer.trimiteMesajTuturor(mesaj);
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void trimiteMesaj(Mesaj mesaj) throws IOException {
        out.writeObject(mesaj);
        out.flush();
    }



}
