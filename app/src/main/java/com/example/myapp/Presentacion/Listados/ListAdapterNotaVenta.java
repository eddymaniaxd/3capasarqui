package com.example.myapp.Presentacion.Listados;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.Dato.Cotizacion;
import com.example.myapp.Presentacion.MainDetalleNotaVenta;
import com.example.myapp.R;

import java.util.ArrayList;

public class ListAdapterNotaVenta extends RecyclerView.Adapter<ListAdapterNotaVenta.CotizacionViewHolder> {
    private ArrayList<Cotizacion> listaCotizacion;

    public ListAdapterNotaVenta(ArrayList<Cotizacion> listaCotizacion){
        this.listaCotizacion=listaCotizacion;
    }

    @NonNull
    @Override
    public CotizacionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_notaventa,null,false);
        return new CotizacionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapterNotaVenta.CotizacionViewHolder holder, int position) {

       holder.nombre_cliente_cotizacion.setText(String.valueOf(listaCotizacion.get(position).getId()));
        holder.fecha_cotizacion.setText(listaCotizacion.get(position).getFecha());


    }

    @Override
    public int getItemCount() {
        return listaCotizacion.size();
    }

    public class CotizacionViewHolder extends RecyclerView.ViewHolder {
        ImageView imgcotizacion;
        TextView nombre_cliente_cotizacion,fecha_cotizacion;
        public CotizacionViewHolder(@NonNull View itemView) {
            super(itemView);
            imgcotizacion=itemView.findViewById(R.id.imgcotizacion);
            nombre_cliente_cotizacion=itemView.findViewById(R.id.nombre_cliente_cotizacion);
            fecha_cotizacion=itemView.findViewById(R.id.fecha_cotizacion);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context=view.getContext();
                    Intent intent=new Intent(context, MainDetalleNotaVenta.class);
                    intent.putExtra("ID",listaCotizacion.get(getAdapterPosition()).getId());
                    context.startActivity(intent);

                }
            });
        }
    }
}
