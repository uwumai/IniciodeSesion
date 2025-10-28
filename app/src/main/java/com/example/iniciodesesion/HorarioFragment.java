package com.example.iniciodesesion;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.HashMap;
import java.util.Random;

public class HorarioFragment extends Fragment {

    private TableLayout tablaHorario;
    private Button btnEditar, btnGuardar;
    private boolean modoEdicion = false;

    // 🎨 Colores pasteles ampliados
    private final String[] coloresPasteles = {
            "#FFD6A5", "#FFB5E8", "#B5EAD7", "#C7CEEA", "#FFF5BA",
            "#FFDAC1", "#E2F0CB", "#FFABAB", "#C2F0FC", "#F1C0E8",
            "#D5AAFF", "#A7FEEB", "#FFE8A1", "#FFC9DE", "#A4F4F9",
            "#E0BBE4", "#FDFD96", "#C8E7FF", "#F3CFC6", "#D0F4DE"
    };

    private final HashMap<String, String> mapaColores = new HashMap<>();
    private final Random random = new Random();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_horario, container, false);

        tablaHorario = view.findViewById(R.id.tablaHorario);
        btnEditar = view.findViewById(R.id.btnEditar);
        btnGuardar = view.findViewById(R.id.btnGuardar);

        generarHorario(); // 🔹 genera todas las filas de 6 AM a 10 PM
        configurarBotones();

        return view;
    }

    private void configurarBotones() {
        btnEditar.setOnClickListener(v -> {
            modoEdicion = true;
            actualizarModoEdicion(true);
            btnEditar.setVisibility(View.GONE);
            btnGuardar.setVisibility(View.VISIBLE);
        });

        btnGuardar.setOnClickListener(v -> {
            modoEdicion = false;
            actualizarModoEdicion(false);
            btnGuardar.setVisibility(View.GONE);
            btnEditar.setVisibility(View.VISIBLE);

            // 🟢 Aplica colores al guardar
            aplicarColoresATodasLasCeldas();
        });
    }

    private void generarHorario() {
        // Limpia la tabla por si se vuelve a crear
        tablaHorario.removeAllViews();

        // 🔹 Encabezado
        TableRow encabezado = new TableRow(getContext());
        String[] dias = {"Hora", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes"};
        for (String dia : dias) {
            TextView tv = new TextView(getContext());
            tv.setText(dia);
            tv.setPadding(16, 8, 16, 8);
            tv.setTextColor(Color.BLACK);
            encabezado.addView(tv);
        }
        tablaHorario.addView(encabezado);

        // 🔹 Filas de 6 AM a 10 PM
        for (int hora = 6; hora <= 22; hora++) {
            TableRow fila = new TableRow(getContext());

            // Columna de la hora
            TextView tvHora = new TextView(getContext());
            String horaTexto = (hora <= 12 ? hora : hora - 12) + (hora < 12 ? " AM" : " PM");
            tvHora.setText(horaTexto);
            tvHora.setPadding(16, 8, 16, 8);
            tvHora.setTextColor(Color.DKGRAY);
            fila.addView(tvHora);

            // Celdas de los días
            for (int j = 1; j < dias.length; j++) {
                EditText celda = new EditText(getContext());
                celda.setSingleLine(false);
                celda.setEnabled(false);
                celda.setMaxLines(2);
                celda.setTextColor(Color.BLACK);
                celda.setBackgroundColor(Color.WHITE);
                fila.addView(celda);
            }
            tablaHorario.addView(fila);
        }
    }

    private void aplicarColoresATodasLasCeldas() {
        mapaColores.clear(); // limpia colores previos para recalcular según textos

        for (int i = 1; i < tablaHorario.getChildCount(); i++) { // omite encabezado
            View fila = tablaHorario.getChildAt(i);
            if (fila instanceof TableRow) {
                TableRow row = (TableRow) fila;
                for (int j = 1; j < row.getChildCount(); j++) { // omite columna de hora
                    View celda = row.getChildAt(j);
                    if (celda instanceof EditText) {
                        EditText editText = (EditText) celda;
                        String texto = editText.getText().toString().trim();

                        if (!texto.isEmpty()) {
                            if (!mapaColores.containsKey(texto)) {
                                mapaColores.put(texto, coloresPasteles[random.nextInt(coloresPasteles.length)]);
                            }
                            editText.setBackgroundColor(Color.parseColor(mapaColores.get(texto)));
                        } else {
                            editText.setBackgroundColor(Color.WHITE);
                        }
                    }
                }
            }
        }
    }

    private void actualizarModoEdicion(boolean habilitar) {
        for (int i = 1; i < tablaHorario.getChildCount(); i++) {
            View fila = tablaHorario.getChildAt(i);
            if (fila instanceof TableRow) {
                TableRow row = (TableRow) fila;
                for (int j = 1; j < row.getChildCount(); j++) {
                    View celda = row.getChildAt(j);
                    if (celda instanceof EditText) {
                        EditText editText = (EditText) celda;
                        editText.setEnabled(habilitar);
                    }
                }
            }
        }
    }
}