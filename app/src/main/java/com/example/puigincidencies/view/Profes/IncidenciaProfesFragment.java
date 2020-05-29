package com.example.puigincidencies.view.Profes;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.puigincidencies.AppFragment;
import com.example.puigincidencies.R;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;


/**
 * A simple {@link Fragment} subclass.
 */
public class IncidenciaProfesFragment extends AppFragment {
    private TextView mostrarTexto;
    private EditText editTextDescripcion;

    public IncidenciaProfesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_incidencia_profes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextDescripcion = view.findViewById(R.id.viewholder_descripcion_profe);

        mostrarTexto = view.findViewById(R.id.descripcion_mostrar_incidencia);

        db.collection("Incidencia").whereEqualTo("descripcion", editTextDescripcion.getText().toString()).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                String lugar = queryDocumentSnapshots.getQuery().toString();
                String descripcion = queryDocumentSnapshots.getQuery().toString();

                mostrarTexto.setText("Lugar: " + lugar + "\n" + "Descripcion: " + descripcion);

            }
        });

    }
}
