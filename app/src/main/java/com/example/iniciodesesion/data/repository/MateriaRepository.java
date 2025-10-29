package com.example.iniciodesesion.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.iniciodesesion.data.local.AppDatabase;
import com.example.iniciodesesion.data.local.dao.MateriaDao;
import com.example.iniciodesesion.data.local.entities.Materia;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MateriaRepository {
    private final MateriaDao materiaDao;
    private final LiveData<List<Materia>> todasLasMaterias;
    private final ExecutorService executorService;

    public MateriaRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        materiaDao = db.materiaDao();
        todasLasMaterias = materiaDao.obtenerTodasLasMaterias();
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<Materia>> obtenerTodasLasMaterias() {
        return todasLasMaterias;
    }

    public LiveData<Materia> obtenerMateriaPorId(int id) {
        return materiaDao.obtenerMateriaPorId(id);
    }

    public void insertar(Materia materia) {
        executorService.execute(() -> materiaDao.insertar(materia));
    }

    public void actualizar(Materia materia) {
        executorService.execute(() -> materiaDao.actualizar(materia));
    }

    public void eliminar(Materia materia) {
        executorService.execute(() -> materiaDao.eliminar(materia));
    }
}