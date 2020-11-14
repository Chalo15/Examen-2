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

/**
 *
 * @author Gonzalo
 */
public class GestorBaseDeDatos {
       Connection cnx = null;

        String url = "jdbc:mysql://localhost:3306/modeloprueba";
        String user = "root";
        String password = "root";

        
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

        GestorBD.obtenerInstancia().cerrarConexion();
            
            
        }
        try {
            cnx = DriverManager.getConnection(
                    url, user, password);

            String cmd = "SELECT * FROM usuarios; ";
            Statement stm = cnx.createStatement();
            
            // Contiene los datos recuperados.
            
            ResultSet rs = stm.executeQuery(cmd);
            
            // Contiene información sobre los datos
            // recuperados, NO contiene los datos.
            
            ResultSetMetaData md = rs.getMetaData();
            System.out.println(
                    "Los datos recuperados contienen " +
                    md.getColumnCount() +
                    " columnas.");
            
            while(rs.next()) {
                int id = rs.getInt("id");
                String login = rs.getString("login");
                String nombre = rs.getString("nombre");
                Date uAcceso = rs.getDate("ultimoAcceso");
                
                System.out.println(String.format(
                        "%d\t%s\t%s\t%s",
                        id,login,nombre,uAcceso.toString()));
            }
            

        } catch (Exception exc) {
        } finally {
            try {
                cnx.close();
            } catch (SQLException ioe) {
            }
        }
    }
}
