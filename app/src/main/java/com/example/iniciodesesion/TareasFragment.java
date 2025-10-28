package com.example.iniciodesesion;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import java.util.Random;

public class TareasFragment extends Fragment {

    private LinearLayout containerSubjects;

    // Colores pastel para los pendientes
    private final String[] coloresPastel = {
            "#F4E1D2", // durazno pálido
            "#EAD7E1", // rosa lavanda suave
            "#DCE2F0", // azul grisáceo suave
            "#E0F4E6", // verde menta pastel
            "#FFF1D0", // vainilla
            "#FBE4D8", // coral claro
            "#E8E8E8"
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_tareas, container, false);

        containerSubjects = root.findViewById(R.id.container_subjects);
        Button btnAddSubject = root.findViewById(R.id.btn_add_subject);

        btnAddSubject.setOnClickListener(v -> agregarCardTarea(inflater));

        return root;
    }

    private void agregarCardTarea(LayoutInflater inflater) {
        if (getContext() == null) return;

        // Inflar la tarjeta desde item_tarea.xml
        View tareaCard = inflater.inflate(R.layout.item_tarea, containerSubjects, false);

        // Referencias dentro del item
        CardView cardView = (CardView) tareaCard;
        EditText tvMateria = tareaCard.findViewById(R.id.tvMateria);
        EditText tvTarea = tareaCard.findViewById(R.id.tvTarea);
        ImageButton btnBorrar = tareaCard.findViewById(R.id.btnBorrar);
        CheckBox checkTarea = tareaCard.findViewById(R.id.checkTarea);

        // Fondo pastel aleatorio para la card
        Random random = new Random();
        String color = coloresPastel[random.nextInt(coloresPastel.length)];
        cardView.setCardBackgroundColor(Color.parseColor(color));

        // Eliminar pendiente
        btnBorrar.setOnClickListener(v -> containerSubjects.removeView(tareaCard));

        // Marcar como completada (desactiva campos y pone gris)
        checkTarea.setOnCheckedChangeListener((buttonView, isChecked) -> {
            tvMateria.setEnabled(!isChecked);
            tvTarea.setEnabled(!isChecked);
            if (isChecked) {
                tvMateria.setTextColor(Color.parseColor("#9E9E9E"));
                tvTarea.setTextColor(Color.parseColor("#9E9E9E"));
            } else {
                tvMateria.setTextColor(Color.parseColor("#4A4A4A"));
                tvTarea.setTextColor(Color.parseColor("#5A5A5A"));
            }
        });

        // Agregar al contenedor principal
        containerSubjects.addView(tareaCard);
    }
}