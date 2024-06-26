package com.campuslands.modules.paymenttype.domain;

public class Paymenttype {
    private int id;
    private String name;

    
    public Paymenttype() {}

    public Paymenttype(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Paymenttype(String name){
        this.name = name;
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


    
}
