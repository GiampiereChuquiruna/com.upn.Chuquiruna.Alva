package com.example.comupnchuquirunaalvafinal.entities;

public class Duelista {
    public int id;
    public String nombre;

    public Duelista(){
    }

    public Duelista(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
