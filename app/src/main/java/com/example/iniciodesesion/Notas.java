package com.example.iniciodesesion;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Notas extends AppCompatActivity {

    private EditText etApuntes;
    private LinearLayout containerTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);

        // Vincular vistas
        TextView tvSubjectTitle = findViewById(R.id.tvSubjectTitle);
        etApuntes = findViewById(R.id.etApuntes);
        Button btnAddTask = findViewById(R.id.btnAddTask);
        containerTasks = findViewById(R.id.container_tasks);

        // Recibir el nombre de la asignatura (opcional)
        String nombreAsignatura = getIntent().getStringExtra("nombreAsignatura");
        if (nombreAsignatura != null) {
            tvSubjectTitle.setText(nombreAsignatura);
        }

        // Botón para añadir apuntes/tareas dinámicamente
        btnAddTask.setOnClickListener(v -> {
            String newTask = etApuntes.getText().toString().trim();
            if (!newTask.isEmpty()) {
                // Crear TextView para mostrar la tarea
                TextView task = new TextView(this);
                task.setText(newTask);
                task.setPadding(16, 16, 16, 16);
                task.setTextSize(16f);

                // Agregar al contenedor
                containerTasks.addView(task);

                // Limpiar EditText
                etApuntes.setText("");
            }
        });
    }
}
