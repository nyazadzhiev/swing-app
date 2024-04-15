package models;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class Person implements Serializable {
    private String name;
    private String phoneNumber;
    private String address;
    private String officeAddress;
    private String officeName;
    private int yearsExperience;

    public Person() {
    }

    public Person(String name, String phoneNumber, String address, String officeAddress, String officeName, int yearsExperience) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.officeAddress = officeAddress;
        this.officeName = officeName;
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

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public int getYearsExperience() {
        return yearsExperience;
    }

    public void setYearsExperience(int yearsExperience) {
        this.yearsExperience = yearsExperience;
    }
}
