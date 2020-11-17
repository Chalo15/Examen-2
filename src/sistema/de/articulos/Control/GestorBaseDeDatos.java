/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.de.articulos.Control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import sistema.de.artícuos.Vista.Articulo;

/**
 *
 * @author Gonzalo
 */
public class GestorBaseDeDatos{
    private Articulo ar;
    Connection cnx;

    String url;
    String user;
    String password;

    public GestorBaseDeDatos() {
        cnx = null;

        url = "jdbc:mysql://localhost:3306/inventario";
        user = "root";
        password = "sqldepablo99";
    }

        
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
        stm.setString(2, categoria);
        stm.setString(3, nombre);
        stm.setString(4, String.valueOf(cantidad));
        stm.setString(5, String.valueOf(precio));
        stm.setString(6, descripcion);
        if (stm.executeUpdate() != 1) {
            throw new Exception();
        }

        Statement stmm = cnx.createStatement();
        ResultSet rs = stmm.executeQuery("SELECT * FROM articulos;");
        while(rs.next()) {             

              String catego = rs.getString("categoria");

                String nomb = rs.getString("nombre");  
                int canti = rs.getInt("cantidad");
                int prec = rs.getInt("precio"); 
                String descri = rs.getString("descripcion"); 
                Articulo arti=new Articulo(nomb,descri,catego,prec,canti,0);
                ar.InsertarLista(arti);
        }

        try {
            cnx.close();
        } catch (Exception exc) {
        } finally {
            cnx = null;
        }

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
        ResultSet rs = stm.executeQuery("SELECT * FROM articulos; ");
        while(rs.next()) {

            String categoria = rs.getString("categoria");
            if(categoria.equals(cate)){
                String nombre = rs.getString("nombre");  
                int cantidad = rs.getInt("cantidad");
                int precio = rs.getInt("precio"); 
                String descripcion = rs.getString("descripcion"); 
                Articulo arti=new Articulo(nombre,descripcion,categoria,precio,cantidad,0);
                ar.InsertarLista(arti);
            }             
        }


        try {
              cnx.close();
        } catch (Exception exc) {
        } finally {
              cnx = null;
        }
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
        ResultSet rs = stm.executeQuery("SELECT * FROM articulos; ");
        int cont=0;
        while(rs.next()) {

            String categoria = rs.getString("categoria");
            if(categoria.equals(cat)){
              cont++;
            }
            } 
            if(cont==0){
                PreparedStatement stmm =
                cnx.prepareStatement("DELETE FROM  "
                + "WHERE categoria=?; ");
                   stmm.clearParameters();
                   stmm.setString(2, cat);
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
            ar.InsertarLista(arti);
        }

        try {
            cnx.close();
        } catch (Exception exc) {
        } finally {
            cnx = null;
        }    
    }

    public Articulo getAr() {
        return ar;
    }

    
    
    
}
