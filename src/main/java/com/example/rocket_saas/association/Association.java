package com.example.rocket_saas.association;

import com.example.rocket_saas.user.UserAsso;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Locale;

@Entity
public class Association {

    @Id
    @SequenceGenerator(
            name = "association_sequence",
            sequenceName = "association_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "association_sequence"

    )
    private Long id;

    private String name;
    private String sigle;
    private String city;
    private String country;
    private String logo;
    private String description;
    private Long createdAt;
    @OneToOne
    @JoinColumn(name = "admin_id")
    private UserAsso admin;

    public Association(String name, String sigle,String city, String country, Long createdAt) {
        this.name = name;
        this.sigle = sigle.toLowerCase(Locale.ROOT);
        this.city = city;
        this.country = country;
        this.createdAt = createdAt;
    }

    public Association(String name, String sigle, String city, String description) {
        this.name = name;
        this.sigle = sigle;
        this.city = city;
        this.description = description;
    }

    public Association(String sigle, String description) {
        this.sigle = sigle;
        this.description = description;
    }

    public Association() {

    }

    public UserAsso getAdmin() {
        return admin;
    }

    public void setAdmin(UserAsso admin) {
        this.admin = admin;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getSigle() {
        return sigle;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getLogo() {
        return logo;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }
}
