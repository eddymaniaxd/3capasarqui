package com.example.myapp.Dato;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.myapp.Conexion.Db;

import java.util.ArrayList;
import java.util.List;

public class Dcategoria extends Db {
    private SQLiteDatabase BasedeDatos;

    Context context;

    public Dcategoria(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    //-----INSERTANDO A LA BASE DE DATOS ---------
    public long agregar(String nombre1) {
        Db dbHelper = new Db(context);
        BasedeDatos = dbHelper.getWritableDatabase();
        long i = 0;
        try {
            ContentValues registro = new ContentValues();
            registro.put("nombre", nombre1);
            i = BasedeDatos.insert("categoria", null, registro);
            BasedeDatos.close();
        } catch (Exception e) {
            e.toString();
        }
        return i;
    }

//-----MOSTRAR LISTA DE CATEGORIA DE LA BASE DE DATOS ---------

    public ArrayList<Categoria> getlistaCategoria() {
        Db dbHelper = new Db(context);
        BasedeDatos = dbHelper.getWritableDatabase();
        ArrayList<Categoria> listaCategoria = new ArrayList<>();

        Categoria categoria = null;
        Cursor cursorCategoria = null;

        cursorCategoria = BasedeDatos.rawQuery("SELECT *FROM categoria", null);

        if (cursorCategoria.moveToFirst()) {
            do {
                categoria = new Categoria();
                categoria.setId(cursorCategoria.getInt(0));
                categoria.setNombre(cursorCategoria.getString(1));
                listaCategoria.add(categoria);
            } while (cursorCategoria.moveToNext());
        }
        cursorCategoria.close();
        return listaCategoria;
    }

    //-----EDICION DE CATEGORIA DE LA BASE DE DATOS ---------
    public boolean editCategoria(int id, String nombre1) {
        Db dbHelper = new Db(context);
        BasedeDatos = dbHelper.getWritableDatabase();
        boolean correcto = false;
        try {
            BasedeDatos.execSQL("UPDATE categoria SET nombre='" + nombre1 + "'WHERE id='" + id + "'");
            correcto = true;
        } catch (Exception e) {
            e.toString();
            correcto = false;
        } finally {
            BasedeDatos.close();
        }
        return correcto = true;
    }

    //-----ElIMINAR LA CATEGORIA DE LA BASE DE DATOS ---------

    public boolean elimCategoria(int id) {
        Db dbHelper = new Db(context);
        BasedeDatos = dbHelper.getWritableDatabase();
        boolean correcto = false;
        try {
            BasedeDatos.execSQL("DELETE FROM categoria WHERE id='" + id + "'");
            correcto = true;
        } catch (Exception e) {
            e.toString();
            correcto = false;
        } finally {
            BasedeDatos.close();
        }
        return correcto = true;
    }


    public Categoria getCategoria(int id) {
        Db dbHelper = new Db(context);
        BasedeDatos = dbHelper.getWritableDatabase();

        Categoria categoria = null;
        Cursor cursorCategoria = null;

        cursorCategoria = BasedeDatos.rawQuery("SELECT *FROM categoria WHERE id=" + id, null);

        if (cursorCategoria.moveToFirst()) {

            categoria = new Categoria();
            categoria.setId(cursorCategoria.getInt(0));
            categoria.setNombre(cursorCategoria.getString(1));


        }
        cursorCategoria.close();
        return categoria;
    }


    public Cursor mostrarCategoria() {

        try {
            Db dbHelper = new Db(context);
            BasedeDatos = dbHelper.getWritableDatabase();
            Cursor fila = BasedeDatos.rawQuery("SELECT *FROM categoria", null);
            if (fila.moveToFirst()) {
                return fila;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }


    }

}
