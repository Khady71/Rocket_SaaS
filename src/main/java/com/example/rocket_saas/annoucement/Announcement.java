package com.example.rocket_saas.annoucement;

import com.example.rocket_saas.association.Association;
import com.example.rocket_saas.user.UserAsso;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Announcement {
    @Id
    @SequenceGenerator(
            name = "announcement_sequence",
            sequenceName = "announcement_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "announcement_sequence"

    )
    private Long id;
    private String title;
    private String content;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserAsso author;
    private Date date;
    @ManyToOne
    @JoinColumn(name = "association_id")
    private Association association;


    public Association getAssociation() {
        return association;
    }

    public void setAssociation(Association association) {
        this.association = association;
    }


    public UserAsso getAuthor() {
        return author;
    }

    public void setAuthor(UserAsso author) {
        this.author = author;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
