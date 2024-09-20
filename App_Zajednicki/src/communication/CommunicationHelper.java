/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

/**
 *
 * @author Isidora
 */

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.Socket;

public class CommunicationHelper {

//pomocna klasa koja otvara in i out stream i salje i prima objekte preko soketa    
    
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public CommunicationHelper(Socket socket) throws IOException {
        this.socket = socket;
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }

    public void send(Object obj) throws IOException {
        out.writeObject(obj);
    }

    public Object receive() throws IOException, ClassNotFoundException {
        return in.readObject();
    }
}

