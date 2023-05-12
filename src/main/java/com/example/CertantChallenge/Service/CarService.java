package com.example.CertantChallenge.Service;

import com.example.CertantChallenge.Entities.Car;
import com.example.CertantChallenge.Enums.VTVStatus;
import com.example.CertantChallenge.Exceptions.NotFoundException;
import com.example.CertantChallenge.Repositories.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final InspectionService inspectionService;

    //Injecting the CarRepository
    public CarService(CarRepository carRepository, InspectionService inspectionService) {
        this.carRepository = carRepository;
        this.inspectionService = inspectionService;
    }

    //Basic CRUD operations

    public Car create(Car car) {
        return carRepository.save(car);
    }

    public Car findById(String carLicensePlate) {
        Car car = carRepository.findById(carLicensePlate).orElse(null);
        if (car == null) {
            throw new NotFoundException("Car with license plate " + carLicensePlate + " does not exist");
        }
        car.getInspections().forEach(inspectionService::updateInspectionStatus);
        return car;
    }

    public List<Car> findAll() {

        List<Car> carList = carRepository.findAll();
        for (Car car : carList) {
            car.getInspections().forEach(inspectionService::updateInspectionStatus);
        }
        return carList;

    }

    public Car update(Car car) {

        if (!carRepository.existsById(car.getCarLicensePlate())) {
            throw new NotFoundException("Car with license plate " + car.getCarLicensePlate() + " does not exist");
        }
        return carRepository.save(car);
    }

    public void delete(String carLicensePlate) {
        carRepository.deleteById(carLicensePlate);
    }


    public List<Car> findByStatus(String status) {
        status = status.toUpperCase();

        if (!VTVStatus.verifyStatus(status)) {
            throw new NotFoundException("Status " + status + " does not exist");
        }
        List<Car> carList = carRepository.findByInspectionsStatus(status);
        for (Car car : carList) {
            car.getInspections().forEach(inspectionService::updateInspectionStatus);
        }

        String finalStatus = status;
        for (Car car : carList) {
            car.getInspections().removeIf(inspection -> !inspection.getStatus().equals(finalStatus));
            if (car.getInspections().isEmpty()) {
                carList.remove(car);
            }
        }
        return carList;
    }
}
