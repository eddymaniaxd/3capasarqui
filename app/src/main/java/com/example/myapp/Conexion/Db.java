package com.example.myapp.Conexion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Db extends SQLiteOpenHelper {
    private static  final int Db_Version =1;
    private static  final String Db_Name ="parcialArqui.db";

    public Db(@Nullable  Context context){
        super(context, Db_Name,  null, Db_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {
        BaseDeDatos.execSQL("create table categoria(id integer primary key autoincrement,nombre text not null)");
        BaseDeDatos.execSQL("create table repartidor(id integer primary key autoincrement,nombreCompleto text not null,celular text not null )");
        BaseDeDatos.execSQL("create table cliente(id integer primary key autoincrement,nombreCompleto text not null,celular text not null, ubicacion text )");
        BaseDeDatos.execSQL("create table producto(id integer primary key autoincrement,nombreProducto text not null,precio int not null,descripcion text, image BLOB, idcat integer, foreign key(idcat)references categoria(id))");
        BaseDeDatos.execSQL("create table cotizacion(id integer primary key autoincrement,fecha text not null,idcliente integer,foreign key(idcliente)references cliente(id))");
        BaseDeDatos.execSQL("create table detallecotizacion(id integer primary key autoincrement,idcotizacion integer,idproducto integer,cantidad int,foreign key(idcotizacion)references cotizacion(id),foreign key(idproducto)references producto(id))");



    }

    @Override
    public void onUpgrade(SQLiteDatabase BaseDeDatos, int i, int i1) {
        BaseDeDatos.execSQL("DROP TABLE IF EXISTS categoria");
        BaseDeDatos.execSQL("DROP TABLE IF EXISTS cliente");
        BaseDeDatos.execSQL("DROP TABLE IF EXISTS repartidor");
        BaseDeDatos.execSQL("DROP TABLE IF EXISTS producto");
        BaseDeDatos.execSQL("DROP TABLE IF EXISTS cotizacion");
        BaseDeDatos.execSQL("DROP TABLE IF EXISTS detallecotizacion");
        //onCreate(BaseDeDatos);
    }

}
