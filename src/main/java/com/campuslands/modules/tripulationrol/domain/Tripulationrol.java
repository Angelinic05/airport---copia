package com.campuslands.modules.tripulationrol.domain;

public class Tripulationrol {
    private int id;
    private String name;

    public Tripulationrol() {}

    public Tripulationrol(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Tripulationrol(String name) {
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
