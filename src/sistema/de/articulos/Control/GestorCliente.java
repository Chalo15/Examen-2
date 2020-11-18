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
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import sistema.de.artícuos.Modelo.Servidor;
import sistema.de.artícuos.Vista.Articulo;

/**
 *
 * @author USER
 */
public class GestorCliente implements Runnable{
    private String cadena = "";

  
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
            salida = new PrintWriter(sktCliente.getOutputStream());
            entrada = new BufferedInputStream(sktCliente.getInputStream());
            
            Scanner srv = new Scanner(entrada);
            
            while(true) {//extraigo la info del objeto
                
                // Espera la respuesta..
                String c = srv.nextLine();
                System.out.println("Confirmación: " + c);
               
                String[] vec = c.split("_");
                

                int opc = Integer.parseInt(vec[0]);
                System.out.println(opc);
                
                switch(opc){
                    case 0:
                        break;
                    case 1:
                        cadena = gestorPrincipal.agregar(vec[1],vec[2],vec[5],Integer.parseInt(vec[3]),Integer.parseInt(vec[4]));
                        break;
                    case 2:
                        cadena = gestorPrincipal.consultar(vec[1]);
                        break;
                    case 3:
                        cadena = gestorPrincipal.eliminar(vec[1]);
                        break;
                }
                
                try{   
                   
                    salida.println(cadena);
                    salida.flush();
                    //System.out.println("Enviando datos al servidor: " + "Hola");
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
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
            System.out.println("Ocurrio un error con la salida de datos ...");
        }           
    }  
    
    @Override
    public String toString() {
        return String.format("Cliente@%s", direccionCliente.getHostName(),nCliente);
    }
 
    private Servidor gestorPrincipal;
    private InetAddress direccionCliente;
    private Socket sktCliente;
    private PrintWriter salida;
    private BufferedInputStream entrada;  
    private final int nCliente;
}
