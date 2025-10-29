package com.example.iniciodesesion.ui.apuntes;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.iniciodesesion.data.local.entities.Materia;
import com.example.iniciodesesion.data.repository.MateriaRepository;

import java.util.List;

public class ApuntesViewModel extends AndroidViewModel {
    private final MateriaRepository repository;
    private final LiveData<List<Materia>> materias;

    public ApuntesViewModel(@NonNull Application application) {
        super(application);
        repository = new MateriaRepository(application);
        materias = repository.obtenerTodasLasMaterias();
    }

    public LiveData<List<Materia>> getMaterias() {
        return materias;
    }

    public void agregarMateria(String nombre, int color) {
        Materia materia = new Materia(nombre, color);
        repository.insertar(materia);
    }

    public void actualizarMateria(Materia materia) {
        repository.actualizar(materia);
    }

    public void eliminarMateria(Materia materia) {
        repository.eliminar(materia);
    }
}