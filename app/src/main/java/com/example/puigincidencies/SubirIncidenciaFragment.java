package com.example.puigincidencies;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

public class SubirIncidenciaFragment extends AppFragment {
    private ImageView imageView;
    private Spinner spinnerClase;
    private EditText editTextDescripcion;
    private Button subirIncidencia;
    private StorageReference mStorage;
    private String lugar, textoDescripcion;
    private boolean aceptarIncidencia = false;
    Map<String, Object> incidencia = new HashMap<>();



    public SubirIncidenciaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_subir_incidencia, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayAdapter<CharSequence> adapterSpinnerClases = ArrayAdapter.createFromResource(getContext(), R.array.valores_spinner_clase, android.R.layout.simple_spinner_item);
        spinnerClase = view.findViewById(R.id.spinnner_clase);
        spinnerClase.setAdapter(adapterSpinnerClases);
        spinnerClase.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lugar = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        editTextDescripcion = view.findViewById(R.id.descripcion_et_subir_incidencia);
        textoDescripcion = editTextDescripcion.getText().toString();

        subirIncidencia = view.findViewById(R.id.btn_subir_incidencias);
        subirIncidencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subirDatos();
            }
        });

        //FALTA AÑADIR LA CAMARA PARA SUBIR A LA BASE DE DATOS TODA LA INFO DE LA INCIDENCIA, AL SUBIRLO A LA BASE DE DATOS PETA
        ImageButton sacarfoto;
        sacarfoto=view.findViewById(R.id.sacarfoto);
        sacarfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tomarfoto();
            }
        });
    }

    private void subirDatos(){

        if(lugar.isEmpty() && textoDescripcion.isEmpty()){
            Toast.makeText(requireContext(), "Rellene los campos requeridos (Lugar y descripcion)", Toast.LENGTH_SHORT).show();
        }else{
            incidencia.put("idUsuario",user.getUid());
            incidencia.put("lugar", lugar);
            incidencia.put("descripcion",textoDescripcion);
            incidencia.put("aceptarIncidencia",aceptarIncidencia);
            incidencia.put("foto",currentPhotoPath);
            db.collection("Incidencia").document().set(incidencia);
            navController.popBackStack();
            Toast.makeText(requireContext(), "Incidencia subida", Toast.LENGTH_SHORT).show();

        }
    }


    String currentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();

        return image;
    }

    static final int REQUEST_TAKE_PHOTO = 1;

    public void tomarfoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(requireActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
              // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(requireActivity(),
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    static final int REQUEST_IMAGE_CAPTURE =1;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
         Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
            mStorage = FirebaseStorage.getInstance().getReference();
            Uri uri = data.getData();

            StorageReference filePath= mStorage.child("fotos").child(uri.getLastPathSegment());
            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(requireActivity() ,"foto subida correctamente",Toast.LENGTH_SHORT).show();
                }
            });




        }
    }

}
