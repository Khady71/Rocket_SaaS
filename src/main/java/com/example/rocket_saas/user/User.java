package com.example.rocket_saas.user;

import com.example.rocket_saas.association.Association;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class User {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private int phone;
    private Role role;
    @ManyToOne
    @JoinColumn(name = "association_id")
    private Association association;
    private String password;

    public Association getAssociation() {
        return association;
    }

    public void setAssociation(Association association) {
        this.association = association;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
