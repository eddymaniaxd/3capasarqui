package com.example.myapp.Dato;

public class Vendedor {
    private int id;
    private String nombreCompleto;
    private String celular;
    private String ubicacion;

    public Vendedor() {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.celular = celular;
        this.ubicacion = ubicacion;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getCelular() {
        return celular;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    public String toString(){
        return id+"-"+nombreCompleto;
    }
}
