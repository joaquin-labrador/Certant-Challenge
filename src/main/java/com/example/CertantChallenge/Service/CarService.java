package com.example.CertantChallenge.Service;

import com.example.CertantChallenge.Entities.Car;
import com.example.CertantChallenge.Enums.VTVStatus;
import com.example.CertantChallenge.Exceptions.InternalServerError;
import com.example.CertantChallenge.Exceptions.NotFoundException;
import com.example.CertantChallenge.Repositories.CarRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.CertantChallenge.Utils.NotUpdateNull.getNullPropertyNames;

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
        try {
            return carRepository.save(car);
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }

    public Car findById(String carLicensePlate) {
        try {
            Car car = carRepository.findById(carLicensePlate).orElse(null);
            if (car == null) {
                throw new NotFoundException("Car with license plate " + carLicensePlate + " does not exist");
            }
            car.getInspections().forEach(inspectionService::updateInspectionStatus);
            return car;
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }

    }

    public List<Car> findAll() {
        try {
            List<Car> carList = carRepository.findAll();
            for (Car car : carList) {
                car.getInspections().forEach(inspectionService::updateInspectionStatus);
            }
            return carList;
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }

    }

    public Car update(Car car) {
        try {
            Car carToUpdate = carRepository.findById(car.getCarLicensePlate()).orElse(null);
            if (carToUpdate == null) {
                throw new NotFoundException("Car with license plate " + car.getCarLicensePlate() + " does not exist");
            }
            BeanUtils.copyProperties(car, carToUpdate, getNullPropertyNames(car)); // solo actualiza los atributos que no son null e ignora el id
            return carRepository.save(carToUpdate);
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }

    public void delete(String carLicensePlate) {
        try {
            carRepository.deleteById(carLicensePlate);
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }


    public List<Car> findByStatus(String status) {
        try {
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
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }
}
