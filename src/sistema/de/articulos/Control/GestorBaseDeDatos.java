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

    String url;
    String user;
    String password;

    public GestorBaseDeDatos() {
        cnx = null;

        url = "jdbc:mysql://localhost:3306/inventario";
        user = "root";
        password = "sqldepablo99";
    }

    public String agregar(String nombre, String descripcion, String categoria, int precio, int cantidad) throws Exception {

        try {
            cnx = DriverManager.getConnection(url, user, password);
        } catch (Exception exc) {
            throw exc;
        } finally {
        }

        PreparedStatement stm
                = cnx.prepareStatement("INSERT INTO articulos ; "//modificar
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
        while (rs.next()) {
            arti += rs.getString(2)+"_"+rs.getString(3)+"_"+rs.getString(4)+"_"+rs.getString(5)+"_"+rs.getString(6)+";";
        }
        try {
            cnx.close();
        } catch (Exception exc) {
        } finally {
            cnx = null;
        }
        return arti;

    }

    public String consultar(String cate) throws Exception {

        try {
            cnx = DriverManager.getConnection(url, user, password);
        } catch (Exception exc) {
            throw exc;
        } finally {
        }

        Statement stm = cnx.createStatement();

        // Contiene los datos recuperados.
        ResultSet rs = stm.executeQuery("SELECT * FROM articulos; ");
        while (rs.next()) {

            String categoria = rs.getString("categoria");
            if (categoria.equals(cate)) {
                arti += rs.getString(2)+"_"+rs.getString(3)+"_"+rs.getString(4)+"_"+rs.getString(5)+"_"+rs.getString(6)+";";
            }
        }
        try {
            cnx.close();
        } catch (Exception exc) {
        } finally {
            cnx = null;
        }
        return arti;
    }

    public String eliminar(String cat) throws Exception {

        try {
            cnx = DriverManager.getConnection(url, user, password);
        } catch (Exception exc) {
            throw exc;
        } finally {
        }

        Statement stm = cnx.createStatement();

        // Contiene los datos recuperados.
        ResultSet rs = stm.executeQuery("SELECT * FROM articulos; ");
        int cont = 0;
        while (rs.next()) {

            String categoria = rs.getString("categoria");
            if (categoria.equals(cat)) {
                cont++;
            }
        }
        if (cont == 0) {
            PreparedStatement stmm
                    = cnx.prepareStatement("DELETE FROM articulos; "
                            + "WHERE categoria=?; ");
            stmm.clearParameters();
            stmm.setString(2, cat);
            if (stmm.executeUpdate() != 1) {
                throw new Exception();
            }
        }
        Statement stmm = cnx.createStatement();
        ResultSet rss = stmm.executeQuery("SELECT * FROM articulos;");
        while (rs.next()) {
            arti += rss.getString(2)+"_"+rss.getString(3)+"_"+rss.getString(4)+"_"+rss.getString(5)+"_"+rss.getString(6)+";";
        }

        try {
            cnx.close();
        } catch (Exception exc) {
        } finally {
            cnx = null;
        }
        return arti;
    }
    
}
