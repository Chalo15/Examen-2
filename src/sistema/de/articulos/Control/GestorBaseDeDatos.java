/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.de.articulos.Control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Observable;
import sistema.de.artícuos.Vista.Articulo;

/**
 *
 * @author Gonzalo
 */
public class GestorBaseDeDatos extends Observable{
       Connection cnx = null;

        String url = "jdbc:mysql://localhost:3306/modeloprueba";
        String user = "root";
        String password = "root";
        private ArrayList <Articulo> lista;

        
        public void agregar(String nombre, String descripcion, String categoria, int precio, int cantidad)throws Exception {
            
             try {
            cnx = DriverManager.getConnection( url , user , password);
        } catch (Exception exc) {
            throw exc;
        } finally {
        }
             
             
        PreparedStatement stm =
                cnx.prepareStatement("INSERT INTO   "//modificar
            + "(categoria, nombre, cantidad, precio, descripcion) "//modificar
            + "VALUES(?, ?, ?, ?, ?); ");//modificar
        stm.clearParameters();
        stm.setString(1, categoria);
        stm.setString(2, nombre);
        stm.setString(3, String.valueOf(cantidad));
        stm.setString(4, String.valueOf(precio));
        stm.setString(5, descripcion);
        if (stm.executeUpdate() != 1) {
            throw new Exception();
        }

          Statement stmm = cnx.createStatement();
          ResultSet rs = stmm.executeQuery("SELECT * FROM ; ");
          while(rs.next()) {
               
                
                String catego = rs.getString("categoria");
              
                  String nomb = rs.getString("nombre");  
                  int canti = rs.getInt("cantidad");
                  int prec = rs.getInt("precio"); 
                  String descri = rs.getString("descripcion"); 
                   Articulo arti=new Articulo(nomb,descri,catego,prec,canti,0);
                   lista.add(arti);
                }
        
        try {
            cnx.close();
        } catch (Exception exc) {
        } finally {
            cnx = null;
        }
           
        this.setChanged();
        this.notifyObservers();
        
            
        }
        
        public void consultar(String cate) throws Exception{                  
       
             try {
            cnx = DriverManager.getConnection( url , user , password);
        } catch (Exception exc) {
            throw exc;
        } finally {
        }
            
            Statement stm = cnx.createStatement();
            
            // Contiene los datos recuperados.
            ResultSet rs = stm.executeQuery("SELECT * FROM ; ");
        while(rs.next()) {
               
                
                String categoria = rs.getString("categoria");
                if(categoria.equals(cate)){
                  String nombre = rs.getString("nombre");  
                  int cantidad = rs.getInt("cantidad");
                  int precio = rs.getInt("precio"); 
                  String descripcion = rs.getString("descripcion"); 
                   Articulo arti=new Articulo(nombre,descripcion,categoria,precio,cantidad,0);
                   lista.add(arti);
                }
               
                
              
            }
            

      try {
            cnx.close();
        } catch (Exception exc) {
        } finally {
            cnx = null;
        }
     this.setChanged();
     this.notifyObservers();
     }
        
    public ArrayList<Articulo> getLista() {
        return lista;
    }
        
    public void eliminar(String cat) throws Exception{
               
               try {
            cnx = DriverManager.getConnection( url , user , password);
                   } 
               catch (Exception exc) {
            throw exc;
                   } 
               finally {
                }
            
            Statement stm = cnx.createStatement();
            
            // Contiene los datos recuperados.
            ResultSet rs = stm.executeQuery("SELECT * FROM ; ");
            int cont=0;
            while(rs.next()) {

                String categoria = rs.getString("categoria");
                if(categoria.equals(cat)){
                  cont++;
                }
                } 
              if(cont!=0){
                 PreparedStatement stmm =
                cnx.prepareStatement("DELETE FROM  "
                + "WHERE categoria=?; ");
                    stmm.clearParameters();
                stmm.setString(1, cat);
                if (stmm.executeUpdate() != 1) {
                    throw new Exception();
                 }    
                }
            Statement stmmm = cnx.createStatement();
          ResultSet rss = stmmm.executeQuery("SELECT * FROM ; ");
          while(rss.next()) {
               
                
                String catego = rs.getString("categoria");
              
                  String nomb = rs.getString("nombre");  
                  int canti = rs.getInt("cantidad");
                  int prec = rs.getInt("precio"); 
                  String descri = rs.getString("descripcion"); 
                   Articulo arti=new Articulo(nomb,descri,catego,prec,canti,0);
                   lista.add(arti);
                }
     
    try {
            cnx.close();
        } catch (Exception exc) {
        } finally {
            cnx = null;
        }    
        this.setChanged();
     this.notifyObservers();
}
}
