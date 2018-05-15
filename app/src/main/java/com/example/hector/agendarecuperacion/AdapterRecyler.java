package com.example.hector.agendarecuperacion;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class AdapterRecyler extends RecyclerView.Adapter<AdapterRecyler.DiasTareasHolder> {

    ArrayList<Evento> eventos;

    public AdapterRecyler(ArrayList<Evento> eventos){
        this.eventos = eventos;
    }


    @Override
    public DiasTareasHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.linea_recylcer, parent, false);
        DiasTareasHolder holder = new DiasTareasHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final DiasTareasHolder holder, int position) {
        Evento evento =  eventos.get(position);
        holder.textViewNombre.setText(evento.getNombre());
        holder.textViewDiaTarea.setText(evento.getFecha());
        holder.textViewHoraTarea.setText(evento.getHora());
        holder.textViewDescripcionTarea.setText(evento.getDescripcion());
    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }

    public static class DiasTareasHolder extends RecyclerView.ViewHolder{

        TextView textViewNombre, textViewDiaTarea, textViewHoraTarea, textViewDescripcionTarea;
        public DiasTareasHolder(View itemView) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.textview_nombrerecy);
            textViewDiaTarea = itemView.findViewById(R.id.textview_fecharecy);
            textViewHoraTarea = itemView.findViewById(R.id.textview_horarecy);
            textViewDescripcionTarea = itemView.findViewById(R.id.textview_descripcionrecy);
        }
    }

}
