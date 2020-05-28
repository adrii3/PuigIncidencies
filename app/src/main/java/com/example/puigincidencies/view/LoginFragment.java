package com.example.puigincidencies.view;


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
import android.widget.TextView;
import android.widget.Toast;

import com.example.puigincidencies.AppFragment;
import com.example.puigincidencies.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;

import static android.widget.Toast.LENGTH_SHORT;



public class LoginFragment extends AppFragment {


    private EditText editEmailProfe, editContraseñaProfe;
    private Button btnIniciarSesionProfe;
    private ImageView imagenGoogle;


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);

    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        editEmailProfe = view.findViewById(R.id.edit_text_usuario);
        editContraseñaProfe = view.findViewById(R.id.edit_text_contraseña);

        btnIniciarSesionProfe =view.findViewById(R.id.boton_login_iniciar_sesion);
        btnIniciarSesionProfe.setOnClickListener(new View.OnClickListener() {
            //Inicio de  sesión de profes
            @Override
            public void onClick(View v) {
                iniciarSesion();
            }
        });
    }

    private void iniciarSesion(){
        String email = editEmailProfe.getText().toString();
        String contraseña = editContraseñaProfe.getText().toString();

        if(!email.isEmpty() && !contraseña.isEmpty()){
            auth.signInWithEmailAndPassword(email,contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                        db.collection("Users").document(user.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot == null) {
                                    navController.navigate(R.id.inicioFragment);
                                } else {
                                    navController.navigate(R.id.inicioProfesFragment);
                                }
                            }
                        });
                    }else{
                        Toast.makeText(getContext(), "Usuario o contraseña incorrectos", LENGTH_SHORT).show();
                    }
                }
            });
        }else{
            Toast.makeText(getContext(),"Rellene los campos requeridos", Toast.LENGTH_LONG);
        }
    }
}

