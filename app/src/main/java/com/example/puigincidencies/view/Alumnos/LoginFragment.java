package com.example.puigincidencies.view.Alumnos;


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
import android.widget.TextView;
import android.widget.Toast;

import com.example.puigincidencies.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.widget.Toast.LENGTH_SHORT;



public class LoginFragment extends Fragment {


    private EditText editEmail, editContraseña;
    private Button btnIniciarSesion;
    private TextView textClick;

    private NavController navController;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");

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

        navController = Navigation.findNavController(view);

        editEmail = view.findViewById(R.id.edit_text_usuario);
        editContraseña = view.findViewById(R.id.edit_text_contraseña);




        btnIniciarSesion =view.findViewById(R.id.boton_login_iniciar_sesion);
        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                iniciarSesion();
            }
        });

        textClick =  view.findViewById(R.id.text_click);
        textClick.setOnClickListener(new View.OnClickListener() {
            //Navegación a registro de profesores
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.loginProfesFragment);
            }
        });

    }

    private void iniciarSesion(){
        String email = editEmail.getText().toString();
        String contraseña = editContraseña.getText().toString();


        if(!email.isEmpty() && !contraseña.isEmpty()){
            firebaseAuth.signInWithEmailAndPassword(email,contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    String uid = firebaseAuth.getCurrentUser().getUid();
                    if(task.isSuccessful()){
                        if(!uid.equals(mDatabase.child(firebaseAuth.getCurrentUser().getUid()))){
                            actualizarUI(firebaseAuth.getCurrentUser());
                        }else {
                            Toast.makeText(getContext(),"Inicia sesión como profe", LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getContext(), "Usuario o contraseña incorrecto", LENGTH_SHORT).show();
                    }
                }
            });
        }else{
            Toast.makeText(getContext(),"Rellene los campos requeridos", Toast.LENGTH_LONG);
            return;
        }
    }

    private void actualizarUI(FirebaseUser currentUser){
        if(currentUser != null) {
            navController.navigate(R.id.inicioFragment);
        }
    }
}

