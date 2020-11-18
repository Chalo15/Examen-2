/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.de.articulos.Control;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import sistema.de.artícuos.Vista.Articulo;
import sistema.de.artícuos.Vista.Ventana;

/**
 *
 * @author USER
 */
public class ClienteSocket implements Runnable {
    private PrintWriter salida;
    private BufferedInputStream entrada;
    private Socket socket;
    private Ventana ven;
    
    public ClienteSocket(Ventana cli){
        this.ven = cli;
    }

    @Override
    public void run() {

        try {
            socket = new Socket("localhost",1234);
            salida = new PrintWriter(socket.getOutputStream());
            entrada = new BufferedInputStream(socket.getInputStream());
            
            Scanner srv = new Scanner(entrada);
            
            while(true){
            
            // Espera la respuesta..
            String c = srv.nextLine();
            System.out.println("Confirmación: " + c);
            ven.actualizarTabla(c);                     
        }
            
        } catch (IOException ex) {
            Logger.getLogger(ClienteSocket.class.getName()).log(Level.SEVERE, null, ex);
        }           
    }
    
    //enviando al servidor
    public void enviarAlServidor(String ar){
        salida.println(ar);
        salida.flush();
    }
    
    
    
}
