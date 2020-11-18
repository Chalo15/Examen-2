/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.de.articulos.Control;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author USER
 */
public class ConexionBase {

    private static ConexionBase instancia = null;
    private static String URL = "jdbc:mysql://localhost:3306/inventario";
    private static String USUARIO = "root";
    private static String CLAVE = "sqldepablo99";
    private Connection cnx = null;
    String protocolo;
    String url;
    String baseDatos;
    String usuario;
    String clave;
//  

    private ConexionBase(String url, String usuario, String clave) {
        this.cnx = null;
        this.url = url;
        this.usuario = usuario;
        this.clave = clave;
    }

    private ConexionBase() throws Exception {
        this(URL, USUARIO, CLAVE);
    }

    public static ConexionBase obtenerInstancia() {
        if (instancia == null) {
            try {
                instancia = new ConexionBase();
            } catch (Exception exc) {
            }
        }
        return instancia;
    }

    public Connection obtenerConexion() throws Exception {
        try {
            cnx = DriverManager.getConnection(
                    url,
                    usuario,
                    clave);
        }catch (Exception exc) {
                    throw exc;
                }finally {
        }
        return cnx;
    }

    public void cerrarConexion() {
        try {
            cnx.close();
        }catch (Exception exc) {
                }finally {
            cnx = null;
        }
    }
}