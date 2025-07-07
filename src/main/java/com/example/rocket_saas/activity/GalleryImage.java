package com.example.rocket_saas.activity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class GalleryImage {
    @Id
    @GeneratedValue
    private Long id;

    private String url;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}