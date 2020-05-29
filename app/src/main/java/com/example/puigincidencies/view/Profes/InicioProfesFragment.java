package com.example.puigincidencies.view.Profes;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.puigincidencies.AppFragment;
import com.example.puigincidencies.IncidenciaAdapter;
import com.example.puigincidencies.R;
import com.example.puigincidencies.model.IncidenciaRecyclerInicio;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.Query;


/**
 * A simple {@link Fragment} subclass.
 */
public class InicioProfesFragment extends AppFragment {

    FloatingActionButton subirIncidenciaProfes;
    Spinner filtrarIncidencias;
    String filtro;
    RecyclerView recyclerView;
    IncidenciaAdapter incidenciaAdapter;


    public InicioProfesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inicio_profes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView =  view.findViewById(R.id.recycler_view_inicio);

        ArrayAdapter<CharSequence> adapterSpinnerFiltro = ArrayAdapter.createFromResource(getContext(), R.array.valores_spinner_filtro_inicio, android.R.layout.simple_spinner_item);
        filtrarIncidencias = view.findViewById(R.id.spinner_inicio_profes);
        filtrarIncidencias.setAdapter(adapterSpinnerFiltro);
        filtrarIncidencias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filtro = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        subirIncidenciaProfes = view.findViewById(R.id.floating_subir_incidencia_profes);
        subirIncidenciaProfes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.subirIncidenciaFragment);
            }
        });




        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Query query = db.collection("Incidencia");
        FirestoreRecyclerOptions<IncidenciaRecyclerInicio> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<IncidenciaRecyclerInicio>()
                .setQuery(query, IncidenciaRecyclerInicio.class).build();

        incidenciaAdapter = new IncidenciaAdapter(firestoreRecyclerOptions);
        incidenciaAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(incidenciaAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        incidenciaAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        incidenciaAdapter.stopListening();
    }
    Query setQuery(){
        return db.collection("Incidencia");
    }
}
