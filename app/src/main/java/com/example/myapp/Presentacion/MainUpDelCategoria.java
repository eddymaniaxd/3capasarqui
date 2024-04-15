package com.example.myapp.Presentacion;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.Negocio.Ncategoria;
import com.example.myapp.R;

public class MainUpDelCategoria extends AppCompatActivity {
   private EditText categoriaup;
   private Button btneliminar, btnupdate;
   int id=0;
   boolean correcto=false;
   private Ncategoria nc;
   private com.example.myapp.Dato.Categoria categoria;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_up_del_categoria);


        categoriaup=findViewById(R.id.txtupcategoria);
        btneliminar=findViewById(R.id.btnElimCategoria);
        btnupdate=findViewById(R.id.btnUPCategoria);

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
        nc= new Ncategoria(MainUpDelCategoria.this);
        categoria=nc.getCategoria(id);
         if (categoria!=null){
             categoriaup.setText(categoria.getNombre());
         }

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             correcto= nc.editCategoria(id,categoriaup.getText().toString()) ;
              if (correcto){
                  Toast.makeText(MainUpDelCategoria.this,"REGISTRO MODIFICADO DE ID="+id,Toast.LENGTH_SHORT).show();
                  volverAlista();
              }else{
                  Toast.makeText(MainUpDelCategoria.this,"ERROR AL MODIFICAR",Toast.LENGTH_SHORT).show();

              }
            }
        });


        btneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainUpDelCategoria.this);
                builder.setMessage("¿Desea ELIMINAR Esta CATEGORIA?").setPositiveButton("Si",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (nc.elimCategoria(id)){
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
        Intent intent = new Intent(MainUpDelCategoria.this, MainActivityCategoria.class);
        startActivity(intent);
    }
}