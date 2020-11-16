/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.de.articulos.Control;

//import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Observer;
import sistema.de.artícuos.Modelo.Servidor;
import sistema.de.artícuos.Vista.Articulo;

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
            entrada = new ObjectInputStream(sktCliente.getInputStream());
            salida = new ObjectOutputStream(sktCliente.getOutputStream());

            
            while(true) {//extraigo la info del objeto
                  String c = entrada.readObject().toString();
                  Articulo art=null;
                  try{
                      art=(Articulo)entrada.readObject();
                  }
                   catch (ClassNotFoundException ex) {
                    
                } 
                  catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
          
                System.out.println("Línea recibida del Cliente#" + nCliente + ": " + c);
                
                
                
                int opc=art.getBanderaOpcion();
                switch(opc){
                    case 1:
                        gestorPrincipal.agregar(art.getNombre(), art.getDescripcion(), art.getCategoria(), art.getPrecio(), art.getCantidad());
                        break;
                    case 2:
                        gestorPrincipal.consultar(art.getCategoria());
                        break;
                    case 3:
                        gestorPrincipal.eliminar(art.getCategoria());
                        break;
                }
                   
                try{
                    
                    
                salida.writeObject(gestorPrincipal);
                salida.flush();
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
            try{
            salida.close();
            try {
                entrada.close();
            } catch (IOException ex) {
                System.err.println("Ocurrio un error con la entrada de datos ... ");
            }
            System.out.println("Ocurrio un error con la salida de datos ...");
            }
            catch(IOException ex){
                System.err.println("Ocurrio un error con la entrada de datos ... ");
            }
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
    private ObjectInputStream entrada;
    private ObjectOutputStream salida;  
    private final int nCliente;
}
