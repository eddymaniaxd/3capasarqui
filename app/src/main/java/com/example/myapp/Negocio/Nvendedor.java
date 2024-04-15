package com.example.myapp.Negocio;

import android.content.Context;

import com.example.myapp.Dato.Vendedor;
import com.example.myapp.Dato.Dvendedor;

import java.util.ArrayList;

public class Nvendedor {

    private Dvendedor dc;
    Context context;

    public Nvendedor(Context context1){
        this.context=context1;
        this.dc=new Dvendedor(this.context);
    }

    public  long agregar(String nombreCompleto, String celular , String ubicacion ){
        long i;
        if (!nombreCompleto.equals("")&&!celular.equals("")){
            i=dc.agregar(nombreCompleto,celular,ubicacion);
        }else{  i=0; }
        return i;

    }

    public boolean editVendedor(int id,String nombreCompleto, String celular , String ubicacion){
        boolean  correcto=false;
        if (!nombreCompleto.equals("")&&!celular.equals("")){
            correcto=dc.editVendedor(id,nombreCompleto,celular,ubicacion);
        }
        return correcto;
    }

    public boolean elimVendedor(int id) {
        boolean correcto;
        return correcto = dc.elimVendedor(id);
    }

    public Vendedor getVendedor(int id){
        return dc.getCliente(id);
    }

    public ArrayList<Vendedor> getlistaCliente(){
        return dc.getlistaVendedor();
    }
}
