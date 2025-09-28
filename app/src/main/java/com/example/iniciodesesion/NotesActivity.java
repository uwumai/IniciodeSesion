package com.example.iniciodesesion;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class NotesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton fabAddNote;
    private List<Note> notesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        initViews();
        setupRecyclerView();
        setupFab();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerViewNotes);
        fabAddNote = findViewById(R.id.fabAddNote);
    }

    private void setupRecyclerView() {
        notesList = new ArrayList<>();
        // Agregar notas de ejemplo
        notesList.add(new Note("Matemáticas", "Cálculo Diferencial", "Derivadas y límites..."));
        notesList.add(new Note("Física", "Mecánica Clásica", "Leyes de Newton..."));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Aquí necesitarás crear un NotesAdapter
        // NotesAdapter adapter = new NotesAdapter(notesList);
        // recyclerView.setAdapter(adapter);
    }

    private void setupFab() {
        fabAddNote.setOnClickListener(v -> {
            // Abrir actividad para crear nueva nota
            Toast.makeText(this, "Crear nueva nota", Toast.LENGTH_SHORT).show();
        });
    }

    // Clase interna para representar una nota
    public static class Note {
        private String subject;
        private String title;
        private String content;

        public Note(String subject, String title, String content) {
            this.subject = subject;
            this.title = title;
            this.content = content;
        }

        // Getters
        public String getSubject() { return subject; }
        public String getTitle() { return title; }
        public String getContent() { return content; }
    }
}
