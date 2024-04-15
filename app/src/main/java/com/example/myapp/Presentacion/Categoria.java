package com.example.myapp.Presentacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapp.Negocio.Ncategoria;
import com.example.myapp.R;

public class Categoria extends AppCompatActivity {

    private EditText nombrecategoria;
    private Button btnguardarCategoria,btnvolverCategoria;
    private boolean correcto;
    private Ncategoria ncategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);

        nombrecategoria = findViewById(R.id.txtnombrecategoria);

        btnguardarCategoria=  findViewById(R.id.btnguardarCategoria);
        btnvolverCategoria=  findViewById(R.id.btnvolverCategoria);

                    //-----AGREGAR UNA  CATEGORIA ---------

        btnguardarCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ncategoria=new Ncategoria(Categoria.this);
                long  i=ncategoria.agregar(nombrecategoria.getText().toString());
                if (i>0){
                    Toast.makeText(Categoria.this,"Dato insertado correctamente de ID="+i,Toast.LENGTH_SHORT).show();
                    limpiar();
                    volverAlista();
                }else{
                    Toast.makeText(Categoria.this,"Debes Rellanar Todo Los Campos",Toast.LENGTH_SHORT).show();

                }
            }
        });
        //-----VOLVER AL LISTADO DE CATEGORIA ---------
          btnvolverCategoria.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(getApplicationContext(), MainActivityCategoria.class);
                    startActivity(i);
                }
            });
    }
    //-----LIMPIAR TEXT DE  CATEGORIA ---------
      public void limpiar(){
        nombrecategoria.setText("");
    }
    private void volverAlista(){
        Intent intent = new Intent(Categoria.this, MainActivityCategoria.class);
        startActivity(intent);
    }

}