package com.campuslands.modules.customer.domain;

public class Customer {

    protected int id;
    protected String name;
    protected int age;
    protected int idDocument;
    
    public Customer(int id, String name, int age, int idDocument) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.idDocument = idDocument;
    }

    public Customer() {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getIdDocument() {
        return idDocument;
    }

    public void setIdDocument(int idDocument) {
        this.idDocument = idDocument;
    }

    @Override
    public String toString(){
        return String.format("Cliente id: %d\n  nombre: %s -- edad: %d -- idTipo de documento: %d", id, name, age, idDocument);
    }

}
