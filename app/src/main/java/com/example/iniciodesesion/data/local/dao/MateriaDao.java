package com.example.iniciodesesion.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.iniciodesesion.data.local.entities.Materia;

import java.util.List;

@Dao
public interface MateriaDao {
    @Query("SELECT * FROM materias ORDER BY fecha_creacion DESC")
    LiveData<List<Materia>> obtenerTodasLasMaterias();

    @Query("SELECT * FROM materias WHERE id = :id")
    LiveData<Materia> obtenerMateriaPorId(int id);

    @Insert
    long insertar(Materia materia);

    @Update
    void actualizar(Materia materia);

    @Delete
    void eliminar(Materia materia);

    @Query("DELETE FROM materias WHERE id = :id")
    void eliminarPorId(int id);

    @Query("SELECT COUNT(*) FROM materias WHERE nombre = :nombre")
    int contarPorNombre(String nombre);
}
