package com.example.myapp.Dato;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.myapp.Conexion.Db;

import java.util.ArrayList;

public class Dvendedor {
    private SQLiteDatabase BasedeDatos;

    Context context;

    public Dvendedor(@Nullable Context context) {
     //  super(context);
        this.context=context;
    }

    //-----INSERTANDO A LA BASE DE DATOS ---------
    public long agregar(String nombreCompleto,String celular,String ubicacion ) {
        Db dbHelper = new Db(context);
        BasedeDatos = dbHelper.getWritableDatabase();
        long i=0;
        try{
            ContentValues registro=new ContentValues();
            registro.put("nombreCompleto",nombreCompleto);
            registro.put("celular",celular);
            registro.put("ubicacion",ubicacion);
            i =BasedeDatos.insert("cliente",null,registro);
            BasedeDatos.close();
        }catch ( Exception e){
            e.toString();
        }
        return i;
    }

//-----MOSTRAR LISTA DE VENDORES DE LA BASE DE DATOS ---------

    public ArrayList<Vendedor> getlistaVendedor(){
        Db dbHelper = new Db(context);
        BasedeDatos = dbHelper.getWritableDatabase();
        ArrayList<Vendedor> listaVendedor;
        listaVendedor = new ArrayList<>();

        Vendedor vendedor =null;
        Cursor cursorCliente= null;

        cursorCliente =BasedeDatos.rawQuery("SELECT *FROM cliente",null);

        if (cursorCliente.moveToFirst()){
            do{
                vendedor =new Vendedor();
                vendedor.setId(cursorCliente.getInt(0));
                vendedor.setNombreCompleto(cursorCliente.getString(1));
                vendedor.setCelular(cursorCliente.getString(2));
                vendedor.setUbicacion(cursorCliente.getString(3));
                listaVendedor.add(vendedor);
            }while (cursorCliente.moveToNext());
        }
        cursorCliente.close();
        return listaVendedor;
    }

    //-----EDICION DE VENDEDOR DE LA BASE DE DATOS ---------
    public boolean editVendedor(int id,String nombreCompleto,String celular, String ubicacion ) {
        Db dbHelper = new Db(context);
        BasedeDatos = dbHelper.getWritableDatabase();
        boolean correcto=false;
        try{
            BasedeDatos.execSQL("UPDATE cliente SET nombreCompleto='"+nombreCompleto+"',celular='"+celular+"',ubicacion='"+ubicacion+"'WHERE id='"+id+"'");
            correcto=true;
        }catch ( Exception e){
            e.toString();
            correcto=false;
        }finally {
            BasedeDatos.close();
        }
        return correcto=true;
    }

    //-----ElIMINAR Al Vendedor DE LA BASE DE DATOS ---------

    public boolean elimVendedor(int id) {
        Db dbHelper = new Db(context);
        BasedeDatos = dbHelper.getWritableDatabase();
        boolean correcto=false;
        try{
            BasedeDatos.execSQL("DELETE FROM cliente WHERE id='"+id+"'");
            correcto=true;
        }catch ( Exception e){
            e.toString();
            correcto=false;
        }finally {
            BasedeDatos.close();
        }
        return correcto=true;
    }

    public Vendedor getCliente(int id){
        Db dbHelper = new Db(context);
        BasedeDatos = dbHelper.getWritableDatabase();

        Vendedor vendedor =null;
        Cursor cursorCliente= null;

        cursorCliente =BasedeDatos.rawQuery("SELECT *FROM cliente WHERE id="+id,null);

        if (cursorCliente.moveToFirst()){

                vendedor =new Vendedor();
                vendedor.setId(cursorCliente.getInt(0));
                vendedor.setNombreCompleto(cursorCliente.getString(1));
                vendedor.setCelular(cursorCliente.getString(2));
                vendedor.setUbicacion(cursorCliente.getString(3));


        }
        cursorCliente.close();
        return vendedor;
    }

}
