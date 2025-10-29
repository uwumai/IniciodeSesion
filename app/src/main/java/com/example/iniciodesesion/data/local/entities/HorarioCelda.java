package com.example.iniciodesesion.data.local.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "horario")
public class HorarioCelda {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "hora")
    private int hora; // 6-22

    @ColumnInfo(name = "dia")
    private int dia; // 1=Lun, 2=Mar, 3=Mie, 4=Jue, 5=Vie

    @ColumnInfo(name = "texto")
    private String texto;

    @ColumnInfo(name = "color")
    private String color;

    public HorarioCelda(int hora, int dia, String texto, String color) {
        this.hora = hora;
        this.dia = dia;
        this.texto = texto;
        this.color = color;
    }

    // Getters
    public int getId() { return id; }
    public int getHora() { return hora; }
    public int getDia() { return dia; }
    public String getTexto() { return texto; }
    public String getColor() { return color; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setHora(int hora) { this.hora = hora; }
    public void setDia(int dia) { this.dia = dia; }
    public void setTexto(String texto) { this.texto = texto; }
    public void setColor(String color) { this.color = color; }
}
