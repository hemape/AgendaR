package com.example.hector.agendarecuperacion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.EventLog;
import android.util.Log;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class MyDBAdapter {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "AGENDA";
    private static final String DATABASE_TABLE = "TAREAS";

    private static final String TITLE = "title";
    private static final String YEAR = "year";

    private static final String DATABASE_CREATE = "CREATE TABLE "+DATABASE_TABLE+" (_id integer primary key autoincrement, nombretarea text, fecha_t text, hora_t text, descripcion_t text);";
    private static final String DATABASE_DROP = "DROP TABLE IF EXISTS "+DATABASE_TABLE+";";

    // Contexto de la aplicación que usa la base de datos
    private final Context context;
    // Clase SQLiteOpenHelper para crear/actualizar la base de datos
    private MyDbHelper dbHelper;
    // Instancia de la base de datos
    private SQLiteDatabase db;

    public MyDBAdapter(Context c){
        context = c;
        dbHelper = new MyDbHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        open();
    }

    public void open(){

        try{
            db = dbHelper.getWritableDatabase();
        }catch(SQLiteException e){
            db = dbHelper.getReadableDatabase();
        }

    }

    public String guardarTarea(Evento obj) {
        String mensaje = "";
        ContentValues newValues = new ContentValues();
        newValues.put("nombretarea", obj.getNombre());
        newValues.put("fecha_t", obj.getFecha());
        newValues.put("hora_t", obj.getHora());
        newValues.put("descripcion_t", obj.getDescripcion());

        try {
            db.insertOrThrow("TAREAS", null, newValues);
            mensaje = "Tarea ingresada correctamente";
        } catch (SQLException e) {
            mensaje = "No se ha podido ingresar la tarea";
        }
        db.close();
        return mensaje;
    }


    // Devuelve en un array list todos los eventos
    public ArrayList llenar_Tarea() {
        ArrayList<Evento> lista = new ArrayList<Evento>();

        String q = "SELECT * FROM TAREAS";
        Cursor registros = db.rawQuery(q, null);

        if (registros.moveToFirst()) {
            do {
                // Preparamos valores
                String nombre = registros.getString(1);
                String fecha = registros.getString(2);
                String hora = registros.getString(3);
                String descripcion = registros.getString(4);

                // Creamos objeto
                Evento obj = new Evento(nombre, fecha, hora, descripcion);

                // Guardamos objeto a la lista
                lista.add(obj);
            } while (registros.moveToNext());
        }
        return lista;

    }
//Devuelve solo una
    public Evento devuelveTarea(int id) {
        String q = "SELECT * FROM TAREAS WHERE _id = '"+id+"'";
        Cursor registros = db.rawQuery(q, null);

        if (registros.moveToFirst()) {

            // Preparamos valores
            String nombre = registros.getString(1);
            String fecha = registros.getString(2);
            String hora = registros.getString(3);
            String descripcion = registros.getString(4);

            // Creamos objeto
            Evento obj = new Evento(nombre, fecha, hora, descripcion);

            // Guardamos objeto a la lista
            return obj;

        }else{
            Log.d("#TEMP", "No se ha encontado el registro Nº " + id);
            return null;
        }
    }

    public void eliminaTarea(int id){
        db.delete("TAREAS", "id="+id, null);
    }


    private static class MyDbHelper extends SQLiteOpenHelper {

        public MyDbHelper (Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
            super(context,name,factory,version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DATABASE_DROP);
            onCreate(db);
        }

    }
}
