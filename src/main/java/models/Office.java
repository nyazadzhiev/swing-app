package models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Office {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public int id;
    public String city;
    public String name;
    public String phoneNumber;

    @OneToMany(mappedBy = "office")
    public List<User> users;

    public Office() {
    }

    public Office(String city, String name, String phoneNumber) {
        this.city = city;
        this.name = name;
        this.phoneNumber = phoneNumber;
        users = new ArrayList<>();
    }

    @Override
    public String toString() {
        return city + "                                                                                                                                  " + name;
    }
}
