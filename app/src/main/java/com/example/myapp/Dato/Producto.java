package com.example.myapp.Dato;

public class Producto {
    private String nombreProducto, descripcion;
    private byte[] imagen;
    private int precio,id,idcat ;


    public Producto() {
        this.nombreProducto = nombreProducto;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen=imagen;
        this.idcat=idcat;
    }

    public int getId() {
        return id;
    }
    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
    public String toString(){
        return nombreProducto;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public int getIdcat() {
        return idcat;
    }

    public void setIdcat(int idcat) {
        this.idcat = idcat;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
}
