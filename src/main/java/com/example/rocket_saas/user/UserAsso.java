package com.example.rocket_saas.user;

import com.example.rocket_saas.association.Association;
import jakarta.persistence.*;

@Entity
public class UserAsso {




    @Id
    @SequenceGenerator(
            name = "UserAsso_sequence",
            sequenceName = "UserAsso_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "UserAsso_sequence"

    )
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Role role;
    @ManyToOne
    @JoinColumn(name = "association_id")
    private Association association;
    private String password;

    public UserAsso() {

    }

    public UserAsso(String firstName, String lastName, String email, String phone, Role role, Association association) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.association = association;
    }

    public Association getAssociation() {
        return association;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Role getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public void setAssociation(Association association) {
        this.association = association;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
