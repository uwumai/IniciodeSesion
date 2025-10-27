package com.example.iniciodesesion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HorarioFragment extends Fragment {

    public HorarioFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_horario, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AutoCompleteTextView menuHorarios = view.findViewById(R.id.menuHorarios);
        String[] opciones = {"Mañana", "Tarde", "Noche"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(),
                R.layout.item_dropdown, opciones);
        menuHorarios.setAdapter(adapter);

        menuHorarios.setOnItemClickListener((parent, v, position, id) -> {
            String seleccionado = parent.getItemAtPosition(position).toString();
            Toast.makeText(requireContext(), "Horario seleccionado: " + seleccionado, Toast.LENGTH_SHORT).show();
        });
    }
}