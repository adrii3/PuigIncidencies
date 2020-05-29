package com.example.puigincidencies;

import com.example.puigincidencies.view.Profes.InicioProfesFragment;
import com.google.firebase.firestore.Query;

public class IncidenciaAceptadaFragment extends InicioProfesFragment {
    Query setQuery(){
        return  db.collection("Incidencia").whereEqualTo("aceptarIncidencia", true);
    }
}
