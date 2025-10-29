package com.example.iniciodesesion.ui.horario;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.iniciodesesion.R;

import java.util.HashMap;
import java.util.Map;

public class HorarioFragment extends Fragment {
    private HorarioViewModel viewModel;
    private GridLayout gridHorario;
    private Map<String, EditText> celdasMap = new HashMap<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_horario, container, false);

        gridHorario = view.findViewById(R.id.gridHorario);
        initViewModel();
        generarHorario();
        setupObservers();

        return view;
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(HorarioViewModel.class);
    }

    private void generarHorario() {
        String[] dias = {"", "Lun", "Mar", "Mié", "Jue", "Vie"};

        // Configurar GridLayout
        gridHorario.setColumnCount(6);
        gridHorario.setRowCount(18); // 6 AM a 10 PM = 17 horas + 1 header

        // Agregar headers de días
        for (int i = 0; i < dias.length; i++) {
            TextView tv = new TextView(getContext());
            tv.setText(dias[i]);
            tv.setGravity(Gravity.CENTER);
            tv.setPadding(8, 8, 8, 8);
            tv.setTextColor(Color.BLACK);
            tv.setBackgroundColor(Color.parseColor("#E0E0E0"));

            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.columnSpec = GridLayout.spec(i, 1f);
            params.rowSpec = GridLayout.spec(0);
            params.width = 0;
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            params.setMargins(2, 2, 2, 2);
            tv.setLayoutParams(params);

            gridHorario.addView(tv);
        }

        // Agregar celdas de horario
        for (int hora = 6; hora <= 22; hora++) {
            // Columna de horas
            TextView tvHora = new TextView(getContext());
            tvHora.setText(hora + ":00");
            tvHora.setGravity(Gravity.CENTER);
            tvHora.setPadding(8, 8, 8, 8);
            tvHora.setTextColor(Color.BLACK);
            tvHora.setBackgroundColor(Color.parseColor("#E0E0E0"));

            GridLayout.LayoutParams paramsHora = new GridLayout.LayoutParams();
            paramsHora.columnSpec = GridLayout.spec(0, 1f);
            paramsHora.rowSpec = GridLayout.spec(hora - 5);
            paramsHora.width = 0;
            paramsHora.height = 100;
            paramsHora.setMargins(2, 2, 2, 2);
            tvHora.setLayoutParams(paramsHora);

            gridHorario.addView(tvHora);

            // Celdas editables para cada día
            for (int dia = 1; dia <= 5; dia++) {
                EditText celda = new EditText(getContext());
                celda.setGravity(Gravity.CENTER);
                celda.setBackgroundColor(Color.WHITE);
                celda.setTextColor(Color.BLACK);
                celda.setHint("");

                String key = hora + "_" + dia;
                celdasMap.put(key, celda);

                GridLayout.LayoutParams paramsCelda = new GridLayout.LayoutParams();
                paramsCelda.columnSpec = GridLayout.spec(dia, 1f);
                paramsCelda.rowSpec = GridLayout.spec(hora - 5);
                paramsCelda.width = 0;
                paramsCelda.height = 100;
                paramsCelda.setMargins(2, 2, 2, 2);
                celda.setLayoutParams(paramsCelda);

                final int horaFinal = hora;
                final int diaFinal = dia;

                celda.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {}

                    @Override
                    public void afterTextChanged(Editable s) {
                        String texto = s.toString();
                        viewModel.guardarCelda(horaFinal, diaFinal, texto, "#FFFFFF");
                    }
                });

                gridHorario.addView(celda);
            }
        }
    }

    private void setupObservers() {
        viewModel.getHorarioCompleto().observe(getViewLifecycleOwner(), celdas -> {
            if (celdas != null) {
                for (var celda : celdas) {
                    String key = celda.getHora() + "_" + celda.getDia();
                    EditText et = celdasMap.get(key);
                    if (et != null && !et.getText().toString().equals(celda.getTexto())) {
                        et.setText(celda.getTexto());
                    }
                }
            }
        });
    }
}
