package com.example.myapp.Negocio;

import android.content.Context;

import com.example.myapp.Dato.DdetalleNotaVenta;
import com.example.myapp.Dato.DetalleNotaVenta;
import com.example.myapp.Dato.Producto;

import java.util.ArrayList;

public class NdetalleNotaVenta {
    private DdetalleNotaVenta dc;
    Context context;

    public NdetalleNotaVenta(Context context1){
        this.context=context1;
        this.dc=new DdetalleNotaVenta(this.context);
    }
    public  long agregar(int idcotizacion, int idproducto, int cantidad ){
        long i;
        if (idcotizacion>0&&idproducto>0&&cantidad>0){
            i=dc.agregar(idcotizacion,idproducto,cantidad);
        }else{  i=0; }
        return i;

    }

    public ArrayList<Producto> getlistaProdCotizacion(int idcot){
        return dc.getlistaProdCotizacion( idcot);
    }


    ///prueba de arrays

    public ArrayList<DetalleNotaVenta> getlistaDetalleCotizacion(int idcot){
        return dc.getlistaDetalleCotizacion( idcot);
    }

    public DetalleNotaVenta getDetalle(int id){
        return dc.getDetalle(id);
    }

    public boolean editDetalleProducto(int id, int cantidad){
        return dc.editDetalleProducto(id,cantidad);
    }

    public boolean elimDetalleProducto(int id){
        return dc.elimDetalleProducto(id);
    }


    public ArrayList<DetalleNotaVenta> sumarProducto(int idcot){
        return dc.sumarProducto( idcot);
    }


}
