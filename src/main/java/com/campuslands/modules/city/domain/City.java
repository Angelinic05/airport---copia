package com.campuslands.modules.city.domain;

public class City {
    
    protected int id;
    protected String name;
    protected int idCountry;
    
    public City() {
    }

    public City(String name, int idCountry) {
        this.name = name;
        this.idCountry = idCountry;
    }

    public City(int id, String name, int idCountry) {
        this.id = id;
        this.name = name;
        this.idCountry = idCountry;
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

    public int getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(int idCountry) {
        this.idCountry = idCountry;
    }

    @Override
    public String toString() {
        return "City [id=" + id + ", name=" + name + ", idCountry=" + idCountry + "]";
    }

    

}
