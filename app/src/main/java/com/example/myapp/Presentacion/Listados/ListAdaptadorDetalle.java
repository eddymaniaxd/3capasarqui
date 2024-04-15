package com.example.myapp.Presentacion.Listados;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.Dato.DetalleNotaVenta;
import com.example.myapp.Dato.Producto;
import com.example.myapp.Presentacion.MainUpDetalleNotaVenta;
import com.example.myapp.R;

import java.util.ArrayList;

public class ListAdaptadorDetalle extends RecyclerView.Adapter<ListAdaptadorDetalle.DetalleViewHolder>{
    private ArrayList<Producto> listaProducto;
    private ArrayList<DetalleNotaVenta> listaArrayDetalleCot;

    public ListAdaptadorDetalle(ArrayList<Producto> listaProducto,ArrayList<DetalleNotaVenta> listaArrayDetalleCot){
     this.listaProducto= listaProducto;
     this.listaArrayDetalleCot=listaArrayDetalleCot;
    }

    @NonNull
    @Override
    public DetalleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_datalle_notaventa,null,false);
        return new DetalleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdaptadorDetalle.DetalleViewHolder holder, int position) {
       //holder.nombre_producto_cotizacion.setText(listaDetalleCotizacion.get(position).getNombreProducto());

        byte[] foodImage = listaProducto.get(position).getImagen();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
        holder.imgdeProducto.setImageBitmap(bitmap);

        holder.nombre_producto_cotizacion.setText(listaProducto.get(position).getNombreProducto());
        holder.precio_letra.setText("Precio:");
        holder.precio_producto_cotizacion.setText(String.valueOf(listaProducto.get(position).getPrecio()));
        holder.cantidad_letra.setText("Cantidad:");
        holder.cantidad_cotizacion.setText(String.valueOf(listaArrayDetalleCot.get(position).getCantidad()));


    }


    @Override
    public int getItemCount() {
        return listaProducto.size();
    }

    public class DetalleViewHolder extends RecyclerView.ViewHolder  {
        ImageView imgdeProducto;
        TextView nombre_producto_cotizacion,precio_producto_cotizacion,cantidad_cotizacion,cantidad_letra,precio_letra;

        public DetalleViewHolder(@NonNull View itemView) {
            super(itemView);
            imgdeProducto=itemView.findViewById(R.id.imgdeProducto);
            nombre_producto_cotizacion=itemView.findViewById(R.id.nombre_producto_cotizacion);
            precio_producto_cotizacion=itemView.findViewById(R.id.precio_producto_cotizacion);
            cantidad_cotizacion=itemView.findViewById(R.id.cantidad_cotizacion);
            precio_letra=itemView.findViewById(R.id.precio_letra);
            cantidad_letra=itemView.findViewById(R.id.cantidad_letra);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context=view.getContext();
                    Intent intent=new Intent(context, MainUpDetalleNotaVenta.class);
                    intent.putExtra("ID",listaArrayDetalleCot.get(getAdapterPosition()).getId());
                    context.startActivity(intent);

                }
            });

        }
    }
}
