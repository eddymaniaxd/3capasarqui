package com.example.myapp.Presentacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapp.Presentacion.Listados.ListAdapterNotaVenta;
import com.example.myapp.Dato.Cotizacion;
import com.example.myapp.Negocio.Nnotaventa;
import com.example.myapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivityNotaVenta extends AppCompatActivity {
    private ArrayList<Cotizacion> listaArrayCotizacion=new ArrayList<>();
    RecyclerView listaCotizacion;
    private Nnotaventa ncot;
    FloatingActionButton floatCotizacion,floatVolverCotizacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_notaventa);

        listaCotizacion=findViewById(R.id.recyCotizacion);
        listaCotizacion.setLayoutManager(new LinearLayoutManager(MainActivityNotaVenta.this));

        floatCotizacion=findViewById(R.id.floatCotizacion);
        floatVolverCotizacion=findViewById(R.id.floatVolverCotizacion);

        listarCotizacion();
        floatCotizacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityNotaVenta.this, MainAddNotaVenta.class);
                startActivity(intent);
            }
        });

        floatVolverCotizacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityNotaVenta.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void listarCotizacion() {
        ncot=new Nnotaventa(MainActivityNotaVenta.this);
        listaArrayCotizacion=new ArrayList<>();
        listaArrayCotizacion=ncot.getlistaCotizacion();
        ListAdapterNotaVenta adaptor=new ListAdapterNotaVenta(listaArrayCotizacion);
        listaCotizacion.setAdapter(adaptor);
    }
}