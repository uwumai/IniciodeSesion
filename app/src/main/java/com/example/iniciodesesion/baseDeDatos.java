package com.example.iniciodesesion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

class BaseDeDatos extends SQLiteOpenHelper {

    private static final String NOMBRE_BD = "apuntes.db";
    private static final int VERSION_BD = 1;

    public BaseDeDatos(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE materias (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS materias");
        onCreate(db);
    }

    // 🔹 Agregar materia (si no existe ya)
    public void agregarMateria(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) return;

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT nombre FROM materias WHERE nombre = ?", new String[]{nombre});

        if (cursor.getCount() == 0) {
            ContentValues valores = new ContentValues();
            valores.put("nombre", nombre);
            db.insert("materias", null, valores);
        }

        cursor.close();
        db.close();
    }

    // 🔹 Obtener todas las materias guardadas
    public List<String> obtenerMaterias() {
        List<String> materias = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT nombre FROM materias", null);
        if (cursor.moveToFirst()) {
            do {
                materias.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return materias;
    }
}