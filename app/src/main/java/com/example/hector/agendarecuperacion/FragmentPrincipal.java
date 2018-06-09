package com.example.hector.agendarecuperacion;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the

 * to handle interaction events.
 * Use the {@link FragmentPrincipal#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPrincipal extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    RecyclerView rv;
    ArrayList<Evento> eventos;
    MyDBAdapter bbdd;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private AdapterRecyler.ComunicaAdaptadorAmbFragmentPrincipal mListener;


    public FragmentPrincipal() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentPrincipal.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentPrincipal newInstance(String param1, String param2) {
        FragmentPrincipal fragment = new FragmentPrincipal();
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
        View v = inflater.inflate(R.layout.fragment_fragment_principal, container, false);

        // Elementos Vista
        rv = (RecyclerView) v.findViewById(R.id.recyclerPrincipal);

        // Añadimos valores prueba
        eventos = new ArrayList<Evento>();
        // Cargamos BBDD
        bbdd = new MyDBAdapter(getContext());

        // Cargamos valores BBDD
        eventos = bbdd.llenar_Tarea();

        // Usar un administrador para LinearLayout
        LinearLayoutManager lManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(lManager);

        // Generamos adaptador y conectamos
        AdapterRecyler.ComunicaAdaptadorAmbFragmentPrincipal comunicador = new AdapterRecyler.ComunicaAdaptadorAmbFragmentPrincipal() {
            @Override
            public void UsuariHaTriatUnaTasca(Integer idTasca) {
                mListener.carregaDetallTascaFragment(idTasca);
            }
        };
        AdapterRecyler ad = new AdapterRecyler(eventos, comunicador);
        rv.setAdapter(ad);

        return v;
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


    public interface ComunicaFragmentPrincipalAmbActivity {
        void carregaDetallTascaFragment(Integer id);
    }


}
