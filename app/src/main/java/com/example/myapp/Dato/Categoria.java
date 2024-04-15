package com.example.myapp.Dato;

import java.io.Serializable;

public class Categoria {
    private int id;
    private String nombre;

    public Categoria() {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String  getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

  public String toString(){
        return nombre;
  }


}

