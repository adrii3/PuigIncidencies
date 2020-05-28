package com.example.puigincidencies.view.Profes;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.puigincidencies.AppFragment;
import com.example.puigincidencies.R;
import com.example.puigincidencies.model.Incidencia;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
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

        RecyclerView incidenciasRecyclerView = view.findViewById(R.id.recycler_view_inicio);
        FirestoreRecyclerOptions<Incidencia> options = new FirestoreRecyclerOptions.Builder<Incidencia>()
                .setQuery(setQuery(), Incidencia.class)
                .setLifecycleOwner(this)
                .build();

        incidenciasRecyclerView.setAdapter(new IncidenciasAdapter(options));

    }

    class IncidenciasAdapter extends FirestoreRecyclerAdapter<Incidencia, IncidenciasAdapter.IncidenciaViewHolder> {
        IncidenciasAdapter(@NonNull FirestoreRecyclerOptions<Incidencia> options) { super(options); }


        @NonNull
        @Override
        public IncidenciaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new IncidenciaViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_incidencia, parent, false));
        }


        @Override
        protected void onBindViewHolder(@NonNull IncidenciaViewHolder holder, int position, @NonNull final Incidencia incidencia) {
            final String incidenciaKey = getSnapshots().getSnapshot(position).getId();

            holder.textViewLugar.setText(incidencia.lugar);
            holder.textViewLugar.setText(incidencia.descripcion);
        }


        class IncidenciaViewHolder extends RecyclerView.ViewHolder{

            TextView textViewLugar, textViewDesc;


            IncidenciaViewHolder(@NonNull View itemView) {
                super(itemView);

                textViewLugar = itemView.findViewById(R.id.viewholder_lugar);
                textViewDesc = itemView.findViewById(R.id.viewholder_descripcion);
            }
        }
    }

    Query setQuery(){
        return db.collection("Incidencia").limit(50);
    }
}
