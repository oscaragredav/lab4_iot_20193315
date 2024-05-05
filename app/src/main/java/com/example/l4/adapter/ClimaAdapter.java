package com.example.l4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.l4.R;
import com.example.l4.entity.Clima;

public class ClimaAdapter extends RecyclerView.Adapter<ClimaAdapter.ClimaViewHolder>{
    Context context;
    Clima clima;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Clima getClima() {
        return clima;
    }

    public void setClima(Clima clima) {
        this.clima = clima;
    }

    @NonNull
    @Override
    public ClimaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_clima_recycler,parent,false);
        return new ClimaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClimaViewHolder holder, int position) {
        Clima clima1 = clima;
        holder.clima = clima1;
//        Toast.makeText(getContext(), clima1.getName(), Toast.LENGTH_SHORT).show();
        TextView name = holder.itemView.findViewById(R.id.name);
        name.setText(clima1.getName());

        TextView min = holder.itemView.findViewById(R.id.min);
        min.setText(clima1.getMain().getTemp_min());

        TextView max = holder.itemView.findViewById(R.id.max);
        max.setText(clima1.getMain().getTemp_max());

        TextView temp = holder.itemView.findViewById(R.id.temp);
        temp.setText(clima1.getMain().getTemp());


        TextView wind = holder.itemView.findViewById(R.id.viento);
        wind.setText(obtenerDireccionViento(Double.parseDouble(clima1.getWind().getDeg())));
//        wind.setText(clima1.getWind().getDeg());
    }

    //método obtenido de ChatGpt mediante el sgte prom:
    // cuando realizo este método, clima1.getWind().getDirection()  me da un string con valores desde 0 a 360. Cómo puedo implementar un método para que en el wind.setText se implemente un string del tipo "norte", "sur, etc dependiendo del valor de clima1.getWind().getDirection()
    // se modificó lo obtenido parseando los strings a double para poder realizar la comparación
    private String obtenerDireccionViento(double grados) {
        if (grados >= 337.5 || grados < 22.5) {
            return "Norte";
        } else if (grados >= 22.5 && grados < 67.5) {
            return "Noreste";
        } else if (grados >= 67.5 && grados < 112.5) {
            return "Este";
        } else if (grados >= 112.5 && grados < 157.5) {
            return "Sureste";
        } else if (grados >= 157.5 && grados < 202.5) {
            return "Sur";
        } else if (grados >= 202.5 && grados < 247.5) {
            return "Suroeste";
        } else if (grados >= 247.5 && grados < 292.5) {
            return "Oeste";
        } else {
            return "Noroeste";
        }
    }

//modificado con ayuda de ChatGpt con la prompt
    //cómo puedo modificar el método getItemCount() si es que ya no recibiré una lista, sino solo la instancia de un objeto
    @Override
    public int getItemCount() {
        if (clima != null) {
            return 1;
        } else {
            return 0;
        }
    }

    public class ClimaViewHolder extends RecyclerView.ViewHolder{
        Clima clima;
        public ClimaViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
