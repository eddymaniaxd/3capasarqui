package com.example.myapp.Dato;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.myapp.Conexion.Db;

import java.util.ArrayList;

public class Dproducto {

    private SQLiteDatabase BasedeDatos;

    Context context;

    public  Dproducto(@Nullable Context context) {

        this.context=context;
    }
    //-----INSERTANDO A LA BASE DE DATOS ---------
    public long agregar(String nombreProducto,int precio,String descripcion, byte[] image,int idcat  ) {
        Db dbHelper = new Db(context);
        BasedeDatos = dbHelper.getWritableDatabase();
        long i=0;
        try{
            ContentValues registro=new ContentValues();
            registro.put("nombreProducto",nombreProducto);
            registro.put("precio",precio);
            registro.put("descripcion",descripcion);
            registro.put("image",image);
            registro.put("idcat",idcat);
            i =BasedeDatos.insert("producto",null,registro);
            BasedeDatos.close();
        }catch ( Exception e){
            e.toString();
        }
        return i;
    }




//-----MOSTRAR LISTA DE PRODUCTO DE LA BASE DE DATOS ---------

    public ArrayList<Producto> getlistaProducto(){
        Db dbHelper = new Db(context);
        BasedeDatos = dbHelper.getWritableDatabase();
        ArrayList<Producto> listaProducto;
        listaProducto = new ArrayList<>();

        Producto producto=null;
        Cursor cursorProducto= null;

        cursorProducto =BasedeDatos.rawQuery("SELECT *FROM producto",null);

        if (cursorProducto.moveToFirst()){
            do{
                producto =new Producto();
                producto.setId(cursorProducto.getInt(0));
                producto.setNombreProducto(cursorProducto.getString(1));
                producto.setPrecio(cursorProducto.getInt(2));
                producto.setDescripcion(cursorProducto.getString(3));
                producto.setImagen(cursorProducto.getBlob(4));
                listaProducto.add(producto);
            }while (cursorProducto.moveToNext());
        }
        cursorProducto.close();
        return listaProducto;
    }

    //-----EDICION DE PRODUCTO DE LA BASE DE DATOS ---------
    public boolean editProducto(int id,String nombreProducto,int precio,String descripcion,int idcat ) {
        Db dbHelper = new Db(context);
        BasedeDatos = dbHelper.getWritableDatabase();
        boolean correcto=false;
        try{
            BasedeDatos.execSQL("UPDATE producto SET nombreProducto='"+nombreProducto+"',precio='"+precio+"',descripcion='"+descripcion+"',idcat='"+idcat+"'WHERE id='"+id+"'");
            correcto=true;
        }catch ( Exception e){
            e.toString();
            correcto=false;
        }finally {
            BasedeDatos.close();
        }
        return correcto=true;
    }
    //-----OBTENER EL OBJETO  PRODUCTO  DE LA BASE DE DATOS ---------

    public Producto getProducto(int id){
        Db dbHelper = new Db(context);
        BasedeDatos = dbHelper.getWritableDatabase();

        Producto producto=null;
        Cursor cursorProducto= null;

        cursorProducto =BasedeDatos.rawQuery("SELECT *FROM producto WHERE id="+id,null);

        if (cursorProducto.moveToFirst()){

            producto =new Producto();
            producto.setId(cursorProducto.getInt(0));
            producto.setNombreProducto(cursorProducto.getString(1));
            producto.setPrecio(cursorProducto.getInt(2));
            producto.setDescripcion(cursorProducto.getString(3));
            producto.setImagen(cursorProducto.getBlob(4));

        }
        cursorProducto.close();
        return producto;
    }

    //-----ElIMINAR EL PRODUCTO  DE LA BASE DE DATOS ---------

    public boolean elimProducto(int id) {
        Db dbHelper = new Db(context);
        BasedeDatos = dbHelper.getWritableDatabase();
        boolean correcto=false;
        try{
            BasedeDatos.execSQL("DELETE FROM producto WHERE id='"+id+"'");
            correcto=true;
        }catch ( Exception e){
            e.toString();
            correcto=false;
        }finally {
            BasedeDatos.close();
        }
        return correcto=true;
    }
    //-----OBTENER EL ID CATEGORIA DEL PRODUCTO  DE LA BASE DE DATOS ---------

    public int getIdCategoria(int id){
        Db dbHelper = new Db(context);
        BasedeDatos = dbHelper.getWritableDatabase();
        int idcat=0;
        Cursor cursorProducto= null;

        cursorProducto =BasedeDatos.rawQuery("SELECT *FROM producto WHERE id="+id,null);

        if (cursorProducto.moveToFirst()){
           idcat= cursorProducto.getInt(5);

        }
        cursorProducto.close();
        return idcat;
    }


    //-----MOSTRAR LISTA DE PRODUCTO DE LA BASE DE DATOS POR CATEGORIA---------

    public ArrayList<Producto> getlistaProductoCategoria(){
        Db dbHelper = new Db(context);
        BasedeDatos = dbHelper.getWritableDatabase();
        ArrayList<Producto> listaProducto;
        listaProducto = new ArrayList<>();

        Producto producto=null;
        Cursor cursorProducto= null;

        cursorProducto =BasedeDatos.rawQuery("SELECT *FROM producto ORDER BY idcat asc",null);

        if (cursorProducto.moveToFirst()){
            do{
                producto =new Producto();
                producto.setId(cursorProducto.getInt(0));
                producto.setNombreProducto(cursorProducto.getString(1));
                producto.setPrecio(cursorProducto.getInt(2));
                producto.setDescripcion(cursorProducto.getString(3));
                producto.setImagen(cursorProducto.getBlob(4));
                listaProducto.add(producto);
            }while (cursorProducto.moveToNext());
        }
        cursorProducto.close();
        return listaProducto;
    }

}
