package com.example.iniciodesesion;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SubjectsActivity extends AppCompatActivity {

    private RecyclerView recyclerViewSubjects;
    private List<Subject> subjectsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects);

        initViews();
        setupSubjects();
    }

    private void initViews() {
        recyclerViewSubjects = findViewById(R.id.recyclerViewSubjects);
    }

    private void setupSubjects() {
        subjectsList = new ArrayList<>();
        // Agregar materias de ejemplo
        subjectsList.add(new Subject("Matemáticas", "Prof. García", "#FFE5B4"));
        subjectsList.add(new Subject("Física", "Prof. López", "#E8F5E8"));
        subjectsList.add(new Subject("Programación", "Prof. Martínez", "#E3F2FD"));
        subjectsList.add(new Subject("Historia", "Prof. Rodríguez", "#FFF3E0"));

        recyclerViewSubjects.setLayoutManager(new GridLayoutManager(this, 2));
        // Aquí necesitarás crear un SubjectsAdapter
        // SubjectsAdapter adapter = new SubjectsAdapter(subjectsList);
        // recyclerViewSubjects.setAdapter(adapter);
    }

    // Clase interna para representar una materia
    public static class Subject {
        private String name;
        private String professor;
        private String color;

        public Subject(String name, String professor, String color) {
            this.name = name;
            this.professor = professor;
            this.color = color;
        }

        // Getters
        public String getName() { return name; }
        public String getProfessor() { return professor; }
        public String getColor() { return color; }
    }
}
