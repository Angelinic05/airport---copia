package com.campuslands.modules.gate.domain;

public class Gate {
    int id;
    String gateNumber;
    int idAirport;
    
    public Gate(){}

    public Gate(String gateNumber, int idAirport){
        this.gateNumber = gateNumber;
        this.idAirport = idAirport;
    }

    public Gate(int id, String gateNumber, int idAirport) {
        this.id = id;
        this.gateNumber = gateNumber;
        this.idAirport = idAirport;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    } 

    public String getGateNumber() {
        return gateNumber;
    }

    public void setGateNumber(String gateNumber) {
        this.gateNumber = gateNumber;
    }

    public int getIdAirport() {
        return idAirport;
    }

    public void setIdAirport(int idAirport) {
        this.idAirport = idAirport;
    } 
}
