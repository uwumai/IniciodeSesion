package com.example.iniciodesesion.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.iniciodesesion.data.local.AppDatabase;
import com.example.iniciodesesion.data.local.dao.HorarioDao;
import com.example.iniciodesesion.data.local.entities.HorarioCelda;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HorarioRepository {
    private final HorarioDao horarioDao;
    private final ExecutorService executorService;

    public HorarioRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        horarioDao = db.horarioDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<HorarioCelda> obtenerCelda(int hora, int dia) {
        return horarioDao.obtenerCelda(hora, dia);
    }

    public LiveData<List<HorarioCelda>> obtenerTodoElHorario() {
        return horarioDao.obtenerTodoElHorario();
    }

    public void insertarOActualizar(HorarioCelda celda) {
        executorService.execute(() -> horarioDao.insertarOActualizar(celda));
    }

    public void eliminarCelda(int hora, int dia) {
        executorService.execute(() -> horarioDao.eliminarCelda(hora, dia));
    }
}
