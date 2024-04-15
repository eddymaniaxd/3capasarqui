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

import com.example.myapp.Dato.Producto;
import com.example.myapp.Presentacion.MainUpDelProducto;
import com.example.myapp.R;

import java.util.ArrayList;

public class ListAdapterProducto extends RecyclerView.Adapter<ListAdapterProducto.ProductoViewHolder>  {
    private ArrayList<Producto> listaProducto;
    private Context context;

    public ListAdapterProducto(ArrayList<Producto> listaProducto){
        this.listaProducto=listaProducto;
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_producto,null,false);
        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapterProducto.ProductoViewHolder holder, int position) {
        byte[] foodImage = listaProducto.get(position).getImagen();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
         holder.imgProducto.setImageBitmap(bitmap);
        holder.nombre_Producto.setText(listaProducto.get(position).getNombreProducto());
        holder.precio_Producto.setText(String.valueOf(listaProducto.get(position).getPrecio()));
    }

    @Override
    public int getItemCount() {
        return listaProducto.size();
    }

    public class ProductoViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProducto;
        TextView nombre_Producto,precio_Producto;
        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProducto=itemView.findViewById(R.id.imgProducto);
            nombre_Producto=itemView.findViewById(R.id.nombre_Producto);
            precio_Producto=itemView.findViewById(R.id.precio_Producto);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context=view.getContext();
                    Intent intent=new Intent(context, MainUpDelProducto.class);
                    intent.putExtra("ID",listaProducto.get(getAdapterPosition()).getId());
                    context.startActivity(intent);

                }
            });
        }
    }
}



