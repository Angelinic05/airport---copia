package com.campuslands.modules.country.domain;

public class Country {
    
    protected int id;
    protected String name;
    
    public Country(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Country(String name){
        this.name = name;
    }

    public Country() {
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
    public String toString(){
        return String.format("id: %d -- Pais: %s", id, name);
    }

}
