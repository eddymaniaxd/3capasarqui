package com.example.myapp.Negocio;

import android.content.Context;

import com.example.myapp.Dato.Vendedor;
import com.example.myapp.Dato.Cotizacion;
import com.example.myapp.Dato.Dnotaventa;

import java.util.ArrayList;

public class Nnotaventa {

    private Dnotaventa dc;
    Context context;

    public Nnotaventa(Context context1){
        this.context=context1;
        this.dc=new Dnotaventa(this.context);
    }
    public  long agregar(String fecha,int idcliente ){
        long i;
        if (!fecha.equals("")&&idcliente!=0){
            i=dc.agregar(fecha,idcliente);
        }else{  i=0; }
        return i;

    }

    public ArrayList<Cotizacion> getlistaCotizacion(){
        return dc.getlistaCotizacion();
    }
    public Vendedor getClienteCotizacion(int idcot){
        return dc.getClienteCotizacion(idcot);
    }
}
