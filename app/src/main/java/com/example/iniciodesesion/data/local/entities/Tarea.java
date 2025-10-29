package com.example.iniciodesesion.data.local.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "tareas",
        foreignKeys = @ForeignKey(
                entity = Materia.class,
                parentColumns = "id",
                childColumns = "materia_id",
                onDelete = ForeignKey.CASCADE
        ))
public class Tarea {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "materia_id")
    private int materiaId;

    @ColumnInfo(name = "descripcion")
    private String descripcion;

    @ColumnInfo(name = "completada")
    private boolean completada;

    @ColumnInfo(name = "fecha_creacion")
    private long fechaCreacion;

    public Tarea(int materiaId, String descripcion) {
        this.materiaId = materiaId;
        this.descripcion = descripcion;
        this.completada = false;
        this.fechaCreacion = System.currentTimeMillis();
    }

    // Getters
    public int getId() { return id; }
    public int getMateriaId() { return materiaId; }
    public String getDescripcion() { return descripcion; }
    public boolean isCompletada() { return completada; }
    public long getFechaCreacion() { return fechaCreacion; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setMateriaId(int materiaId) { this.materiaId = materiaId; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setCompletada(boolean completada) { this.completada = completada; }
    public void setFechaCreacion(long fechaCreacion) { this.fechaCreacion = fechaCreacion; }
}