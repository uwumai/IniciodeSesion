package com.example.iniciodesesion.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.iniciodesesion.data.local.entities.Tarea;

import java.util.List;

@Dao
public interface TareaDao {
    @Query("SELECT * FROM tareas WHERE materia_id = :materiaId ORDER BY fecha_creacion DESC")
    LiveData<List<Tarea>> obtenerTareasPorMateria(int materiaId);

    @Query("SELECT * FROM tareas WHERE completada = 0 ORDER BY fecha_creacion DESC")
    LiveData<List<Tarea>> obtenerTareasPendientes();

    @Query("SELECT * FROM tareas ORDER BY fecha_creacion DESC")
    LiveData<List<Tarea>> obtenerTodasLasTareas();

    @Insert
    long insertar(Tarea tarea);

    @Update
    void actualizar(Tarea tarea);

    @Delete
    void eliminar(Tarea tarea);

    @Query("DELETE FROM tareas WHERE materia_id = :materiaId")
    void eliminarPorMateria(int materiaId);
}
