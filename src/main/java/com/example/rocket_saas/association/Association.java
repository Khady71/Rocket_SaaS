package com.example.rocket_saas.association;

import com.example.rocket_saas.user.User;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Association {

    @Id
    private Long id;
    private String name;
    private String city;
    private String country;
    private String logo;
    private String description;
    private Date createdAt;
    @OneToOne
    @JoinColumn(name = "admin_id")
    private User admin;

    public Association(String name, String city, String country, Date createdAt) {
        this.name = name;
        this.city = city;
        this.country = country;
        this.createdAt = createdAt;
    }

    public Association() {

    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
