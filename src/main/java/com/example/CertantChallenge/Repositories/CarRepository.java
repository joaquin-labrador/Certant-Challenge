package com.example.CertantChallenge.Repositories;

import com.example.CertantChallenge.Entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, String> {
    public List<Car> findByInspectionsStatus(String status);
}
