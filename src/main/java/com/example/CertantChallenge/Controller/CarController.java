package com.example.CertantChallenge.Controller;

import com.example.CertantChallenge.Entities.Car;
import com.example.CertantChallenge.Service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    //basic CRUD operations for the Car entity
    @GetMapping("/cars")
    @ResponseBody
    public ResponseEntity<List<Car>> getCars() {
        return ResponseEntity.ok(carService.findAll());
    }

    @GetMapping("/cars/{carLicensePlate}")
    @ResponseBody
    public ResponseEntity<Car> getCarById(@PathVariable String carLicensePlate) {

        return ResponseEntity.ok(carService.findById(carLicensePlate));
    }

    @DeleteMapping("/cars/{carLicensePlate}")
    @ResponseBody
    public ResponseEntity<Void> deleteCar(@PathVariable String carLicensePlate) {
        carService.delete(carLicensePlate);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/cars")
    @ResponseBody
    public ResponseEntity<Car> createCar(Car car) {
        return ResponseEntity.ok(carService.create(car));
    }

    @PutMapping("/cars")
    @ResponseBody
    public ResponseEntity<Car> updateCar(Car car) {
        return ResponseEntity.ok(carService.update(car));
    }


    @GetMapping("/carsByStatus/{status}")
    @ResponseBody
    public ResponseEntity<List<Car>> getCarsByStatus(@PathVariable String status) {
        return ResponseEntity.ok(carService.findByStatus(status));
    }


}
