package com.example.puigincidencies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.puigincidencies.model.Incidencia;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class IncidenciaAdapter extends FirestoreRecyclerAdapter<Incidencia, IncidenciaAdapter.ViewHolderIncidencia> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public IncidenciaAdapter(@NonNull FirestoreRecyclerOptions<Incidencia> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolderIncidencia holder, int position, @NonNull Incidencia model) {
        holder.lugar.setText(model.getLugar());
        holder.descripcion.setText(model.getDescripcion());
    }

    @NonNull
    @Override
    public ViewHolderIncidencia onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_incidencia_alumno, parent, false);
        return new ViewHolderIncidencia(view);
    }

    public class ViewHolderIncidencia extends RecyclerView.ViewHolder {
        TextView lugar;
        TextView descripcion;

        public ViewHolderIncidencia(@NonNull View itemView) {
            super(itemView);

            lugar = itemView.findViewById(R.id.viewholder_lugar);
            descripcion = itemView.findViewById(R.id.viewholder_descripcion);
        }
    }
}
