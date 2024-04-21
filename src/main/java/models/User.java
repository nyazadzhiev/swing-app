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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_id")

    private Office office;
    @Embedded
    private Person person;
    private Role role;

    @OneToMany(mappedBy = "courier")
    private List<Order> courierOrders;

    @OneToMany(mappedBy = "client")
    private List<Order> clientOrders;

    public Office getOffice() {
        return office;
    }

    public User() {
    }

    public User(String username, String password, Person person, Role role, Office office) {
        this.username = username;
        this.password = password;
        this.person = person;
        this.role = role;
        this.office = office;
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
