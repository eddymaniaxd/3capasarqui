package com.example.myapp.Dato;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.myapp.Conexion.Db;

import java.util.ArrayList;

public class DdetalleNotaVenta {
    private SQLiteDatabase BasedeDatos;
    Context context;
    private Dproducto dp;
    public DdetalleNotaVenta(@Nullable Context context) {

        this.context=context;
        this.dp=new Dproducto(this.context);
    }
    //-----INSERTANDO A LA BASE DE DATOS ---------
    public long agregar(int idcotizacion,int idproducto,int cantidad  ) {
        Db dbHelper = new Db(context);
        BasedeDatos = dbHelper.getWritableDatabase();
        long i=0;
        try{
            ContentValues registro=new ContentValues();
            registro.put("idcotizacion",idcotizacion);
            registro.put("idproducto",idproducto);
            registro.put("cantidad",cantidad);
            i =BasedeDatos.insert("detallecotizacion",null,registro);
            BasedeDatos.close();
        }catch ( Exception e){
            e.toString();
        }
        return i;
    }

    //-----MOSTRAR LISTA DE LOS PRODUCTO DE LA COTIZACION DE LA BASE DE DATOS ---------//
   public ArrayList<Producto> getlistaProdCotizacion(int idcot){
        Db dbHelper = new Db(context);
        BasedeDatos = dbHelper.getWritableDatabase();
        ArrayList<Producto> listaProducto;
        listaProducto = new ArrayList<>();
        Producto producto=null;
        Cursor cursordetalle= null;
        cursordetalle =BasedeDatos.rawQuery("SELECT *FROM detallecotizacion  WHERE idcotizacion="+idcot,null);
        if (cursordetalle.moveToFirst()){
            do{
                producto =new Producto();
                //hubo cambio ahi 0 por 2
                int idprod=cursordetalle.getInt(2);

                producto=  dp.getProducto(idprod);
                listaProducto.add(producto);
            }while (cursordetalle.moveToNext());
        }
        cursordetalle.close();
        return listaProducto;
    }

     //prueba de deatalle cotizacion realizando el array para enviar al adaptador

   public ArrayList<DetalleNotaVenta> getlistaDetalleCotizacion(int idcot){
        Db dbHelper = new Db(context);
        BasedeDatos = dbHelper.getWritableDatabase();
        ArrayList<DetalleNotaVenta> listaDetalle;
       listaDetalle = new ArrayList<>();
       DetalleNotaVenta detalle=null;
        Cursor cursordetalle= null;
        cursordetalle =BasedeDatos.rawQuery("SELECT *FROM detallecotizacion  WHERE idcotizacion="+idcot,null);
        if (cursordetalle.moveToFirst()){
            do{
                detalle =new DetalleNotaVenta();
                detalle.setId(cursordetalle.getInt(0));
                detalle.setIdcotizacion(cursordetalle.getInt(1));
                detalle.setIdproducto(cursordetalle.getInt(2));
                detalle.setCantidad(cursordetalle.getInt(3));
                listaDetalle.add(detalle);
            }while (cursordetalle.moveToNext());
        }
        cursordetalle.close();
        return listaDetalle ;
    }

    public DetalleNotaVenta getDetalle(int id){
        Db dbHelper = new Db(context);
        BasedeDatos = dbHelper.getWritableDatabase();

        DetalleNotaVenta detalle=null;
        Cursor cursordetalle= null;

        cursordetalle =BasedeDatos.rawQuery("SELECT *FROM detallecotizacion WHERE id="+id,null);

        if (cursordetalle.moveToFirst()){

            detalle =new DetalleNotaVenta();
            detalle.setId(cursordetalle.getInt(0));
            detalle.setIdcotizacion(cursordetalle.getInt(1));
            detalle.setIdproducto(cursordetalle.getInt(2));
            detalle.setCantidad(cursordetalle.getInt(3));

        }
        cursordetalle.close();
        return detalle;
    }

    //-----ElIMINAR UN PRODUCTO DEL DETALLE DE PRODUCTO  DE LA BASE DE DATOS ---------

    public boolean elimDetalleProducto(int id) {
        Db dbHelper = new Db(context);
        BasedeDatos = dbHelper.getWritableDatabase();
        boolean correcto=false;
        try{
            BasedeDatos.execSQL("DELETE FROM detallecotizacion WHERE id='"+id+"'");
            correcto=true;
        }catch ( Exception e){
            e.toString();
            correcto=false;
        }finally {
            BasedeDatos.close();
        }
        return correcto=true;
    }

    //-----EDICION UN PRODUCTO DEL DETALLE DE PRODUCTO  DE LA BASE DE DATOS ---------
    public boolean editDetalleProducto(int id,int cantidad ) {
        Db dbHelper = new Db(context);
        BasedeDatos = dbHelper.getWritableDatabase();
        boolean correcto=false;
        try{
            BasedeDatos.execSQL("UPDATE detallecotizacion SET cantidad='"+cantidad+"'WHERE id='"+id+"'");
            correcto=true;
        }catch ( Exception e){
            e.toString();
            correcto=false;
        }finally {
            BasedeDatos.close();
        }
        return correcto=true;
    }
    //SUMA DE PRODUCTOS POR AGRUPACIÓN

    public ArrayList<DetalleNotaVenta> sumarProducto(int idcot){
        Db dbHelper = new Db(context);
        BasedeDatos = dbHelper.getWritableDatabase();
        ArrayList<DetalleNotaVenta> listaDetalle;
        listaDetalle = new ArrayList<>();
        DetalleNotaVenta detalle=null;
        Cursor cursordetalle= null;
        cursordetalle =BasedeDatos.rawQuery("SELECT idproducto, Sum(cantidad)FROM detallecotizacion  WHERE idcotizacion='"+idcot+"'GROUP BY (idproducto)",null);
        if (cursordetalle.moveToFirst()){
            do{
                detalle =new DetalleNotaVenta();
                detalle.setIdproducto(cursordetalle.getInt(0));
                detalle.setCantidad(cursordetalle.getInt(1));
                listaDetalle.add(detalle);
            }while (cursordetalle.moveToNext());
        }
        cursordetalle.close();
        return listaDetalle ;
    }

    //Monto TOTAL DE LOS PRODUCTOS
   /* public int  MontoTotal(int idcot){
        Db dbHelper = new Db(context);
        BasedeDatos = dbHelper.getWritableDatabase();
        Cursor cursordetalle= null;
        int monto=0;
        return monto;
    }*/


}
