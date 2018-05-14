package com.example.hector.agendarecuperacion;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AnyadirTarea.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AnyadirTarea#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AnyadirTarea extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    EditText fecha, hora, descripcion, nombre;
    Button cancelar, ok, btnfecha, btnhora;
    MyDBAdapter bbdd;
    DatePickerDialog datepick;
    TimePickerDialog timepick;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AnyadirTarea() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AnyadirTarea.
     */
    // TODO: Rename and change types and number of parameters
    public static AnyadirTarea newInstance(String param1, String param2) {
        AnyadirTarea fragment = new AnyadirTarea();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_anyadir_tarea, container, false);
        fecha = v.findViewById(R.id.EditTextFecha);
        hora = v.findViewById(R.id.EditTextHora);
        descripcion = v.findViewById(R.id.EditTextDescripcion);
        cancelar = v.findViewById(R.id.BotonCancelar);
        ok = v.findViewById(R.id.BotonConfirmar);
        btnfecha= v.findViewById(R.id.botonFecha);
        btnhora = v.findViewById(R.id.botonHora);
        bbdd = new MyDBAdapter(getContext());
        // Creamos DatePicker
        datepick = new DatePickerDialog(getContext());
        datepick.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // Accion cuando ha seleccionado una fecha
                fecha.setText(dayOfMonth + "/" + month + "/" + year);
            }
        });

         timepick = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                // Preuab
                hora.setText(hourOfDay + ":" + minute);
            }
        }, 0,0,false);




        btnfecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datepick.show();
            }

        });
        btnhora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timepick.show();
            }

        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardar();
            }
        });
        return v;
    }

    private void guardar() {
        String textofecha = fecha.getText().toString();
        String textohora= hora.getText().toString();
        String textodescripcion= descripcion.getText().toString();
        String nombre="";

    Evento objeto = new Evento(nombre, textofecha, textohora, textodescripcion);
        bbdd.guardarTarea(objeto);
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
