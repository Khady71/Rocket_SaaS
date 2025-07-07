package com.example.rocket_saas.activity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Date;

@Entity
public class ActivityRegistration {

    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activity;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Date registerAt;

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
