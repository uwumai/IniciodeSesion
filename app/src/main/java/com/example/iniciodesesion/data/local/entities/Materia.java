package com.example.iniciodesesion.data.local.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "materias")
public class Materia {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "nombre")
    private String nombre;

    @ColumnInfo(name = "color")
    private int color;

    @ColumnInfo(name = "fecha_creacion")
    private long fechaCreacion;

    public Materia(String nombre, int color) {
        this.nombre = nombre;
        this.color = color;
        this.fechaCreacion = System.currentTimeMillis();
    }

    // Getters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public int getColor() { return color; }
    public long getFechaCreacion() { return fechaCreacion; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setColor(int color) { this.color = color; }
    public void setFechaCreacion(long fechaCreacion) { this.fechaCreacion = fechaCreacion; }
}