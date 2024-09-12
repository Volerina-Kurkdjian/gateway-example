package com.example.gateway.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

public class Vehicle {

    private Status status;

    private String owner;

    @JsonProperty("association_date")
    private Date associationDate;

    public Vehicle() {
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Date getAssociationDate() {
        return associationDate;
    }

    public void setAssociationDate(Date associationDate) {
        this.associationDate = associationDate;
    }
}
