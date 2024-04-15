package com.example.myapp.Presentacion;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import android.widget.Toast;

import com.example.myapp.Dato.Categoria;
import com.example.myapp.Negocio.Ncategoria;
import com.example.myapp.Negocio.Nproducto;
import com.example.myapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;



public class MainAddProducto extends AppCompatActivity {

    private ArrayList<com.example.myapp.Dato.Categoria> listaArrayCategoria = new ArrayList<>();
    ImageView imgprod;
    private EditText nombreProducto,descripcion,precio;
    private Button btnaddimage,btnguardarProducto;
    Spinner spinerproducto;
    private Ncategoria nc;
    private Nproducto ncproducto;
    int idcat;
    private ActivityResultLauncher<String> imagePickerLauncher;
    private Uri selectImgUri;
    private String saveImgPath;
   FloatingActionButton floatVolverAddProducto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_add_producto);
       imgprod = findViewById(R.id.imageDeProducto);
       btnaddimage = findViewById(R.id.btnaddimage);

        floatVolverAddProducto=findViewById(R.id.floatVolverAddProducto);
        btnguardarProducto=findViewById(R.id.btnguardarProducto);
        spinerproducto = findViewById(R.id.spProducto);

        nombreProducto=findViewById(R.id.txtnombreproducto);
        descripcion=findViewById(R.id.txtdescripcion);
        precio=findViewById(R.id.txtprecio);

        llenarSpiner();

                 //AGREGRA PRODUCTO//////////////
        btnguardarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ncproducto=new Nproducto(MainAddProducto.this);
               int  price=(!precio.getText().toString().equals(""))?Integer.parseInt(precio.getText().toString()):0;
                long  i=ncproducto.agregar(nombreProducto.getText().toString(),price,descripcion.getText().toString(), imagenByte(imgprod),idcat);
                if (i>0){
                    Toast.makeText(MainAddProducto.this,"Dato insertado correctamente de ID="+i,Toast.LENGTH_SHORT).show();
                    limpiar();
                    volverAlista();
                }else{
                    Toast.makeText(MainAddProducto.this,"Debes Rellanar Los Campos OBLIGATORIO (Nombre, Precio)",Toast.LENGTH_SHORT).show();

                }
            }
        });
      btnaddimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eligiriImagenProducto();
            }
        });



        floatVolverAddProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(), MainActivityProducto.class);
                startActivity(i);
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 99 && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                 imgprod.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public static byte[] imagenByte(ImageView image) {

        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Bitmap redimensionada=Bitmap.createScaledBitmap(bitmap,300,400,true);
        redimensionada.compress(Bitmap.CompressFormat.PNG, 70, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    public void eligiriImagenProducto( ) {
        try {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, 99);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void llenarSpiner(){
        nc = new Ncategoria(MainAddProducto.this);
        listaArrayCategoria = nc.getlistaCategoria();
        ArrayAdapter<Categoria> adaptador = new  ArrayAdapter<Categoria>
                (this, android.R.layout.simple_spinner_item, listaArrayCategoria);
        adaptador.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinerproducto.setAdapter(adaptador);

        spinerproducto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idcat=((Categoria)adapterView.getSelectedItem()).getId();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }



    private void limpiar(){
        nombreProducto.setText("");
        precio.setText("");
        descripcion.setText("");
    }
    private void volverAlista(){
        Intent intent = new Intent(MainAddProducto.this, MainActivityProducto.class);
        startActivity(intent);
    }

}