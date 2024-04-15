package com.example.myapp.Negocio;

import android.content.Context;

import com.example.myapp.Dato.Categoria;
import com.example.myapp.Dato.Dcategoria;

import java.util.ArrayList;
import java.util.List;

public class Ncategoria {
    private Dcategoria dc;
    Context context;

    public Ncategoria(Context context1){
        this.context=context1;
        this.dc=new Dcategoria(this.context);
    }

    public  long agregar(String nombre ){
        long i;
        if (!nombre.equals("")){
            i=dc.agregar(nombre);
        }else{  i=0; }
        return i;

    }

    public ArrayList<Categoria> getlistaCategoria(){
        return dc.getlistaCategoria();
    }


    public boolean editCategoria(int id,String nombre){
        boolean  correcto=false;
        if (!nombre.equals("")){
           correcto=dc.editCategoria(id,nombre);
        }
       return correcto;
    }

    public boolean elimCategoria(int id) {
        boolean correcto;
        return correcto = dc.elimCategoria(id);
    }

    public Categoria  getCategoria(int id){
        return dc.getCategoria(id);
    }
}
