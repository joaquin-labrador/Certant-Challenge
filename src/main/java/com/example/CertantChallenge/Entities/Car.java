package com.example.CertantChallenge.Entities;

import jakarta.persistence.*;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @Column(name = "car_license_plate")
    private String carLicensePlate; //patente
    @Column(name = "brand")
    @NonNull
    private String brand;
    @Column(name = "model")
    @NonNull
    private String model;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "car_license_plate")
    @NonNull
    private List<Inspection> inspections = new ArrayList<>();

    public Car() {
    }

    public Car(String carLicensePlate, @NonNull String brand, @NonNull String model) {
        this.carLicensePlate = carLicensePlate;
        this.brand = brand;
        this.model = model;
    }

    public String getCarLicensePlate() {
        return carLicensePlate;
    }

    public void setCarLicensePlate(String carLicensePlate) {
        this.carLicensePlate = carLicensePlate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Inspection> getInspections() {
        return inspections;
    }

    public void setInspections(List<Inspection> inspections) {
        this.inspections = inspections;
    }

    @Override
    public String toString() {
        return "Car{" + "carLicensePlate='" + carLicensePlate + '\'' + ", brand='" + brand + '\'' + ", model='" + model + '\'' + ", inspections=" + inspections + '}';
    }
}
