/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.de.articulos.Control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import sistema.de.artícuos.Vista.Articulo;

/**
 *
 * @author Gonzalo
 */
public class GestorBaseDeDatos {

    String arti = "";
    private Articulo ar;
    private ArrayList<Articulo> lista = new ArrayList();
    Connection cnx;
    Statement stmm;
    PreparedStatement stm;

    String url;
    String user;
    String password;

    public GestorBaseDeDatos() {
        Articulo a1 = new Articulo("Yogurt", "Light", "Lacteos", 2500, 10, 1);
        Articulo a2 = new Articulo("Aceite Mazola", "Para cocinar", "Abarrotes", 2700, 20, 1);
        Articulo a3 = new Articulo("En las motañas de la locura", "Autor HP Lovecraft", "Libros", 5000, 7, 1);
        Articulo a4 = new Articulo("Taladro", "Herramienta de hogar", "Herramientas", 15000, 5, 1);
        Articulo a5 = new Articulo("Circuito de carros", "Para juegos ", "Juguetes", 7000, 9, 1);
        Articulo a6 = new Articulo("Castillo de princesas", "Para juegos", "Jueguetes", 7000, 5, 1);
        Articulo a7 = new Articulo("Silla de oficina", "Asiento para oficina", "Oficina", 20000, 8, 1);
        lista.add(a1);
        lista.add(a2);
        lista.add(a3);
        lista.add(a4);
        lista.add(a5);
        lista.add(a6);
        lista.add(a7);
        stm = null;
        stmm = null;
    }

    public String agregar(String nombre, String descripcion, String categoria, int precio, int cantidad) throws Exception {
       /* try {
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
        ConexionBase.obtenerInstancia().cerrarConexion();*/
        Articulo a = new Articulo(nombre, descripcion, categoria, precio, cantidad, 1);
        lista.add(a);
        return ObtenerBase();
    }

    public String consultar(String cate) throws Exception {
       /* try {
            cnx = ConexionBase.obtenerInstancia().obtenerConexion();
            String consulta = "SELECT * FROM articulos where categoria = " + cate;
            stmm = cnx.createStatement();
            ResultSet rs = stmm.executeQuery(consulta);
            while (rs.next()) {
                arti += rs.getString(2) + "_" + rs.getString(3) + "_" + rs.getString(4) + "_" + rs.getString(5) + "_" + rs.getString(6) + ";";
            }
        } catch (Exception ex) {
            Logger.getLogger(GestorBaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        ConexionBase.obtenerInstancia().cerrarConexion();*/
       ArrayList<Articulo> lista2=new ArrayList();
       String artii="";
       for(int i=0;i<lista.size();i++){
           if(lista.get(i).getCategoria().equals(cate)){
               lista2.add(lista.get(i));
           }
       }
       for(int i=0;i<lista2.size();i++){
           // Articulo arr=lista2.get(i);
            artii+= lista2.get(i).getCategoria()+"_"+lista2.get(i).getNombre()+"_"+String.valueOf(lista2.get(i).getCantidad())+"_"+String.valueOf(lista2.get(i).getPrecio())+"_"+lista2.get(i).getDescripcion()+";";
            
        }
        return artii;
    }

    public String eliminar(String cat) throws Exception {
      /*  try {
            cnx = ConexionBase.obtenerInstancia().obtenerConexion();
            String consulta = "DELETE FROM articulos WHERE categoria = ?";
            stm = cnx.prepareStatement(consulta);
            stm.setString(1, cat);
            stm.execute();
            stm.close();
        } catch (Exception ex) {
            Logger.getLogger(GestorBaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConexionBase.obtenerInstancia().cerrarConexion();*/
        for(int i=0;i<lista.size();i++){
            if(lista.get(i).getCategoria().equals(cat)){
                lista.remove(i);
            }
        }
        arti = "";
        for(int i=0;i<lista.size();i++){
            //Articulo ar=lista.get(i);
            arti += lista.get(i).getCategoria()+"_"+lista.get(i).getNombre()+"_"+String.valueOf(lista.get(i).getCantidad())+"_"+String.valueOf(lista.get(i).getPrecio())+"_"+lista.get(i).getDescripcion()+";";
            
        }
        return arti;
    }

    public String ObtenerBase() {
        /*try {
            cnx = ConexionBase.obtenerInstancia().obtenerConexion();
            String consulta = "SELECT * FROM articulos";
            stmm = cnx.createStatement();
            ResultSet rs = stmm.executeQuery(consulta);
            while (rs.next()) {
                arti += rs.getString(2) + "_" + rs.getString(3) + "_" + rs.getString(4) + "_" + rs.getString(5) + "_" + rs.getString(6) + ";";
            }

        } catch (Exception ex) {
            Logger.getLogger(GestorBaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        ConexionBase.obtenerInstancia().cerrarConexion();*/
        arti = "";
        for(int i=0;i<lista.size();i++){
            //Articulo ar=lista.get(i);
            arti += lista.get(i).getCategoria()+"_"+lista.get(i).getNombre()+"_"+String.valueOf(lista.get(i).getCantidad())+"_"+String.valueOf(lista.get(i).getPrecio())+"_"+lista.get(i).getDescripcion()+";";
            
        }
        return arti;
    }
}
