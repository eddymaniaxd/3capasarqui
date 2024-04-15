package com.example.myapp.Presentacion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapp.Dato.Vendedor;
import com.example.myapp.Negocio.Nvendedor;
import com.example.myapp.R;

public class MainUpDelVendedor extends AppCompatActivity {


    private EditText clienteup,celularup,ubicacionup;
    private Button btneliminar, btnupdate;
    int id=0;
    boolean correcto=false;
    private Nvendedor nc;
    private Vendedor vendedor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_up_del_vendedor);

        clienteup=findViewById(R.id.txtnombupcliente);
        celularup=findViewById(R.id.txtupcelular);
        ubicacionup=findViewById(R.id.txtupubicacion);

        btnupdate=findViewById(R.id.btnGuarCliente);
        btneliminar=findViewById(R.id.btnElimCliente);

        if (savedInstanceState==null){
            Bundle extras=getIntent().getExtras();
            if(extras==null){
                id=Integer.parseInt(null);
            }else{
                id=extras.getInt("ID");
            }
        }else{
            id=(int)savedInstanceState.getSerializable("ID");
        }
        nc= new Nvendedor(MainUpDelVendedor.this);
        vendedor =nc.getVendedor(id);
        if (vendedor !=null){
            clienteup.setText(vendedor.getNombreCompleto());
            celularup.setText(vendedor.getCelular());
            ubicacionup.setText(vendedor.getUbicacion());
        }

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correcto= nc.editVendedor(id,clienteup.getText().toString(),celularup.getText().toString(),ubicacionup.getText().toString());
                if (correcto){
                    Toast.makeText(MainUpDelVendedor.this,"REGISTRO MODIFICADO DE ID="+id,Toast.LENGTH_SHORT).show();
                    volverAlista();
                }else{
                    Toast.makeText(MainUpDelVendedor.this,"ERROR AL MODIFICAR",Toast.LENGTH_SHORT).show();

                }
            }
        });


        btneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainUpDelVendedor.this);
                builder.setMessage("¿Desea ELIMINAR Esta CATEGORIA?").setPositiveButton("Si",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (nc.elimVendedor(id)){
                                    volverAlista();
                                }
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();

            }
        });
    }

    private void volverAlista(){
        Intent intent = new Intent(MainUpDelVendedor.this, MainActivityVendedor.class);
        startActivity(intent);
    }
}