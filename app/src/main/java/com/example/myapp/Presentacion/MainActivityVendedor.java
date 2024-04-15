package com.example.myapp.Presentacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapp.Presentacion.Listados.ListAdapterVendedor;
import com.example.myapp.Dato.Vendedor;
import com.example.myapp.Negocio.Nvendedor;
import com.example.myapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivityVendedor extends AppCompatActivity {
    private ArrayList<Vendedor> listaArrayVendedor =new ArrayList<>();
    RecyclerView listaCliente;
    private Nvendedor nc;
    FloatingActionButton floatCliente,floatVolverClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_vendedor);

        listaCliente=findViewById(R.id.recyCliente);
        listaCliente.setLayoutManager(new LinearLayoutManager(MainActivityVendedor.this));


        floatCliente=findViewById(R.id.floatCliente);
        floatVolverClient=findViewById(R.id.floatVolverClient);

        listarCliente();

        floatCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityVendedor.this, MainAddVendedor.class);
                startActivity(intent);
            }
        });

        floatVolverClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityVendedor.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


    public  void listarCliente(){
        nc=new Nvendedor(MainActivityVendedor.this);
        listaArrayVendedor =new ArrayList<>();
        listaArrayVendedor =nc.getlistaCliente();
        ListAdapterVendedor adaptor=new ListAdapterVendedor(listaArrayVendedor);
        listaCliente.setAdapter(adaptor);
    }
}