package com.campuslands.modules.flightfare.domain;

public class Flightfare {
    int id;
    String description;
    String details;
    double value;

    public Flightfare(){}

    public Flightfare(String description, String details, double value){
        this.description = description;
        this.details = details;
        this.value = value;
    }

    public Flightfare(int id, String description, String details, double value) {
        this.id = id;
        this.description = description;
        this.details = details;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
   
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetails(){
        return details;
    }

    public void setDetails(String details){
        this.details = details;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

}
