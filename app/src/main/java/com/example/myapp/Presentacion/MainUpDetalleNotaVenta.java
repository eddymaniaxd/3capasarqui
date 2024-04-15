package com.example.myapp.Presentacion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp.Dato.DetalleNotaVenta;
import com.example.myapp.Dato.Producto;
import com.example.myapp.Negocio.NdetalleNotaVenta;
import com.example.myapp.Negocio.Nproducto;
import com.example.myapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainUpDetalleNotaVenta extends AppCompatActivity {

   int id=0;
    private NdetalleNotaVenta ndc;
    private DetalleNotaVenta detalle;
    private Producto producto;
    private Nproducto np;
    boolean correcto=false;
    ImageView imgDetalleProd;
    TextView precioUpdetalle,nombre_producto_detalle;
    EditText cantUPdetalle;
    Button btnelimDetalle,btnguardarDetalle;
    FloatingActionButton floatVolverUPDetalle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_up_detalle_notaventa);
        imgDetalleProd=findViewById(R.id.imgDetalleProd);
        btnelimDetalle=findViewById(R.id.btnelimDetalle);
        btnguardarDetalle=findViewById(R.id.btnguardarDetalle);
        cantUPdetalle=findViewById(R.id.cantUPdetalle);
        precioUpdetalle=findViewById(R.id.precioUpdetalle);
        nombre_producto_detalle=findViewById(R.id.nombre_producto_detalle);

        floatVolverUPDetalle=findViewById(R.id.floatVolverUPDetalle);

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
        ndc= new NdetalleNotaVenta(MainUpDetalleNotaVenta.this);
        detalle=ndc.getDetalle(id);
        int idprod=detalle.getIdproducto();
        np= new Nproducto(MainUpDetalleNotaVenta.this);
        producto=np.getProducto(idprod);
        if (producto!=null){
            byte[] foodImage = producto.getImagen();
            Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
            imgDetalleProd.setImageBitmap(bitmap);
            nombre_producto_detalle.setText(producto.getNombreProducto());
            precioUpdetalle.setText(String.valueOf(producto.getPrecio()));
            cantUPdetalle.setText(String.valueOf(detalle.getCantidad()));

        }
        btnguardarDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correcto= ndc.editDetalleProducto(id,Integer.parseInt(cantUPdetalle.getText().toString()));
                if (correcto){
                    Toast.makeText(MainUpDetalleNotaVenta.this,"REGISTRO MODIFICADO DE ID="+id,Toast.LENGTH_SHORT).show();
                    volverAlista();
                }else{
                    Toast.makeText(MainUpDetalleNotaVenta.this,"ERROR AL MODIFICAR",Toast.LENGTH_SHORT).show();

                }
            }
        });

        btnelimDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainUpDetalleNotaVenta.this);
                builder.setMessage("¿Desea ELIMINAR Este PRODUCTO de su cotización?").setPositiveButton("Si",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (ndc.elimDetalleProducto(id)){
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

        floatVolverUPDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volverAlista();
            }
        });
    }

    private void volverAlista(){
        Intent intent = new Intent(MainUpDetalleNotaVenta.this, MainActivityNotaVenta.class);
        startActivity(intent);
    }
}