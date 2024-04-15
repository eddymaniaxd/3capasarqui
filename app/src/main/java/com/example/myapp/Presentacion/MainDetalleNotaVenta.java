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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp.Presentacion.Listados.ListAdaptadorDetalle;
import com.example.myapp.Dato.DetalleNotaVenta;
import com.example.myapp.Dato.Producto;
import com.example.myapp.Negocio.Nnotaventa;
import com.example.myapp.Negocio.NdetalleNotaVenta;
import com.example.myapp.Negocio.Nproducto;
import com.example.myapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainDetalleNotaVenta extends AppCompatActivity {
    private static final int REQUEST_CODE_WRITE_EXTERNAL_STORAGE_PERMISSION =1 ;
    Spinner spinerprod;
    Button btnaddCotProd,btncotizar;
    EditText txtcantProd;
    TextView cliente_cotizacion;
    private int idprod,idcot=0;
    RecyclerView listaProducto;
    private Nproducto np;
    private Nnotaventa ncot;
    private NdetalleNotaVenta ndt;
    private ArrayList<Producto> listaArrayProducto= new ArrayList<>();
    private ArrayList<DetalleNotaVenta> listaArrayDetalleCot= new ArrayList<>();
    FloatingActionButton floatVolverProdCot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_detalle_notaventa);
        btnaddCotProd=findViewById(R.id.btnaddCotProd);
        floatVolverProdCot=findViewById(R.id.floatVolverProdCot);
        txtcantProd=findViewById(R.id.txtcantProd);
        btncotizar=findViewById(R.id.btncotizar);
        spinerprod=findViewById(R.id.spinerprod);
        cliente_cotizacion=findViewById(R.id.cliente_cotizacion);


        listaProducto=findViewById(R.id.recyCotProd);
        listaProducto.setLayoutManager(new LinearLayoutManager(MainDetalleNotaVenta.this));


        if (savedInstanceState==null){
            Bundle extras=getIntent().getExtras();
            if(extras==null){
                idcot=Integer.parseInt(null);
            }else{
                idcot=extras.getInt("ID");
            }
        }else{
            idcot=(int)savedInstanceState.getSerializable("ID");
        }
         ncot=new Nnotaventa(MainDetalleNotaVenta.this);
        String phone=  ncot.getClienteCotizacion(idcot).getCelular();
        String nombreCliente=ncot.getClienteCotizacion(idcot).getNombreCompleto();
        cliente_cotizacion.setText(nombreCliente);
        listarProducto();
        llenarSpinerProducto();
        floatVolverProdCot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VolveraListarCotizacion();
            }
        });
        btnaddCotProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               DetalledeCotizacion();
            }
        });


      /*  btncotizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_VIEW);
                String uri="whatsapp://send?phone=591"+phone+"&text=Hola mundo";
                sendIntent.setData(Uri.parse(uri));
                startActivity(sendIntent);
            }
        });*/

        btncotizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                compartirPDFProductos();
            }
        });
    }



    private void VolveraListarCotizacion() {
        Intent intent = new Intent(MainDetalleNotaVenta.this, MainActivityNotaVenta.class);
        startActivity(intent);
    }

    private void llenarSpinerProducto() {
        np= new Nproducto(MainDetalleNotaVenta.this);
        listaArrayProducto = np.getlistaProducto();
        ArrayAdapter<Producto> adaptador = new  ArrayAdapter<Producto>
                (this, android.R.layout.simple_spinner_item, listaArrayProducto);
        adaptador.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinerprod.setAdapter(adaptador);

        spinerprod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idprod=((Producto)adapterView.getSelectedItem()).getId();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void DetalledeCotizacion() {
        ndt=new NdetalleNotaVenta(MainDetalleNotaVenta.this);
        int  cantidad=(!txtcantProd.getText().toString().equals(""))?Integer.parseInt(txtcantProd.getText().toString()):0;
        long  i=ndt.agregar(idcot,idprod,cantidad);
        if (i>0){
            Toast.makeText(MainDetalleNotaVenta.this,"Producto Agregado Correctamente ID="+i,Toast.LENGTH_SHORT).show();
            txtcantProd.setText("");
           listarProducto();
        }else{
            Toast.makeText(MainDetalleNotaVenta.this,"Datos obligatorio Producto y Cantidad",Toast.LENGTH_SHORT).show();

        }
    }


    private void listarProducto() {
        ndt=new NdetalleNotaVenta(MainDetalleNotaVenta.this);
        listaArrayProducto=new ArrayList<>();
        listaArrayDetalleCot=new ArrayList<>();
        listaArrayProducto=ndt.getlistaProdCotizacion(idcot);
        listaArrayDetalleCot=ndt.getlistaDetalleCotizacion(idcot);
        ListAdaptadorDetalle adaptor=new ListAdaptadorDetalle(listaArrayProducto,listaArrayDetalleCot);
        listaProducto.setAdapter(adaptor);
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
            Paragraph parrafoInicial = new Paragraph("Cotización  de Productos", fuente);
            parrafoInicial.setAlignment(Element.ALIGN_CENTER);
            document.add( parrafoInicial );
            ArrayList<DetalleNotaVenta> detalles =ndt.sumarProducto(idcot);
            PdfPTable tabla = new PdfPTable(4);
            for (DetalleNotaVenta detalle : detalles) {
                int idprod=detalle.getIdproducto();
                Producto producto= np.getProducto(idprod);
                tabla.addCell("Nombre: " + producto.getNombreProducto());
                tabla.addCell("Precio Bs: " + Integer.toString(producto.getPrecio()));
                tabla.addCell("Cantidad: " + Integer.toString(detalle.getCantidad()));
                tabla.addCell("Total: " + Integer.toString(detalle.getCantidad()*producto.getPrecio()));
            }
            document.add(tabla);
            Paragraph parrafoFinal = new Paragraph("Monto Total Bs: "+Integer.toString(150), fuente);
            parrafoFinal.setAlignment(Element.ALIGN_CENTER);
            document.add( parrafoFinal );
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
        startActivity(Intent.createChooser(compartir, "Cotizacion de Productos"));
    }

    public void compartirPDFProductos() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(MainDetalleNotaVenta.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                crearPDF();
            } else {
                ActivityCompat.requestPermissions(
                        MainDetalleNotaVenta.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CODE_WRITE_EXTERNAL_STORAGE_PERMISSION);
            }
        } else {
            crearPDF();
        }
    }


}