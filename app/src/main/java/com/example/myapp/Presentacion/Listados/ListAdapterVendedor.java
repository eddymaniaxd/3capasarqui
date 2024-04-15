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

import com.example.myapp.Dato.Vendedor;
import com.example.myapp.Presentacion.MainUpDelVendedor;
import com.example.myapp.R;

import java.util.ArrayList;

public class ListAdapterVendedor extends RecyclerView.Adapter<ListAdapterVendedor.ClienteViewHolder>  {

    private ArrayList<Vendedor> listaVendedor;



    public ListAdapterVendedor(ArrayList<Vendedor> listaVendedor){
        // this.context=context;
        this.listaVendedor = listaVendedor;

    }
    @NonNull
    @Override
    public ClienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cliente,null,false);
        return new ClienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteViewHolder holder, int position) {
        holder.nombre_Cliente.setText(listaVendedor.get(position).getNombreCompleto());
        holder.celular_Cliente.setText(listaVendedor.get(position).getCelular());
    }

    @Override
    public int getItemCount() {
        return listaVendedor.size();
    }

    public class ClienteViewHolder extends RecyclerView.ViewHolder {
        ImageView iconImage;
        TextView nombre_Cliente,celular_Cliente;
        public ClienteViewHolder(@NonNull View itemView) {
            super(itemView);
            iconImage=itemView.findViewById(R.id.iconImage);
            nombre_Cliente=itemView.findViewById(R.id.nombre_Cliente);
            celular_Cliente=itemView.findViewById(R.id.celular_Cliente);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context=view.getContext();
                    Intent intent=new Intent(context, MainUpDelVendedor.class);
                    intent.putExtra("ID", listaVendedor.get(getAdapterPosition()).getId());
                    context.startActivity(intent);

                }
            });

        }
    }
}
