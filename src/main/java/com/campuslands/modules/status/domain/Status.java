package com.campuslands.modules.status.domain;

public class Status {
    private int id;
    private String nombre;


    public Status() {}

    public Status(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;

    }

    public Status(String nombre) {
        this.nombre = nombre;
    }
    
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
