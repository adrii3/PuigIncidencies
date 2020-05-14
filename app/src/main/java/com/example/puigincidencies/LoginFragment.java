package com.example.puigincidencies;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    EditText editUsuario, editContraseña;
    Button btnIniciarSesion;
    TextView textClick;

    public LoginFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editUsuario = view.findViewById(R.id.edit_text_usuario);

        editContraseña = view.findViewById(R.id.edit_text_contraseña);

        btnIniciarSesion =view.findViewById(R.id.boton_login_iniciar_sesion);

        textClick =  view.findViewById(R.id.text_click);
        textClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.loginProfesFragment);
            }
        });
    }
}
