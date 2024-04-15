package com.example.myapp.Dato;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.myapp.Conexion.Db;

import java.util.ArrayList;

public class Dnotaventa {

    private SQLiteDatabase BasedeDatos;

    Context context;
    private Dvendedor dc;
    public Dnotaventa(@Nullable Context context) {
        this.context=context;
        this.dc=new Dvendedor(this.context);
    }
    //-----INSERTANDO A LA BASE DE DATOS ---------
    public long agregar(String fecha,int idcliente  ) {
        Db dbHelper = new Db(context);
        BasedeDatos = dbHelper.getWritableDatabase();
        long i=0;
        try{
            ContentValues registro=new ContentValues();
            registro.put("fecha",fecha);
            registro.put("idcliente",idcliente);
            i =BasedeDatos.insert("cotizacion",null,registro);
            BasedeDatos.close();
        }catch ( Exception e){
            e.toString();
        }
        return i;
    }

    //-----MOSTRAR LISTA DE COTIZACION DE LA BASE DE DATOS ---------

    public ArrayList<Cotizacion> getlistaCotizacion(){
        Db dbHelper = new Db(context);
        BasedeDatos = dbHelper.getWritableDatabase();
        ArrayList<Cotizacion> listaCotizacion;
        listaCotizacion = new ArrayList<>();

        Cotizacion cotizacion=null;
        Cursor cursorCotizacion= null;

        cursorCotizacion =BasedeDatos.rawQuery("SELECT *FROM cotizacion",null);

        if (cursorCotizacion.moveToFirst()){
            do{
                cotizacion =new Cotizacion();
                cotizacion.setId(cursorCotizacion.getInt(0));
                cotizacion.setFecha(cursorCotizacion.getString(1));
                listaCotizacion.add(cotizacion);
            }while (cursorCotizacion.moveToNext());
        }
        cursorCotizacion.close();
        return listaCotizacion;
    }
///-----------------RETORNA LA COTIZACION EN VENTA----------------------------------------------

  public Vendedor getClienteCotizacion(int id){
    Db dbHelper = new Db(context);
    BasedeDatos = dbHelper.getWritableDatabase();

    Vendedor vendedor =null;
    Cursor cursorcotizacion= null;

      cursorcotizacion =BasedeDatos.rawQuery("SELECT *FROM cotizacion WHERE id="+id,null);

    if (cursorcotizacion.moveToFirst()){

        vendedor =new Vendedor();
        int idcliente=cursorcotizacion.getInt(2);
        vendedor =  dc.getCliente(idcliente);

    }
      cursorcotizacion.close();
    return vendedor;

    }
}
