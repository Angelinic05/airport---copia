package com.campuslands.modules.tripbooking.domain;

import java.sql.Date;

public class Tripbooking {
    private int id;
    private Date date;
    private int idTrip;

    public Tripbooking() {}

    public Tripbooking(int id, Date date, int idTrip) {
        this.id = id;
        this.date = date;
        this.idTrip = idTrip;

    }

    public Tripbooking(Date date, int idTrip) {
        this.date = date;
        this.idTrip = idTrip;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIdTrip() {
        return this.idTrip;
    }

    public void setIdTrip(int idTrip) {
        this.idTrip = idTrip;
    }
}
