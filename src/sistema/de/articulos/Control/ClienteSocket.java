/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.de.articulos.Control;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import sistema.de.artícuos.Vista.Articulo;
import sistema.de.artícuos.Vista.Ventana;

/**
 *
 * @author USER
 */
public class ClienteSocket implements Runnable {
    private ObjectInputStream entrada;
    private ObjectOutputStream salida;
    private Socket socket;
    private Ventana ven;
    
    public ClienteSocket(Ventana cli){
        this.ven = cli;
    }

    @Override
    public void run() {

        try {
            socket = new Socket("localhost",1234);
            entrada= new ObjectInputStream(socket.getInputStream());
            salida = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ClienteSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(true){
            try {
                //recibiendo del servidor
                ArrayList<Articulo> a = (ArrayList)entrada.readObject();
                ven.actualizarTabla(a);
                
            } catch (IOException ex) {
                Logger.getLogger(ClienteSocket.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClienteSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
                       
        }           
    }
    
    //enviando al servidor
    public void enviarAlServidor(Articulo ar){
        try {
            salida.writeObject(ar);
        } catch (IOException ex) {
            Logger.getLogger(ClienteSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
