package com.example.iniciodesesion.ui.tareas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iniciodesesion.R;
import com.example.iniciodesesion.data.local.entities.Tarea;

public class TareasFragment extends Fragment {
    private TareasViewModel viewModel;
    private TareasAdapter adapter;
    private RecyclerView recyclerView;
    private Button btnAddTarea;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tareas, container, false);

        initViews(view);
        initViewModel();
        setupRecyclerView();
        setupObservers();
        setupListeners();

        return view;
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.recyclerViewTareas);
        btnAddTarea = view.findViewById(R.id.btnAddTarea);
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
                // Aquí puedes agregar funcionalidad para editar o ver detalles
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    private void setupObservers() {
        viewModel.getTodasLasTareas().observe(getViewLifecycleOwner(), tareas -> {
            if (tareas != null) {
                adapter.setTareas(tareas);
            }
        });
    }

    private void setupListeners() {
        btnAddTarea.setOnClickListener(v -> mostrarDialogoAgregarTarea());
    }

    private void mostrarDialogoAgregarTarea() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_tarea, null);

        EditText etDescripcion = dialogView.findViewById(R.id.etDescripcionTarea);

        builder.setView(dialogView)
                .setTitle("Nueva Tarea")
                .setPositiveButton("Agregar", (dialog, which) -> {
                    String descripcion = etDescripcion.getText().toString().trim();
                    if (!descripcion.isEmpty()) {
                        viewModel.agregarTarea(0, descripcion); // 0 = sin materia específica
                        Toast.makeText(getContext(), "Tarea agregada", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Ingresa una descripción", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }
}