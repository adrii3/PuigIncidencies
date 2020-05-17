package com.example.puigincidencies;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SubirIncidenciaFragment extends Fragment {

    private Spinner spinnerIncidencias;
    private Spinner spinnerClase;
    private EditText descripcion;
    private Button subirIncidencia;
    String seleccionIncidencia, seleccionClase, textoDescripcion;

   DatabaseReference dbReferencia;


    public SubirIncidenciaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_subir_incidencia, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayAdapter<CharSequence> adapterSpinnerClases = ArrayAdapter.createFromResource(getContext(), R.array.valores_spinner_clase, android.R.layout.simple_spinner_item);
        spinnerClase = view.findViewById(R.id.spinnner_clase);
        spinnerClase.setAdapter(adapterSpinnerClases);
        spinnerClase.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                seleccionClase = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        ArrayAdapter<CharSequence> adapterSpinnerIncidencias = ArrayAdapter.createFromResource(getContext(), R.array.valores_array, android.R.layout.simple_spinner_item);
        spinnerIncidencias = view.findViewById(R.id.spinner_incidencia);
        spinnerIncidencias.setAdapter(adapterSpinnerIncidencias);
        spinnerIncidencias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                seleccionIncidencia = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        descripcion = view.findViewById(R.id.descripcion_et_subir_incidencia);
        textoDescripcion = descripcion.getText().toString();

        dbReferencia = FirebaseDatabase.getInstance().getReference();
        subirIncidencia = view.findViewById(R.id.btn_subir_incidencias);
        subirIncidencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> infoIncidencia = new HashMap<>();
                infoIncidencia.put("nombre clase", spinnerClase );
                infoIncidencia.put("tipoIncidencia", spinnerIncidencias);
                infoIncidencia.put("descripcion",textoDescripcion);
                dbReferencia.child("Incidencia").push().setValue(infoIncidencia);
            }
        });
        //FALTA AÑADIR LA CAMARA PARA SUBIR A LA BASE DE DATOS TODA LA INFO DE LA INCIDENCIA, AL SUBIRLO A LA BASE DE DATOS PETA

    }
}
