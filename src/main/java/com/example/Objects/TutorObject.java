package com.example.Objects;

import javax.print.DocFlavor;

/**
 * Created by Sigal on 5/23/2016.
 */
public class TutorObject {


    public int oid;
    public String name;
    public String telephoneNumber;
    public String email;
    public int pricePerHour;
    public boolean activePayment;

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }
    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(int pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public boolean isActivePayment() {
        return activePayment;
    }

    public void setActivePayment(boolean activePayment) {
        this.activePayment = activePayment;
    }
}


