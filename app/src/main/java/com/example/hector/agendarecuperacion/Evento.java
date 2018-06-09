package com.example.hector.agendarecuperacion;

import android.util.Log;

public class Evento {
         // Variables
        private Integer id;
        private String nombre;
        private String fecha;
        private String hora;
        private String descripcion;

        public Evento(Integer idT ,String nombre, String fecha, String hora, String descripcion) {
            this.id=idT;

            this.nombre = nombre;
            this.fecha = fecha;
            this.hora = hora;
            this.descripcion = descripcion;
        }
        public int getId(){
            try{
                return id.intValue();
            }catch (NullPointerException npe){
                Log.d("Hector", "L'id de tasca es null");
                return -0;
            }
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getFecha() {
            return fecha;
        }

        public void setFecha(String fecha) {
            this.fecha = fecha;
        }

        public String getHora() {
            return hora;
        }

        public void setHora(String hora) {
            this.hora = hora;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }
}


