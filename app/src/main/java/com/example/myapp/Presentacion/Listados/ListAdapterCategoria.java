package com.example.myapp.Presentacion.Listados;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapp.Presentacion.MainUpDelCategoria;
import com.example.myapp.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.Dato.Categoria;

import java.util.ArrayList;

public class ListAdapterCategoria extends RecyclerView.Adapter<ListAdapterCategoria.CategoriaViewHolder> {

    private ArrayList<Categoria> listaCategoria;
    private Context context;


    public ListAdapterCategoria(ArrayList<Categoria> listaCategoria){
        // this.context=context;
        this.listaCategoria=listaCategoria;

    }

    @NonNull
    @Override
    public CategoriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_categoria,null,false);
        return new CategoriaViewHolder(view);
    }


    @Override
    public void onBindViewHolder(CategoriaViewHolder holder, final int position) {

        holder.nombrecategoria.setText(listaCategoria.get(position).getNombre());
    }

    @Override
    public int getItemCount() {
        return listaCategoria.size();
    }

    public  class CategoriaViewHolder  extends RecyclerView.ViewHolder{
        TextView nombrecategoria;

        public CategoriaViewHolder(@NonNull View itemView) {
            super(itemView);
            nombrecategoria=itemView.findViewById(R.id.nombre_categoria);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context=view.getContext();
                    Intent intent=new Intent(context,MainUpDelCategoria.class);
                    intent.putExtra("ID",listaCategoria.get(getAdapterPosition()).getId());
                    context.startActivity(intent);

                }
            });

        }


    }

}
