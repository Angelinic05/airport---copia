package com.campuslands.modules.revemployee.domain;

public class Revemployee {
    int id;
    int idEmployee;
    int idRevision;

    public Revemployee (){}

    public Revemployee (int idEmployee, int idRevision){
        this.idEmployee = idEmployee;
        this.idRevision = idRevision;
    }


    public Revemployee(int id, int idEmployee, int idRevision) {
        this.id = id;
        this.idEmployee = idEmployee;
        this.idRevision = idRevision;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public int getIdRevision() {
        return idRevision;
    }

    public void setIdRevision(int idRevision) {
        this.idRevision = idRevision;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
}
