package com.example.puigincidencies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.puigincidencies.GlideApp;

import com.example.puigincidencies.model.Incidencia;
import com.example.puigincidencies.view.Profes.InicioProfesFragment;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class IncidenciaAdapterProfes extends FirestoreRecyclerAdapter<Incidencia, IncidenciaAdapterProfes.ViewHolderIncidencia>  implements View.OnClickListener{

    private View.OnClickListener listener;
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public IncidenciaAdapterProfes(@NonNull FirestoreRecyclerOptions<Incidencia> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolderIncidencia holder, int position, @NonNull Incidencia incidenciaRecyclerProfe) {

        GlideApp.with(InicioProfesFragment.class).load(incidenciaRecyclerProfe.mediaUrl).circleCrop().into(holder.foto);
        holder.lugar.setText(incidenciaRecyclerProfe.getLugar());
        holder.descripcion.setText(incidenciaRecyclerProfe.getDescripcion());
    }

    @NonNull
    @Override
    public ViewHolderIncidencia onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_incidencia, parent, false);
        view.setOnClickListener(this);
        return new ViewHolderIncidencia(view);
    }


    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if(listener != null){
            listener.onClick(v);
        }
    }

    public class ViewHolderIncidencia extends RecyclerView.ViewHolder {
        ImageView foto;
        TextView lugar;
        TextView descripcion;

        public ViewHolderIncidencia(@NonNull View itemView) {
            super(itemView);

            foto = itemView.findViewById(R.id.idImagen);
            lugar = itemView.findViewById(R.id.viewholder_lugar_profe);
            descripcion = itemView.findViewById(R.id.viewholder_descripcion_profe);
        }
    }
}
