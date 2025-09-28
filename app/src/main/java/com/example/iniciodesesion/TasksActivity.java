package com.example.iniciodesesion;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class TasksActivity extends AppCompatActivity {

    private RecyclerView recyclerViewTasks;
    private FloatingActionButton fabAddTask;
    private List<Task> tasksList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        initViews();
        setupTasks();
        setupFab();
    }

    private void initViews() {
        recyclerViewTasks = findViewById(R.id.recyclerViewTasks);
        fabAddTask = findViewById(R.id.fabAddTask);
    }

    private void setupTasks() {
        tasksList = new ArrayList<>();
        // Agregar tareas de ejemplo
        tasksList.add(new Task("Resolver ejercicios de cálculo", "Matemáticas", "2024-01-15", false));
        tasksList.add(new Task("Laboratorio de física", "Física", "2024-01-16", true));
        tasksList.add(new Task("Proyecto final", "Programación", "2024-01-20", false));

        recyclerViewTasks.setLayoutManager(new LinearLayoutManager(this));
        // Aquí necesitarás crear un TasksAdapter
        // TasksAdapter adapter = new TasksAdapter(tasksList);
        // recyclerViewTasks.setAdapter(adapter);
    }

    private void setupFab() {
        fabAddTask.setOnClickListener(v -> {
            // Abrir diálogo para crear nueva tarea
        });
    }

    // Clase interna para representar una tarea
    public static class Task {
        private String title;
        private String subject;
        private String dueDate;
        private boolean isCompleted;

        public Task(String title, String subject, String dueDate, boolean isCompleted) {
            this.title = title;
            this.subject = subject;
            this.dueDate = dueDate;
            this.isCompleted = isCompleted;
        }

        // Getters y setters
        public String getTitle() { return title; }
        public String getSubject() { return subject; }
        public String getDueDate() { return dueDate; }
        public boolean isCompleted() { return isCompleted; }
        public void setCompleted(boolean completed) { isCompleted = completed; }
    }
}
