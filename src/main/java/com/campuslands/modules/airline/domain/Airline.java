package com.campuslands.modules.airline.domain;

public class Airline{
    protected int id;
    protected String name;
    
    public Airline(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Airline(String name) {
        this.name = name;
    }

    public Airline() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Airline id=%d, name='%s' ", id, name);
    }

}