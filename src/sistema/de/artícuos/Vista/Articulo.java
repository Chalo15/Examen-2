/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.de.artícuos.Vista;

import java.util.ArrayList;

/**
 *
 * @author Gonzalo
 */
public class Articulo {
     private String nombre, descripcion,categoria;
    private int precio, cantidad;
    int banderaOpcion;
    private ArrayList <Articulo> lista = null;

    public Articulo(String nombre, String descripcion, String categoria, int precio, int cantidad, int banderaOpcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precio = precio;
        this.cantidad = cantidad;
        this.banderaOpcion = banderaOpcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getBanderaOpcion() {
        return banderaOpcion;
    }

    public void setBanderaOpcion(int banderaOpcion) {
        this.banderaOpcion = banderaOpcion;
    }

    public ArrayList<Articulo> getLista() {
        return lista;
    }
    public void InsertarLista(Articulo a){
        lista.add(a);
    }

    @Override
    public String toString() {
        return "Articulo{" + "nombre=" + nombre + ", descripcion=" + descripcion + ", categoria=" + categoria + ", precio=" + precio + ", cantidad=" + cantidad + ", banderaOpcion=" + banderaOpcion + '}';
    }

    
}
