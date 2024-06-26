package com.campuslands.modules.revisiondetail.domain;

public class Revisiondetail {
    private int id;
    private String description;
    private int idEmployee;

    public Revisiondetail(int id, String description, int idEmployee){
        this.id = id;
        this.description = description;
        this.idEmployee = idEmployee;
    }


    public Revisiondetail(String description, int idEmployee){
        this.description = description;
        this.idEmployee = idEmployee;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdEmployee() {
        return this.idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }
}
