package com.example.CertantChallenge.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @Column(name = "dni")
    @NonNull
    private String dni; //documento de identidad

    @Column(name = "name")
    @NonNull
    private String name;

    @Column(name = "last_name")
    @NonNull
    private String lastName;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "client_dni")
    @JsonIgnore
    @JsonBackReference
    private List<Car> cars = new ArrayList<>();

    public Client() {
    }

    public Client(@NonNull String dni, @NonNull String name, @NonNull String lastName) {
        this.dni = dni;
        this.name = name;
        this.lastName = lastName;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(@NonNull String dni) {
        this.dni = dni;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getLastName() {
        return lastName;
    }

    public void setLastName(@NonNull String lastName) {
        this.lastName = lastName;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        return "Client{" + "dni='" + dni + '\'' + ", name='" + name + '\'' + ", lastName='" + lastName + '\'' + '}';
    }
}
