package com.example.iniciodesesion.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.iniciodesesion.data.local.entities.HorarioCelda;

import java.util.List;

@Dao
public interface HorarioDao {
    @Query("SELECT * FROM horario WHERE hora = :hora AND dia = :dia LIMIT 1")
    LiveData<HorarioCelda> obtenerCelda(int hora, int dia);

    @Query("SELECT * FROM horario")
    LiveData<List<HorarioCelda>> obtenerTodoElHorario();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertarOActualizar(HorarioCelda celda);

    @Query("DELETE FROM horario WHERE hora = :hora AND dia = :dia")
    void eliminarCelda(int hora, int dia);

    @Query("DELETE FROM horario")
    void eliminarTodo();
}