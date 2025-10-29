package com.example.iniciodesesion.ui.apuntes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iniciodesesion.R;
import com.example.iniciodesesion.data.local.entities.Materia;

import java.util.ArrayList;
import java.util.List;

public class ApuntesAdapter extends RecyclerView.Adapter<ApuntesAdapter.ViewHolder> {
    private List<Materia> materias = new ArrayList<>();
    private OnMateriaClickListener listener;

    public interface OnMateriaClickListener {
        void onMateriaClick(Materia materia);
        void onGuardarClick(Materia materia, String nuevoNombre);
    }

    public ApuntesAdapter(OnMateriaClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_asignatura, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Materia materia = materias.get(position);
        holder.bind(materia, listener);
    }

    @Override
    public int getItemCount() {
        return materias.size();
    }

    public void setMaterias(List<Materia> materias) {
        this.materias = materias;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView colorBox;
        EditText etSubject;
        Button btnGuardar;

        ViewHolder(View itemView) {
            super(itemView);
            colorBox = itemView.findViewById(R.id.colorBox);
            etSubject = itemView.findViewById(R.id.etSubject);
            btnGuardar = itemView.findViewById(R.id.btnGuardar);
        }

        void bind(Materia materia, OnMateriaClickListener listener) {
            colorBox.setCardBackgroundColor(materia.getColor());
            etSubject.setText(materia.getNombre());

            itemView.setOnClickListener(v -> listener.onMateriaClick(materia));

            btnGuardar.setOnClickListener(v -> {
                String nuevoNombre = etSubject.getText().toString().trim();
                if (!nuevoNombre.isEmpty()) {
                    listener.onGuardarClick(materia, nuevoNombre);
                }
            });
        }
    }
}
