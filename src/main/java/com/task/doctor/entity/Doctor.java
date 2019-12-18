package com.task.doctor.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "doctor")
public class Doctor {
    @Id
    @Column(name = "npi_id")
    private Long npiID;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "address")
    private String address;

    public Doctor() {
    }

    public Doctor(Long npiID, String lastName, String firstName, String address) {
        this.npiID = npiID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
    }

    public Long getNpiID() {
        return npiID;
    }

    public void setNpiID(Long npiID) {
        this.npiID = npiID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
