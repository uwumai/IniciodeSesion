package com.example.iniciodesesion;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import java.util.Random;

public class ApuntesFragment extends Fragment {

    private LinearLayout containerSubjects;
    private BaseDeDatos baseDeDatos;
    private final Random random = new Random();

    private final int[] pastelColors = {
            Color.parseColor("#CDB4DB"),
            Color.parseColor("#A2D2FF"),
            Color.parseColor("#B9FBC0"),
            Color.parseColor("#FFB5A7"),
            Color.parseColor("#FFD6A5"),
            Color.parseColor("#EADCA6"),
            Color.parseColor("#D0F4DE"),
            Color.parseColor("#E4C1F9"),
            Color.parseColor("#876E61FF"),
            Color.parseColor("#FFDAB9"),
            Color.parseColor("#FBE073"),
            Color.parseColor("#C4D7C2FF"),
            Color.parseColor("#8B6B4A")
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_apuntes, container, false);

        containerSubjects = view.findViewById(R.id.container_subjects);
        baseDeDatos = new BaseDeDatos(requireContext());

        // Botón de añadir materia
        view.findViewById(R.id.btn_add_subject).setOnClickListener(v -> addSubjectCard());

        return view;
    }

    private void addSubjectCard() {
        if (getContext() == null) return;

        View subjectCard = LayoutInflater.from(getContext())
                .inflate(R.layout.item_asignatura, containerSubjects, false);

        CardView colorBox = subjectCard.findViewById(R.id.colorBox);
        if (colorBox != null) {
            int randomColor = pastelColors[random.nextInt(pastelColors.length)];
            colorBox.setCardBackgroundColor(randomColor);
        }

        EditText etSubject = subjectCard.findViewById(R.id.etSubject);
        Button btnGuardar = subjectCard.findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(v -> {
            String nombreMateria = etSubject.getText().toString().trim();

            if (nombreMateria.isEmpty()) {
                Toast.makeText(getContext(), "Escribe el nombre de la materia", Toast.LENGTH_SHORT).show();
                return;
            }

            // Guardar en base de datos
            baseDeDatos.agregarMateria(nombreMateria);

            Toast.makeText(getContext(), "Materia guardada", Toast.LENGTH_SHORT).show();

            // Ocultar botón después de guardar
            btnGuardar.setVisibility(View.GONE);

            // Bloquear el EditText (ya no editable)
            etSubject.setEnabled(false);
        });

        subjectCard.setOnClickListener(v -> {
            String nombreAsignatura = etSubject.getText().toString().trim();
            if (nombreAsignatura.isEmpty()) {
                nombreAsignatura = "Nueva materia";
            }

            Intent intent = new Intent(getContext(), Notas.class);
            intent.putExtra("nombreAsignatura", nombreAsignatura);
            startActivity(intent);
        });

        containerSubjects.addView(subjectCard);
    }
}