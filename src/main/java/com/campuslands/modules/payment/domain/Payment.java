package com.campuslands.modules.payment.domain;

import java.sql.Date;

public class Payment {
    private int id;
    private int idTripBooking;
    private int idPaymentType;
    private int cardNumber;
    private Date date;

    public Payment() {}

    public Payment(int id, int idTripBooking, int idPaymentType, int cardNumber, Date date) {
        this.id = id;
        this.idTripBooking = idTripBooking;
        this.idPaymentType = idPaymentType;
        this.cardNumber = cardNumber;
        this.date = date;
    }

    public Payment(int idTripBooking, int idPaymentType, int cardNumber, Date date) {
        this.idTripBooking = idTripBooking;
        this.idPaymentType = idPaymentType;
        this.cardNumber = cardNumber;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTripBooking() {
        return idTripBooking;
    }

    public void setIdTripBooking(int idTripBooking) {
        this.idTripBooking = idTripBooking;
    }

    public int getIdPaymentType() {
        return idPaymentType;
    }

    public void setIdPaymentType(int idPaymentType) {
        this.idPaymentType = idPaymentType;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }}
