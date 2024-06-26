package com.campuslands.modules.revision.domain;

import java.sql.Date;

public class Revision {
    private int id;
    private Date revisionDate;
    private int idPlane;

    public Revision(int id, Date revisionDate, int idPlane){
        this.id = id;
        this.revisionDate = revisionDate;
        this.idPlane = idPlane;
    }


    public Revision(Date revisionDate, int idPlane){
        this.revisionDate = revisionDate;
        this.idPlane = idPlane;
    }

    public Revision() {}

    // Getters y setters
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getRevisionDate() {
        return this.revisionDate;
    }

    public void setRevisionDate(Date revisionDate) {
        this.revisionDate = revisionDate;
    }

    public int getIdPlane() {
        return this.idPlane;
    }

    public void setIdPlane(int idPlane) {
        this.idPlane = idPlane;
    }
}
