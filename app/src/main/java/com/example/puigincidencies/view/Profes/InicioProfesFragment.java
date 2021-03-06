package com.example.puigincidencies.view.Profes;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
public class InicioProfesFragment extends AppFragment {

    private FloatingActionButton subirIncidenciaProfes;
    private RecyclerView recyclerView;
    private IncidenciaAdapterProfes incidenciaAdapterProfes;
    private Button aceptadas, noAceptadas , solucionadas, noSolucionadas;



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


        subirIncidenciaProfes = view.findViewById(R.id.floating_subir_incidencia_profes);
        subirIncidenciaProfes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.subirIncidenciaFragment);
            }
        });
        recyclerView =  view.findViewById(R.id.recycler_view_inicio_profes);
        montarRecycler();

        aceptadas = view.findViewById(R.id.boton_incidencias_aceptadas);
        aceptadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.inicioAceptadasFragment);
            }
        });

        noAceptadas = view.findViewById(R.id.boton_incidencias_no_aceptadas);
        noAceptadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.inicioNoAceptadasFragment);
            }
        });

        solucionadas = view.findViewById(R.id.boton_incidencias_solucionadas);
        solucionadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.inicioSolucionadasFragment);
            }
        });

        noSolucionadas = view.findViewById(R.id.boton_incidencias_no_solucionadas);
        noSolucionadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.inicioNoSolucionadasFragment);
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
        Query query = db.collection("Incidencia");
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
