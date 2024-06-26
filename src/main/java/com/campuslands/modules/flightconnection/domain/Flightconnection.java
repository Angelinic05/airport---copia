package com.campuslands.modules.flightconnection.domain;

public class Flightconnection {
    int id;
    String connectionNumber;
    int idTrip;
    int idPlane;
    int idAirport;

    public Flightconnection(){}

    public Flightconnection(String connectionNumber, int idTrip, int idPlane, int idAirport){
        this.connectionNumber = connectionNumber;
        this.idTrip = idTrip;
        this.idPlane = idPlane;
        this.idAirport = idAirport;
    }

    public Flightconnection(int id, String connectionNumber, int idTrip, int idPlane, int idAirport) {
        this.id = id;
        this.connectionNumber = connectionNumber;
        this.idTrip = idTrip;
        this.idPlane = idPlane;
        this.idAirport = idAirport;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getConnectionNumber() {
        return connectionNumber;
    }
    public void setConnectionNumber(String connectionNumber) {
        this.connectionNumber = connectionNumber;
    }
    public int getIdTrip() {
        return idTrip;
    }
    public void setIdTrip(int idTrip) {
        this.idTrip = idTrip;
    }
    public int getIdPlane() {
        return idPlane;
    }
    public void setIdPlane(int idPlane) {
        this.idPlane = idPlane;
    }
    public int getIdAirport() {
        return idAirport;
    }
    public void setIdAirport(int idAirport) {
        this.idAirport = idAirport;
    }
    
    @Override
    public String toString(){
        return String.format("ID: %d -- Connection Number:  %s -- IdTrip: %d -- IdPlane: %d -- IdAirport: %d",id, connectionNumber, idTrip, idPlane, idAirport);
    }
}
