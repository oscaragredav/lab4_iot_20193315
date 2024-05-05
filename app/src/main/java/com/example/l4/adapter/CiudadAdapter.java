package com.example.l4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.l4.R;
import com.example.l4.entity.Ciudad;

import java.util.List;

public class CiudadAdapter extends RecyclerView.Adapter<CiudadAdapter.CiudadViewHolder>{
    Context context;
    List<Ciudad> list;


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Ciudad> getList() {
        return list;
    }

    public void setList(List<Ciudad> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public CiudadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler,parent,false);
        return new CiudadViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CiudadViewHolder holder, int position) {
        Ciudad ciudad1 = list.get(position);
        holder.ciudad = ciudad1;

        TextView name = holder.itemView.findViewById(R.id.name);
        name.setText(ciudad1.getName());
        TextView lat = holder.itemView.findViewById(R.id.lat);
        lat.setText("Lat: "+ ciudad1.getLat());
        TextView lon = holder.itemView.findViewById(R.id.lon);
        lon.setText("Lon: "+ ciudad1.getLon());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CiudadViewHolder extends RecyclerView.ViewHolder{
        Ciudad ciudad;
        public CiudadViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
