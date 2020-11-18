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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import sistema.de.artícuos.Vista.Articulo;

/**
 *
 * @author Gonzalo
 */
public class GestorBaseDeDatos {

    String arti = "";
    private Articulo ar;
    Connection cnx;
    Statement stmm;
    PreparedStatement stm;

    String url;
    String user;
    String password;

    public GestorBaseDeDatos() {
        stm = null;
        stmm = null;
    }

    public String agregar(String nombre, String descripcion, String categoria, int precio, int cantidad) throws Exception {
        try {
            cnx = ConexionBase.obtenerInstancia().obtenerConexion();
            String consulta = "insert into articulos(categoria, nombre, cantidad, precio, descripcion) values (?,?,?,?,?)";
            stm = cnx.prepareStatement(consulta);
            stm.setString(1, categoria);
            stm.setString(2, nombre);
            stm.setString(3, String.valueOf(cantidad));
            stm.setString(4, String.valueOf(precio));
            stm.setString(5, descripcion);
            stm.execute();
            stm.close();
        } catch (Exception ex) {
            Logger.getLogger(GestorBaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConexionBase.obtenerInstancia().cerrarConexion();
        return ObtenerBase();
    }

    public String consultar(String cate) throws Exception {
        try {
            cnx = ConexionBase.obtenerInstancia().obtenerConexion();
            String consulta = "SELECT * FROM articulos where categoria = " + cate;
            stmm = cnx.createStatement();
            ResultSet rs = stmm.executeQuery(consulta);
            while (rs.next()) {
                arti += rs.getString(2)+"_"+rs.getString(3)+"_"+rs.getString(4)+"_"+rs.getString(5)+"_"+rs.getString(6)+";";
            }          
        } catch (Exception ex) {
            Logger.getLogger(GestorBaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        ConexionBase.obtenerInstancia().cerrarConexion();
        return arti;
    }

    public String eliminar(String cat) throws Exception {
        try {
            cnx = ConexionBase.obtenerInstancia().obtenerConexion();
            String consulta = "DELETE FROM articulos WHERE categoria = ?";
            stm = cnx.prepareStatement(consulta);
            stm.setString(1, cat);
            stm.execute();
            stm.close();
        } catch (Exception ex) {
            Logger.getLogger(GestorBaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConexionBase.obtenerInstancia().cerrarConexion();
        return ObtenerBase();
    }
    
    public String ObtenerBase() {
        try {
            cnx = ConexionBase.obtenerInstancia().obtenerConexion();
            String consulta = "SELECT * FROM articulos";
            stmm = cnx.createStatement();
            ResultSet rs = stmm.executeQuery(consulta);
            while (rs.next()) {
                arti += rs.getString(2)+"_"+rs.getString(3)+"_"+rs.getString(4)+"_"+rs.getString(5)+"_"+rs.getString(6)+";";
            }
            
        } catch (Exception ex) {
            Logger.getLogger(GestorBaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        ConexionBase.obtenerInstancia().cerrarConexion();
        return arti;
    }
}
