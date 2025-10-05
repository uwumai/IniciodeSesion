package com.example.iniciodesesion;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import java.util.Random;

public class ApuntesFragment extends Fragment {

    private LinearLayout containerSubjects;
    private final Random random = new Random();

    // 🎨 Paleta de colores pastel
    private final int[] pastelColors = {
            Color.parseColor("#CDB4DB"), // morado claro
            Color.parseColor("#BDE0FE"), // azul claro
            Color.parseColor("#A2D2FF"), // celeste
            Color.parseColor("#B9FBC0"), // verde menta
            Color.parseColor("#FFB5A7"), // coral claro
            Color.parseColor("#FFD6A5"), // naranja pastel
            Color.parseColor("#EADCA6"), // beige
            Color.parseColor("#D0F4DE"), // verde agua
            Color.parseColor("#E4C1F9"), // lila
            Color.parseColor("#FDE2E4")  // rosado pastel
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_apuntes, container, false);

        containerSubjects = view.findViewById(R.id.container_subjects);

        // Botón para añadir asignaturas
        view.findViewById(R.id.btn_add_subject).setOnClickListener(v -> addSubjectCard());

        return view;
    }

    private void addSubjectCard() {
        if (getContext() == null) return;

        // Inflamos el layout de la asignatura (item_asignatura.xml)
        View subjectCard = LayoutInflater.from(getContext())
                .inflate(R.layout.item_asignatura, containerSubjects, false);

        // Caja de color (aleatorio)
        CardView colorBox = subjectCard.findViewById(R.id.colorBox);
        if (colorBox != null) {
            int randomColor = pastelColors[random.nextInt(pastelColors.length)];
            colorBox.setCardBackgroundColor(randomColor);
        }

        // Campo de texto para el nombre de la materia
        EditText etSubject = subjectCard.findViewById(R.id.etSubject);
        if (etSubject != null) {
            etSubject.setHint(getString(R.string.new_subject));
        }

        // 👉 Listener: abrir NotasActivity al hacer clic en la card
        subjectCard.setOnClickListener(v -> {
            assert etSubject != null;
            String nombreAsignatura = etSubject.getText().toString().trim();

            if (nombreAsignatura.isEmpty()) {
                nombreAsignatura = getString(R.string.new_subject);
            }

            // Abrir NotasActivity
            Intent intent = new Intent(getContext(), Notas.class);
            intent.putExtra("nombreAsignatura", nombreAsignatura);
            startActivity(intent);
        });

        // Finalmente agregamos la card al contenedor
        containerSubjects.addView(subjectCard);
    }
}