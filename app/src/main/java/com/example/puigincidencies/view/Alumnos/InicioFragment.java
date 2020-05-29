package com.example.puigincidencies.view.Alumnos;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.puigincidencies.AppFragment;
import com.example.puigincidencies.IncidenciaAdapter;
import com.example.puigincidencies.R;
import com.example.puigincidencies.model.Incidencia;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.Query;


/**
 * A simple {@link Fragment} subclass.
 */
public class InicioFragment extends AppFragment {

    FloatingActionButton floatingSubirIncidencia;
    RecyclerView recyclerAlumno;
    private IncidenciaAdapter incidenciaAdapter;

    public InicioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inicio, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        floatingSubirIncidencia = view.findViewById(R.id.floating_subir_incidencia);
        floatingSubirIncidencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.subirIncidenciaFragment);
            }
        });

        recyclerAlumno = view.findViewById(R.id.recycler_view_inicio);
        montarRecycler();

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

    public void montarRecycler(){
        recyclerAlumno.setLayoutManager(new LinearLayoutManager(getContext()));
        Query query = db.collection("Incidencia").whereEqualTo("aceptarIncidencia",true);
        FirestoreRecyclerOptions<Incidencia> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<Incidencia>()
                .setQuery(query, Incidencia.class).build();

        incidenciaAdapter = new IncidenciaAdapter(firestoreRecyclerOptions);
        incidenciaAdapter.notifyDataSetChanged();
        recyclerAlumno.setAdapter(incidenciaAdapter);

    }
}
