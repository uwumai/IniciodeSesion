package com.example.iniciodesesion.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.iniciodesesion.data.local.dao.HorarioDao;
import com.example.iniciodesesion.data.local.dao.MateriaDao;
import com.example.iniciodesesion.data.local.dao.TareaDao;
import com.example.iniciodesesion.data.local.entities.HorarioCelda;
import com.example.iniciodesesion.data.local.entities.Materia;
import com.example.iniciodesesion.data.local.entities.Tarea;

@Database(entities = {Materia.class, Tarea.class, HorarioCelda.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;

    public abstract MateriaDao materiaDao();
    public abstract TareaDao tareaDao();
    public abstract HorarioDao horarioDao();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AppDatabase.class,
                                    "estudiante_database"
                            ).fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
