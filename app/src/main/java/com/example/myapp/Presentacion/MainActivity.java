package com.example.myapp.Presentacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.myapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imgcategoria= findViewById(R.id.imgcategoria);
        ImageView imgcliente= findViewById(R.id.imgcliente);
        ImageView imgproducto= findViewById(R.id.imgproducto);
        ImageView imgcotización=findViewById(R.id.imgcotización);
       // ImageView imgrepartidor=findViewById(R.id.imgrepartidor);
        //ImageView imgpedido=findViewById(R.id.imgPedido);


        imgcategoria.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(), MainActivityCategoria.class);
                startActivity(i);
            }
        });

       imgcliente.setOnClickListener(new View.OnClickListener(){
            @Override
           public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(), MainActivityVendedor.class);
                startActivity(i);
            }
        });

        imgproducto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(), MainActivityProducto.class);
                startActivity(i);
            }
        });
        imgcotización.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(), MainActivityNotaVenta.class);
                startActivity(i);
            }
        });



        //imgpedido.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //   public void onClick(View view) {
           //     Intent i=new Intent(getApplicationContext(), MainActivityPedido.class);
            //    startActivity(i);
            //}
        //});
    }
}