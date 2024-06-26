package com.campuslands.modules.plane.domain;

import java.sql.Date;

public class Plane {
    int id;
    int capacity;
    Date fabricationDate;
    int idStatus;
    int idModel;

    public Plane(){}

    public Plane(int capacity, Date fabricationDate, int idStatus, int idModel){
        this.capacity = capacity;
        this.fabricationDate = fabricationDate;
        this.idStatus = idStatus;
        this.idModel = idModel;
    }

    public Plane(int id, int capacity, Date fabricationDate, int idStatus, int idModel) {
        this.id = id;
        this.capacity = capacity;
        this.fabricationDate = fabricationDate;
        this.idStatus = idStatus;
        this.idModel = idModel;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Date getFabricationDate() {
        return fabricationDate;
    }

    public void setFabricationDate(Date fabricationDate) {
        this.fabricationDate = fabricationDate;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public int getIdModel() {
        return idModel;
    }

    public void setIdModel(int idModel) {
        this.idModel = idModel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
}
