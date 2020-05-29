package com.example.puigincidencies.view.Profes;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.puigincidencies.AppFragment;
import com.example.puigincidencies.IncidenciaAdapterProfes;
import com.example.puigincidencies.R;
import com.example.puigincidencies.model.Incidencia;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.Query;


/**
 * A simple {@link Fragment} subclass.
 */
public class InicioNoSolucionadasFragment extends AppFragment {
    private FloatingActionButton subirIncidenciaProfes;
    private RecyclerView recyclerView;
    private IncidenciaAdapterProfes incidenciaAdapterProfes;
    private Button general, aceptadas , noAceptadas, solucionadas;

    public InicioNoSolucionadasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inicio_aceptadas, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        subirIncidenciaProfes = view.findViewById(R.id.floating_subir_incidencia_ns);
        subirIncidenciaProfes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.subirIncidenciaFragment);
            }
        });
        recyclerView =  view.findViewById(R.id.recycler_view_inicio_ns);
        montarRecycler();

        general = view.findViewById(R.id.boton_incidencias_generales_ns);
        general.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.inicioProfesFragment);
            }
        });

        aceptadas = view.findViewById(R.id.boton_incidencias_aceptadas_ns);
        aceptadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.inicioAceptadasFragment);
            }
        });

        noAceptadas = view.findViewById(R.id.boton_incidencias_no_aceptadas_ns);
        noAceptadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.inicioNoAceptadasFragment);
            }
        });

        solucionadas = view.findViewById(R.id.boton_incidencias_solucionadas_ns);
        solucionadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.inicioSolucionadasFragment);
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        incidenciaAdapterProfes.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        incidenciaAdapterProfes.stopListening();
    }

    public void montarRecycler(){

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Query query = db.collection("Incidencia").whereEqualTo("incidenciaSolucionada",false);
        FirestoreRecyclerOptions<Incidencia> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<Incidencia>()
                .setQuery(query, Incidencia.class).build();

        incidenciaAdapterProfes = new IncidenciaAdapterProfes(firestoreRecyclerOptions);
        incidenciaAdapterProfes.notifyDataSetChanged();
        recyclerView.setAdapter(incidenciaAdapterProfes);

        incidenciaAdapterProfes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.incidenciaProfesFragment);
            }
        });
    }
}