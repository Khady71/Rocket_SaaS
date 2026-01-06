package com.example.rocket_saas.activity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class ActivityRegistration {

    @Id
    @SequenceGenerator(
            name = "activity_registration_sequence",
            sequenceName = "activity_registration_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "activity_registration_sequence"

    )
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
