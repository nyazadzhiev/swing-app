package models;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class User implements Serializable {
    @Id
    @Column(name = "username")
    private String username;
    private String password;
    @Embedded
    private Person person;
    private Role role;

    @OneToMany(mappedBy = "courier")
    private List<Order> courierOrders;

    @OneToMany(mappedBy = "client")
    private List<Order> clientOrders;

    public User() {
    }

    public User(String username, String password, Person person, Role role) {
        this.username = username;
        this.password = password;
        this.person = person;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
