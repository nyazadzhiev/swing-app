package models;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class Person implements Serializable {
    private String name;
    private String phoneNumber;
    private String address;
    private int yearsExperience;

    public Person() {
    }

    public Person(String name, String phoneNumber, String address, int yearsExperience) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.yearsExperience = yearsExperience;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getYearsExperience() {
        return yearsExperience;
    }

    public void setYearsExperience(int yearsExperience) {
        this.yearsExperience = yearsExperience;
    }
}
