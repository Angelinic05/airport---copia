package com.campuslands.modules.manufacture.domain;

public class Manufacture {
    int id;
    String name;

    public Manufacture(){}

    public Manufacture(String name){
        this.name = name;
    }

    public Manufacture(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
}
