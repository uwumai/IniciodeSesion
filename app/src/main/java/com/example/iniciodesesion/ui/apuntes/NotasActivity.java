package com.example.iniciodesesion.ui.apuntes;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iniciodesesion.R;
import com.example.iniciodesesion.data.local.entities.Tarea;
import com.example.iniciodesesion.ui.tareas.TareasAdapter;
import com.example.iniciodesesion.ui.tareas.TareasViewModel;

public class NotasActivity extends AppCompatActivity {
    private TareasViewModel viewModel;
    private TareasAdapter adapter;
    private RecyclerView recyclerView;
    private TextView tvMateriaNombre;
    private Button btnAddNota;
    private int materiaId;
    private String materiaNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);

        // Obtener datos del intent
        materiaId = getIntent().getIntExtra("materia_id", -1);
        materiaNombre = getIntent().getStringExtra("materia_nombre");

        if (materiaId == -1) {
            Toast.makeText(this, "Error al cargar la materia", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        initViews();
        initViewModel();
        setupRecyclerView();
        setupObservers();
        setupListeners();

        // Configurar título
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(materiaNombre);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initViews() {
        tvMateriaNombre = findViewById(R.id.tvMateriaNombre);
        recyclerView = findViewById(R.id.recyclerViewNotas);
        btnAddNota = findViewById(R.id.btnAddNota);

        tvMateriaNombre.setText(materiaNombre);
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(TareasViewModel.class);
    }

    private void setupRecyclerView() {
        adapter = new TareasAdapter(new TareasAdapter.OnTareaClickListener() {
            @Override
            public void onCheckboxClick(Tarea tarea) {
                viewModel.toggleCompletada(tarea);
            }

            @Override
            public void onTareaClick(Tarea tarea) {
                mostrarDialogoEditarNota(tarea);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void setupObservers() {
        viewModel.getTodasLasTareas().observe(this, tareas -> {
            if (tareas != null) {
                // Filtrar solo las tareas de esta materia
                java.util.List<Tarea> tareasFiltradas = new java.util.ArrayList<>();
                for (Tarea tarea : tareas) {
                    if (tarea.getMateriaId() == materiaId) {
                        tareasFiltradas.add(tarea);
                    }
                }
                adapter.setTareas(tareasFiltradas);
            }
        });
    }

    private void setupListeners() {
        btnAddNota.setOnClickListener(v -> mostrarDialogoAgregarNota());
    }

    private void mostrarDialogoAgregarNota() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_tarea, null);

        EditText etDescripcion = dialogView.findViewById(R.id.etDescripcionTarea);

        builder.setView(dialogView)
                .setTitle("Nueva Nota")
                .setPositiveButton("Agregar", (dialog, which) -> {
                    String descripcion = etDescripcion.getText().toString().trim();
                    if (!descripcion.isEmpty()) {
                        viewModel.agregarTarea(materiaId, descripcion);
                        Toast.makeText(this, "Nota agregada", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Ingresa una descripción", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    private void mostrarDialogoEditarNota(Tarea tarea) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_tarea, null);

        EditText etDescripcion = dialogView.findViewById(R.id.etDescripcionTarea);
        etDescripcion.setText(tarea.getDescripcion());

        builder.setView(dialogView)
                .setTitle("Editar Nota")
                .setPositiveButton("Guardar", (dialog, which) -> {
                    String descripcion = etDescripcion.getText().toString().trim();
                    if (!descripcion.isEmpty()) {
                        tarea.setDescripcion(descripcion);
                        viewModel.actualizarTarea(tarea);
                        Toast.makeText(this, "Nota actualizada", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Eliminar", (dialog, which) -> {
                    viewModel.eliminarTarea(tarea);
                    Toast.makeText(this, "Nota eliminada", Toast.LENGTH_SHORT).show();
                })
                .setNeutralButton("Cancelar", null)
                .show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
