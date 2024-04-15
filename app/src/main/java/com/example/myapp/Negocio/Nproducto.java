package com.example.myapp.Negocio;

import android.content.Context;

import com.example.myapp.Dato.Dproducto;
import com.example.myapp.Dato.Producto;

import java.util.ArrayList;

public class Nproducto {
    private Dproducto dc;
    Context context;

    public Nproducto(Context context1){
        this.context=context1;
        this.dc=new Dproducto(this.context);
    }

    public  long agregar(String nombreProducto,int precio,String descripcion,byte[] imagen,int idcat ) {
        long i;
        if (!nombreProducto.equals("") && (precio > 0) && idcat != 0) {
            i = dc.agregar(nombreProducto, precio, descripcion, imagen, idcat);
        } else {
            i = 0;
        }
        return i;

    }


    public ArrayList<Producto> getlistaProducto(){
        return dc.getlistaProducto();
    }

    public boolean editProducto(int id,String nombreProducto,int precio,String descripcion,int idcat){
        boolean  correcto=false;
        if (!nombreProducto.equals("")&&(precio>0)&&idcat!=0){
            correcto=dc.editProducto(id,nombreProducto,precio,descripcion,idcat);
        }
        return correcto;
    }
    public boolean elimProducto(int id) {
        boolean correcto;
        return correcto = dc.elimProducto(id);
    }
    public Producto getProducto(int id){
        return dc.getProducto(id);
    }

    public int getIdCategoria(int id){
        return dc.getIdCategoria(id);
    }

    public ArrayList<Producto> getlistaProductoCategoria(){
        return dc.getlistaProductoCategoria();
    }
}
