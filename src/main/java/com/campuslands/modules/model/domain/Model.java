package com.campuslands.modules.model.domain;

public class Model {
    int id;
    String name;
    int idManufactures;

    public Model (){}

    public Model (String name, int idManufactures){
        this.name = name;
        this.idManufactures = idManufactures;
    }

    

    public Model(int id, String name, int idManufactures) {
        this.id = id;
        this.name = name;
        this.idManufactures = idManufactures;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdManufactures() {
        return idManufactures;
    }

    public void setIdManufactures(int idManufactures) {
        this.idManufactures = idManufactures;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
