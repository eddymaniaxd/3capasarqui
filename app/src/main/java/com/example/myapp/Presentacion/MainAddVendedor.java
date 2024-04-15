package com.example.myapp.Presentacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapp.Negocio.Nvendedor;
import com.example.myapp.R;

public class MainAddVendedor extends AppCompatActivity {


    private EditText nombrecliente,celular,ubicacion;
    private Button btnguardarvendedor,btnvolvervendedor;
    private boolean correcto;
    private Nvendedor nvendedor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_add_vendedor);


        nombrecliente=findViewById(R.id.txtnombrecliente);
        celular=findViewById(R.id.txtcelular);
        ubicacion=findViewById(R.id.txtubicacion);
        btnguardarvendedor=  findViewById(R.id.btnguardarVendedor);
        btnvolvervendedor=  findViewById(R.id.btnvolverVendedor);

        btnguardarvendedor.setOnClickListener(new View.OnClickListener() {

            // AGREGAR VENDEDOR
            @Override
            public void onClick(View view) {
                nvendedor =new Nvendedor(MainAddVendedor.this);
                Toast.makeText(MainAddVendedor.this,nombrecliente.getText().toString(),Toast.LENGTH_SHORT).show();
                long  i= nvendedor.agregar(nombrecliente.getText().toString(),celular.getText().toString(),ubicacion.getText().toString());

                if (i>0){
                    Toast.makeText(MainAddVendedor.this,"Dato insertado correctamente de ID="+i,Toast.LENGTH_SHORT).show();
                    limpiar();
                    volverAlista();
                }else{
                    Toast.makeText(MainAddVendedor.this,"Debes Rellanar Los Campos OBLIGATORIO (NombreCompleto,Celular)",Toast.LENGTH_SHORT).show();

                }
            }
        });

        btnvolvervendedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(), MainActivityVendedor.class);
                startActivity(i);
            }
        });

    }

    public void limpiar(){
        nombrecliente.setText("");
        celular.setText("");
        ubicacion.setText("");
    }
    private void volverAlista(){
        Intent intent = new Intent(MainAddVendedor.this, MainActivityVendedor.class);
        startActivity(intent);
    }
}