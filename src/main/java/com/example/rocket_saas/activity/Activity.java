package com.example.rocket_saas.activity;

import com.example.rocket_saas.association.Association;
import com.example.rocket_saas.user.UserAsso;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Activity {
    @Id
    @SequenceGenerator(
            name = "activity_sequence",
            sequenceName = "activity_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "activity_sequence"

    )
    private Long id;
    private String name;
    private String description;
    private Date Date;
    @ElementCollection
    private List<String> gallery = new ArrayList<>();    @ManyToOne
    @JoinColumn(name = "association_id")
    private Association association;
    @ManyToOne
    @JoinColumn(name = "created_by_id")
    private UserAsso createdBy;

    public UserAsso getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserAsso createdBy) {
        this.createdBy = createdBy;
    }

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
