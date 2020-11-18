/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.de.artícuos.Modelo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import sistema.de.articulos.Control.GestorBaseDeDatos;
import sistema.de.articulos.Control.GestorCliente;

/**
 *
 * @author USER
 */
public class Servidor  {
    
    public static void main(String[] args) {
        Servidor a = new Servidor();
        a.iniciar();
    }
    
    public Servidor(){
        clientes = new ArrayList<>();
        numCliente = 0;
        base = new GestorBaseDeDatos();
    }

    public void iniciar() {
        System.out.println("Iniciando aplicación..");
        hiloAtencion = new Thread(new Runnable() {
            @Override
            public void run() {
                atenderClientes();
            }
        });
        hiloAtencion.start();
    }
    
    public void atenderClientes(){
    
        int puerto = 1234;
        try {
            System.out.println("Esperando conexión..");
            srv = new ServerSocket(puerto);            
            while (hiloAtencion == Thread.currentThread()) {
                try {
                    skt = srv.accept();
                    numCliente++;
                    System.out.println("Conexión iniciada por el Cliente#" + numCliente);                    
                    GestorCliente nuevoCliente = new GestorCliente(this, skt,numCliente);
                    clientes.add(nuevoCliente);
                    iniciarCanalComunicacion(nuevoCliente);
                    System.out.println("Canal de comunicación abierto con: " + nuevoCliente + " Cliente#" + numCliente);
                } catch (SocketTimeoutException e) {
                    // No se ha conectado ningún cliente.
                    // Se mantiene esperando conexiones..
                    System.out.println("El servidor se mantiene esperando conexiones ...");
                }             
            }
            
        } 
        catch (Exception e) {
            System.err.println(" Se perdió la conexión con el cliente ...");
        }
        finally{
            try {
                skt.close();
                srv.close();
            } catch (IOException ex) {
                System.err.println("Ocurrio un error con la entrada de datos ... ");
            }
            System.out.println("Servidor - Conexión cerrada ...");
        }
    }
    
    public void iniciarCanalComunicacion(GestorCliente nuevoCliente){
        hiloCliente= new Thread(nuevoCliente);
        if(hiloCliente!=null){
            hiloCliente.start();
        }
        
    }
    public String agregar(String nombre, String descripcion, String categoria, int precio, int cantidad) throws Exception{
        return base.agregar(nombre, descripcion, categoria, precio, cantidad); 
    }
    public String consultar(String c) throws Exception{
        return base.consultar(c);  
        
   
    }
    public String eliminar(String c) throws Exception{
        return base.eliminar(c);      
    }

    public GestorBaseDeDatos getBase() {
        return base;
    }
    
    //Atributos
    private ServerSocket srv;
    private Socket skt;
    private Thread hiloAtencion;
    private Thread hiloCliente;
    private ArrayList<GestorCliente> clientes;
    private int numCliente;
    private GestorBaseDeDatos base;
}
