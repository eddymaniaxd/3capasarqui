package com.example.myapp.Presentacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapp.Presentacion.Listados.ListAdapterCategoria;
import com.example.myapp.Negocio.Ncategoria;
import com.example.myapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivityCategoria extends AppCompatActivity  {
    private ArrayList<com.example.myapp.Dato.Categoria> listaArrayCategoria = new ArrayList<>();
    RecyclerView listaCategoria;
    private Ncategoria nc;


    FloatingActionButton floatCatagregar,floatVolverCat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_categoria);

        listaCategoria=findViewById(R.id.recyCategoria);
        listaCategoria.setLayoutManager(new LinearLayoutManager(MainActivityCategoria.this));

        floatCatagregar=findViewById(R.id.floatCatagregar);
        floatVolverCat=findViewById(R.id.floatVolverCat);

        listarCategoria();

        floatCatagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityCategoria.this, Categoria.class);
                startActivity(intent);
            }
        });


        floatVolverCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityCategoria.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

        public  void listarCategoria(){
            nc=new Ncategoria(MainActivityCategoria.this);
            listaArrayCategoria=new ArrayList<>();
            listaArrayCategoria=nc.getlistaCategoria();

            ListAdapterCategoria adaptor=new ListAdapterCategoria(listaArrayCategoria);
            listaCategoria.setAdapter(adaptor);
        }


}