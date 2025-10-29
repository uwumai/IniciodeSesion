package com.example.iniciodesesion.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.iniciodesesion.data.local.AppDatabase;
import com.example.iniciodesesion.data.local.dao.TareaDao;
import com.example.iniciodesesion.data.local.entities.Tarea;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TareaRepository {
    private final TareaDao tareaDao;
    private final ExecutorService executorService;

    public TareaRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        tareaDao = db.tareaDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<Tarea>> obtenerTareasPorMateria(int materiaId) {
        return tareaDao.obtenerTareasPorMateria(materiaId);
    }

    public LiveData<List<Tarea>> obtenerTareasPendientes() {
        return tareaDao.obtenerTareasPendientes();
    }

    public LiveData<List<Tarea>> obtenerTodasLasTareas() {
        return tareaDao.obtenerTodasLasTareas();
    }

    public void insertar(Tarea tarea) {
        executorService.execute(() -> tareaDao.insertar(tarea));
    }

    public void actualizar(Tarea tarea) {
        executorService.execute(() -> tareaDao.actualizar(tarea));
    }

    public void eliminar(Tarea tarea) {
        executorService.execute(() -> tareaDao.eliminar(tarea));
    }
}
