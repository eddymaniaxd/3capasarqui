package com.example.myapp.Dato;

public class Cotizacion {
    private int id;
   private String fecha;
    public Cotizacion() {
        this.id=id;
        this.fecha=fecha;

    }

    public String getFecha() {
        return fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}
