package com.example.iniciodesesion.ui.tareas;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iniciodesesion.R;
import com.example.iniciodesesion.data.local.entities.Tarea;

import java.util.ArrayList;
import java.util.List;

public class TareasAdapter extends RecyclerView.Adapter<TareasAdapter.ViewHolder> {
    private List<Tarea> tareas = new ArrayList<>();
    private OnTareaClickListener listener;

    public interface OnTareaClickListener {
        void onCheckboxClick(Tarea tarea);
        void onTareaClick(Tarea tarea);
    }

    public TareasAdapter(OnTareaClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tarea, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tarea tarea = tareas.get(position);
        holder.bind(tarea, listener);
    }

    @Override
    public int getItemCount() {
        return tareas.size();
    }

    public void setTareas(List<Tarea> tareas) {
        this.tareas = tareas;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        TextView tvDescripcion;

        ViewHolder(View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBoxTarea);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcionTarea);
        }

        void bind(Tarea tarea, OnTareaClickListener listener) {
            checkBox.setChecked(tarea.isCompletada());
            tvDescripcion.setText(tarea.getDescripcion());

            // Tachar texto si está completada
            if (tarea.isCompletada()) {
                tvDescripcion.setPaintFlags(tvDescripcion.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                tvDescripcion.setPaintFlags(tvDescripcion.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            }

            checkBox.setOnClickListener(v -> listener.onCheckboxClick(tarea));
            itemView.setOnClickListener(v -> listener.onTareaClick(tarea));
        }
    }
}
