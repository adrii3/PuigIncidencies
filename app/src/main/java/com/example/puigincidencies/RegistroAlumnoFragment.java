package com.example.puigincidencies;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistroAlumnoFragment extends AppFragment {
      private EditText nombrecompleto;
      private EditText correo;
      private String contraseña;
      private Button boton;


    public RegistroAlumnoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registro_alumno, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nombrecompleto=view.findViewById(R.id.nombre);
        correo=view.findViewById(R.id.correo);
        boton=view.findViewById(R.id.generacionautomaricadecontraseñas);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contraseña =nombrecompleto.getText().toString().trim();
                auth.createUserWithEmailAndPassword(correo.getText().toString(),contraseña);
            }
        });
    }

}
