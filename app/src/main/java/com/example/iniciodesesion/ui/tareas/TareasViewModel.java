package com.example.iniciodesesion.ui.tareas;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.iniciodesesion.data.local.entities.Tarea;
import com.example.iniciodesesion.data.repository.TareaRepository;

import java.util.List;

public class TareasViewModel extends AndroidViewModel {
    private final TareaRepository repository;
    private final LiveData<List<Tarea>> todasLasTareas;

    public TareasViewModel(@NonNull Application application) {
        super(application);
        repository = new TareaRepository(application);
        todasLasTareas = repository.obtenerTodasLasTareas();
    }

    public LiveData<List<Tarea>> getTodasLasTareas() {
        return todasLasTareas;
    }

    public void agregarTarea(int materiaId, String descripcion) {
        Tarea tarea = new Tarea(materiaId, descripcion);
        repository.insertar(tarea);
    }

    public void actualizarTarea(Tarea tarea) {
        repository.actualizar(tarea);
    }

    public void eliminarTarea(Tarea tarea) {
        repository.eliminar(tarea);
    }

    public void toggleCompletada(Tarea tarea) {
        tarea.setCompletada(!tarea.isCompletada());
        repository.actualizar(tarea);
    }
}
