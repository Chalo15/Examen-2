/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.de.articulos.Control;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import sistema.de.artícuos.Modelo.Servidor;

/**
 *
 * @author USER
 */
public class GestorCliente implements Runnable{
    
    public GestorCliente(Servidor gestor, Socket skt, int num) {
        gestorPrincipal = gestor;
        if(skt!=null){
            sktCliente = skt;
        }
        direccionCliente = sktCliente.getInetAddress();
        nCliente = num; 
    }

    @Override
    public void run() {
        try {            
            entrada =
                    new BufferedInputStream(sktCliente.getInputStream());
            Scanner r = new Scanner(entrada);
            salida = new PrintWriter(sktCliente.getOutputStream());
            
            while(true) {
                String s = r.nextLine();
                System.out.println("Línea recibida del Cliente#" + nCliente + ": " + s);
                salida.println("OK");
                salida.flush();
            }
            
        } catch (IOException e) {
            System.err.println("Ocurrio un error con la entrada de datos ... ");
        }
        catch (Exception e) {
            System.err.println(" Se perdió la conexión con el cliente ...");
        }
        finally{
            salida.close();
            try {
                entrada.close();
            } catch (IOException ex) {
                System.err.println("Ocurrio un error con la entrada de datos ... ");
            }
            System.out.println("Servidor - Conexión cerrada ...");
        }
    }  
    
    @Override
    public String toString() {
        return String.format(
                "Cliente@%s", direccionCliente.getHostName(),nCliente);
    }
    
    
    private Servidor gestorPrincipal;
    private InetAddress direccionCliente;
    private Socket sktCliente;
    private BufferedInputStream entrada;
    private PrintWriter salida;   
    private final int nCliente;
}
