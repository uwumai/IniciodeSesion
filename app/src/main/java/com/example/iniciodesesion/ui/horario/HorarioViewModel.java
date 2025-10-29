package com.example.iniciodesesion.ui.horario;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.iniciodesesion.data.local.entities.HorarioCelda;
import com.example.iniciodesesion.data.repository.HorarioRepository;

import java.util.List;

public class HorarioViewModel extends AndroidViewModel {
    private final HorarioRepository repository;
    private final LiveData<List<HorarioCelda>> horarioCompleto;

    public HorarioViewModel(@NonNull Application application) {
        super(application);
        repository = new HorarioRepository(application);
        horarioCompleto = repository.obtenerTodoElHorario();
    }

    public LiveData<List<HorarioCelda>> getHorarioCompleto() {
        return horarioCompleto;
    }

    public void guardarCelda(int hora, int dia, String texto, String color) {
        HorarioCelda celda = new HorarioCelda(hora, dia, texto, color);
        repository.insertarOActualizar(celda);
    }

    public void eliminarCelda(int hora, int dia) {
        repository.eliminarCelda(hora, dia);
    }
}