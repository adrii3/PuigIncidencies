package com.example.puigincidencies.Profes;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.puigincidencies.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static android.widget.Toast.LENGTH_SHORT;


public class LoginProfesFragment extends Fragment {


    private EditText editEmailProfe, editContraseñaProfe;
    private Button btnIniciarSesionProfe;
    private ImageView imagenGoogle;
    FirebaseAuth firebaseAuthProfe;
    NavController navController;


    public LoginProfesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_profes, container, false);

    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editEmailProfe = view.findViewById(R.id.edit_text_usuario_profes);
        editContraseñaProfe = view.findViewById(R.id.edit_text_contraseña_profes);

        btnIniciarSesionProfe =view.findViewById(R.id.boton_login_iniciar_sesion_profes);
        btnIniciarSesionProfe.setOnClickListener(new View.OnClickListener() {
            //Inicio de  sesión de profes
            @Override
            public void onClick(View v) {
                iniciarSesionProfes();
            }
        });

        imagenGoogle =  view.findViewById(R.id.boton_iniciar_sesion_google);
        imagenGoogle.setOnClickListener(new View.OnClickListener() {
            //Inicio de sesión con cuenta de google
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void iniciarSesionProfes(){
        String email = editEmailProfe.getText().toString();
        String contraseña = editContraseñaProfe.getText().toString();

        if(!email.isEmpty() && !contraseña.isEmpty()){
            firebaseAuthProfe.signInWithEmailAndPassword(email,contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        navController.navigate(R.id.inicioProfesFragment);
                    }else{
                        Toast.makeText(getContext(),"Inicio de sesión incorrecto", LENGTH_SHORT).show();
                    }
                }
            });
        }else{
            Toast.makeText(getContext(),"Relllene los campos requeridos", Toast.LENGTH_LONG);
            return;
        }
    }
}
