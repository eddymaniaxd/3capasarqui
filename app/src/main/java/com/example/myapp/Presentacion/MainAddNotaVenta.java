package com.example.myapp.Presentacion;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapp.Dato.Vendedor;
import com.example.myapp.Negocio.Nvendedor;
import com.example.myapp.Negocio.Nnotaventa;
import com.example.myapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

public class MainAddNotaVenta extends AppCompatActivity {

  private EditText fecha;
  private int idcliente, anio,mes, dia;
  private Nnotaventa ncot;
  private Nvendedor ncl;
  Spinner spinercliente;
  Button  btndate,btnguardarCotizacion;
  private ArrayList<Vendedor>listaArrayClinte= new ArrayList<>();
    FloatingActionButton floatVolverAddCotizacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_add_notaventa);
        floatVolverAddCotizacion=findViewById(R.id.floatVolverAddCottizacion);
        fecha=findViewById(R.id.txtfechaCotizacion);
        btndate=findViewById(R.id.btnCalendario);
        btnguardarCotizacion=findViewById(R.id.btnguardarCotizacion);
        spinercliente=findViewById(R.id.spCliente);
        llenarSpinerCliente();


          //       AGREGAR  NOTA DE VENTA ///////////////////////////

        btnguardarCotizacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ncot=new Nnotaventa(MainAddNotaVenta.this);
                long  i=ncot.agregar(fecha.getText().toString(),idcliente);
                if (i>0){
                    Toast.makeText(MainAddNotaVenta.this,"Dato insertado correctamente de ID="+i,Toast.LENGTH_SHORT).show();
                    fecha.setText("");
                    volverAlista();
                }else{
                    Toast.makeText(MainAddNotaVenta.this,"Debes Rellanar Los Campos OBLIGATORIO ",Toast.LENGTH_SHORT).show();

                }
            }
        });
        floatVolverAddCotizacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volverAlista();
            }
        });

        btndate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               AbrirCalendario();
            }
        });
    }


    private void AbrirCalendario() {
        Calendar cal=Calendar.getInstance();
        anio=cal.get(Calendar.YEAR);
        mes=cal.get(Calendar.MONTH);
        dia=cal.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd=new DatePickerDialog(MainAddNotaVenta.this,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOFMonth) {
             String date=year+"/"+month+"/"+dayOFMonth;
                fecha.setText(date);
            }
        },anio,mes,dia);
        dpd.show();
    }


    private void llenarSpinerCliente() {
        ncl = new Nvendedor(MainAddNotaVenta.this);
        listaArrayClinte = ncl.getlistaCliente();
        ArrayAdapter<Vendedor> adaptador = new  ArrayAdapter<Vendedor>
                (this, android.R.layout.simple_spinner_item, listaArrayClinte);
        adaptador.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinercliente.setAdapter(adaptador);

        spinercliente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idcliente=((Vendedor)adapterView.getSelectedItem()).getId();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void volverAlista(){
        Intent intent = new Intent(MainAddNotaVenta.this, MainActivityNotaVenta.class);
        startActivity(intent);
    }
}