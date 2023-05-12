package com.example.CertantChallenge.Entities;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "inspector")
public class Inspector {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(name = "first_name")
    private String firstName;

    @NonNull
    @Column(name = "last_name")
    private String lastName;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "inspector_id")
    private List<Inspection> inspections = new ArrayList<>();

    public Inspector(Long id, @NonNull String firstName, @NonNull String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Inspector() {

    }

    public Inspector(Long id, @NonNull String firstName, @NonNull String lastName, List<Inspection> inspections) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.inspections = inspections;
    }

    @Override
    public String toString() {
        return "Inspector{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", inspections=" + inspections +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NonNull String firstName) {
        this.firstName = firstName;
    }

    public List<Inspection> getInspections() {
        return inspections;
    }

    public void setInspections(List<Inspection> inspections) {
        this.inspections = inspections;
    }

    @NonNull
    public String getLastName() {
        return lastName;
    }

    public void setLastName(@NonNull String lastName) {
        this.lastName = lastName;
    }

}
