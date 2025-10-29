package com.example.iniciodesesion.ui.apuntes;

import android.content.Intent;
import android.graphics.Color;
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
import com.example.iniciodesesion.data.local.entities.Materia;

public class ApuntesFragment extends Fragment {
    private ApuntesViewModel viewModel;
    private ApuntesAdapter adapter;
    private RecyclerView recyclerView;
    private Button btnAdd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_apuntes, container, false);

        initViews(view);
        initViewModel();
        setupRecyclerView();
        setupObservers();
        setupListeners();

        return view;
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.recyclerViewMaterias);
        btnAdd = view.findViewById(R.id.btn_add_subject);
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(ApuntesViewModel.class);
    }

    private void setupRecyclerView() {
        adapter = new ApuntesAdapter(new ApuntesAdapter.OnMateriaClickListener() {
            @Override
            public void onMateriaClick(Materia materia) {
                Intent intent = new Intent(getContext(), NotasActivity.class);
                intent.putExtra("materia_id", materia.getId());
                intent.putExtra("materia_nombre", materia.getNombre());
                startActivity(intent);
            }

            @Override
            public void onGuardarClick(Materia materia, String nuevoNombre) {
                materia.setNombre(nuevoNombre);
                viewModel.actualizarMateria(materia);
                Toast.makeText(getContext(), "Materia actualizada", Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    private void setupObservers() {
        viewModel.getMaterias().observe(getViewLifecycleOwner(), materias -> {
            if (materias != null) {
                adapter.setMaterias(materias);
            }
        });
    }

    private void setupListeners() {
        btnAdd.setOnClickListener(v -> mostrarDialogoAgregarMateria());
    }

    private void mostrarDialogoAgregarMateria() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_materia, null);

        EditText etNombre = dialogView.findViewById(R.id.etNombreMateria);

        builder.setView(dialogView)
                .setTitle("Nueva Asignatura")
                .setPositiveButton("Agregar", (dialog, which) -> {
                    String nombre = etNombre.getText().toString().trim();
                    if (!nombre.isEmpty()) {
                        int[] colores = {
                                Color.parseColor("#FF6B6B"),
                                Color.parseColor("#4ECDC4"),
                                Color.parseColor("#45B7D1"),
                                Color.parseColor("#FFA07A"),
                                Color.parseColor("#98D8C8")
                        };
                        int colorAleatorio = colores[(int) (Math.random() * colores.length)];
                        viewModel.agregarMateria(nombre, colorAleatorio);
                        Toast.makeText(getContext(), "Materia agregada", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Ingresa un nombre", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }
}
