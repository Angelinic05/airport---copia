package com.campuslands.modules.tripcrew.domain;

public class Tripcrew {
    private int id;
    private int idEmployee;
    private int idConnection;

    public Tripcrew() {}

    public Tripcrew(int id, int idEmployee, int idConnection) {
        this.id = id;
        this.idEmployee = idEmployee;
        this.idConnection = idConnection;
    }

    public Tripcrew(int idEmployee, int idConnection) {
        this.idEmployee = idEmployee;
        this.idConnection = idConnection;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEmployee() {
        return this.idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public int getIdConnection() {
        return this.idConnection;
    }

    public void setIdConnection(int idConnection) {
        this.idConnection = idConnection;
    }
}
