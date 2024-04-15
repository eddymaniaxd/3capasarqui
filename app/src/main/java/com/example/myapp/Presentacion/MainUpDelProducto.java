package com.example.myapp.Presentacion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp.Dato.Categoria;
import com.example.myapp.Dato.Producto;
import com.example.myapp.Negocio.Ncategoria;
import com.example.myapp.Negocio.Nproducto;
import com.example.myapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainUpDelProducto extends AppCompatActivity {
    int id=0,idcat;
    private Nproducto np;
    private Producto producto;
    boolean correcto=false;
    Spinner spUProducto;
    private Ncategoria nc,ncp;
    private ArrayList<Categoria> listaArrayCategoria = new ArrayList<>();
     EditText nombreProdUp,precioup,descripcionup;
     TextView tvcategoria;
    private Button btnElimProducto, btnupdateProd;

    FloatingActionButton floatVolverProd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_up_del_producto);

        nombreProdUp=findViewById(R.id.txtnombreUProducto);
        precioup=findViewById(R.id.txtUPprecio);
        descripcionup=findViewById(R.id.txtUPdescripcion);

        floatVolverProd=findViewById(R.id.floatVolverUProducto);
        btnupdateProd=findViewById(R.id.btnguardarUProducto);
        btnElimProducto=findViewById(R.id.btnElimProducto);
        tvcategoria=findViewById(R.id.tvcategoria);
        spUProducto = findViewById(R.id.spUProducto);

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
        np= new Nproducto(MainUpDelProducto.this);
        producto=np.getProducto(id);
        int idcatg=np.getIdCategoria(id);
        ncp = new Ncategoria(MainUpDelProducto.this);
        if (producto!=null){
            nombreProdUp.setText(producto.getNombreProducto());
            precioup.setText(String.valueOf(producto.getPrecio()));
            descripcionup.setText(producto.getDescripcion());
            tvcategoria.setText(ncp.getCategoria(idcatg).getNombre());
        }
        llenarSpiner();

        btnupdateProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correcto= np.editProducto(id,nombreProdUp.getText().toString(),Integer.parseInt(precioup.getText().toString()),descripcionup.getText().toString(),idcat);
                if (correcto){
                    Toast.makeText(MainUpDelProducto.this,"REGISTRO MODIFICADO DE ID="+id,Toast.LENGTH_SHORT).show();
                    volverAlista();
                }else{
                    Toast.makeText(MainUpDelProducto.this,"ERROR AL MODIFICAR",Toast.LENGTH_SHORT).show();

                }
            }
        });
        btnElimProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainUpDelProducto.this);
                builder.setMessage("¿Desea ELIMINAR Este PRODUCTO?").setPositiveButton("Si",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (np.elimProducto(id)){
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


        floatVolverProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volverAlista();
            }
        });

    }

    private void llenarSpiner(){
        nc = new Ncategoria(MainUpDelProducto.this);
        listaArrayCategoria = nc.getlistaCategoria();
        ArrayAdapter<Categoria> adaptador = new  ArrayAdapter<Categoria>
                (this, android.R.layout.simple_spinner_item, listaArrayCategoria);
        adaptador.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spUProducto.setAdapter(adaptador);

        spUProducto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idcat=((Categoria)adapterView.getSelectedItem()).getId();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void volverAlista(){
        Intent intent = new Intent(MainUpDelProducto.this, MainActivityProducto.class);
        startActivity(intent);
    }
}