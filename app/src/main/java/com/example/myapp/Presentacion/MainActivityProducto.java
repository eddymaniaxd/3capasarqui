package com.example.myapp.Presentacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.myapp.Presentacion.Listados.ListAdapterProducto;
import com.example.myapp.Dato.Categoria;
import com.example.myapp.Dato.Producto;
import com.example.myapp.Negocio.Ncategoria;
import com.example.myapp.Negocio.Nproducto;
import com.example.myapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivityProducto extends AppCompatActivity {
    private static final int REQUEST_CODE_WRITE_EXTERNAL_STORAGE_PERMISSION =1 ;
    private ArrayList<Producto> listaArrayProducto=new ArrayList<>();
    RecyclerView listaProducto;
    private Nproducto nc;
    private Ncategoria nct;
    private Categoria  categoria;
    FloatingActionButton floatProducto,floatVolverProducto,floatCatalogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_producto);

        listaProducto=findViewById(R.id.recyProducto);
        listaProducto.setLayoutManager(new LinearLayoutManager(MainActivityProducto.this));

        floatProducto=findViewById(R.id.floatProducto);
        floatVolverProducto=findViewById(R.id.floatVolverProducto);
        floatCatalogo=findViewById(R.id.floatCatalogo);
        listarProducto();

        floatProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityProducto.this, MainAddProducto.class);
                startActivity(intent);
            }
        });

        floatVolverProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityProducto.this, MainActivity.class);
                startActivity(intent);
            }
        });
        floatCatalogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                compartirPDFProductos();
            }
        });
    }

    public  void listarProducto(){
        nc=new Nproducto(MainActivityProducto.this);
        listaArrayProducto=new ArrayList<>();
        listaArrayProducto=nc.getlistaProducto();
        ListAdapterProducto adaptor=new ListAdapterProducto(listaArrayProducto);
        listaProducto.setAdapter(adaptor);
    }


    public void compartirPDFProductos() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(MainActivityProducto.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
               crearPDF();
            } else {
                ActivityCompat.requestPermissions(
                        MainActivityProducto.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CODE_WRITE_EXTERNAL_STORAGE_PERMISSION);
            }
        } else {
           crearPDF();
        }
    }



    private void crearPDF() {
        Document document = new Document();
        String dirreccion = getApplicationContext().getExternalFilesDir(null).toString() + "/catalogo.pdf";
        File file = new File(dirreccion);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            PdfWriter.getInstance(document, fileOutputStream);
            document.open();
            Font fuente = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
            Paragraph parrafoInicial = new Paragraph("Catálogo de Productos", fuente);
            parrafoInicial.setAlignment(Element.ALIGN_CENTER);
            document.add( parrafoInicial );
                ArrayList<Producto> productos = nc.getlistaProductoCategoria();
                    PdfPTable tabla = new PdfPTable(3);
                    for (Producto producto : productos) {
                        Image imagen = Image.getInstance(producto.getImagen());
                        imagen.scaleAbsolute(20, 10);
                        tabla.addCell( imagen );
                        tabla.addCell("Nombre: " + producto.getNombreProducto());
                        tabla.addCell("Precio Bs: " + Integer.toString(producto.getPrecio()));
                       // tabla.addCell("Categoria: " + categoria.getNombre());
                    }
                    document.add(tabla);
                    document.add(new Paragraph(" "));
                    tabla.deleteBodyRows();
            document.close();
            fileOutputStream.close();
            sharePDF(file);
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void sharePDF(File file) {
        Uri url = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".fileprovider", file);
        Intent compartir = new Intent(Intent.ACTION_SEND);
        compartir.setType("application/pdf");
        compartir.putExtra(Intent.EXTRA_STREAM, url);
        compartir.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(Intent.createChooser(compartir, "Catálogo de Productos"));
    }
}