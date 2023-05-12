package com.example.CertantChallenge;

import com.example.CertantChallenge.Entities.Car;
import com.example.CertantChallenge.Entities.Client;
import com.example.CertantChallenge.Entities.Inspection;
import com.example.CertantChallenge.Entities.Inspector;
import com.example.CertantChallenge.Enums.VTVStatus;
import com.example.CertantChallenge.Repositories.CarRepository;
import com.example.CertantChallenge.Repositories.ClientRepository;
import com.example.CertantChallenge.Repositories.InspectionRepository;
import com.example.CertantChallenge.Repositories.InspectorRepository;
import com.example.CertantChallenge.Service.CarService;
import com.example.CertantChallenge.Service.ClientService;
import com.example.CertantChallenge.Service.InspectionService;
import com.example.CertantChallenge.Service.InspectorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class CertantChallengeApplicationTests {
    @Mock
    private ClientRepository clientRepository;
    @InjectMocks
    private ClientService clientService;
    @Mock
    private InspectionRepository inspectionRepository;
    @InjectMocks
    private InspectionService inspectionService;
    @Mock
    private InspectorRepository inspectorRepository;
    @InjectMocks
    private InspectorService inspectorService;

    @Mock
    private CarRepository carRepository;
    @InjectMocks
    private CarService carService;

    //global variables:
    private List<Car> carsMocks;
    private List<Client> clientsMocks;
    private List<Inspector> inspectorsMocks;
    private List<Inspection> inspectionsMocks;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        //create the objects
        carsMocks = new ArrayList<>();
        Car car1 = new Car("ABC123", "Ford", "Fiesta");
        Car car2 = new Car("DFV464", "Chevrolet", "Corsa");
        carsMocks.add(car1);
        carsMocks.add(car2);

        clientsMocks = new ArrayList<>();
        Client client1 = new Client("12345678", "Juan", "Perez");
        Client client2 = new Client("4342345345", "Marcos", "Gomez");
        clientsMocks.add(client1);
        clientsMocks.add(client2);

        inspectorsMocks = new ArrayList<>();
        Inspector inspector1 = new Inspector(null, "Sandra", "Perez");
        Inspector inspector2 = new Inspector(null, "Joaquin", "Garcia");
        inspectorsMocks.add(inspector1);
        inspectorsMocks.add(inspector2);

        inspectionsMocks = new ArrayList<>();
        Inspection inspection1 = new Inspection(LocalDateTime.of(2022, 5, 1, 10, 0), VTVStatus.toString(VTVStatus.APTO), true);
        Inspection inspection2 = new Inspection(LocalDateTime.of(2022, 5, 1, 10, 0), VTVStatus.toString(VTVStatus.APTO), true);

        inspector1.setInspections(List.of(inspection1));
        inspector2.setInspections(List.of(inspection2));

        car1.setInspections(List.of(inspection1));
        car2.setInspections(List.of(inspection2));

        client1.setCars(List.of(car1));
        client2.setCars(List.of(car2));


    }

    @Test
    void findAllCars() {
        when(carRepository.findAll()).thenReturn(Arrays.asList(carsMocks.get(0), carsMocks.get(1)));
        assertNotNull(carService.findAll());
    }

    @Test
    void findCarByPlate() {
        when(carRepository.findById("ABC123")).thenReturn(java.util.Optional.ofNullable(carsMocks.get(0)));
    }

    @Test
    void verifyInspectionsHaveInspector() {
        when(carRepository.findById("ABC123")).thenReturn(java.util.Optional.ofNullable(carsMocks.get(0)));
    }

    @Test
    void verifyInspectionsHaveCar() {
        when(carRepository.findById("ABC123")).thenReturn(java.util.Optional.ofNullable(carsMocks.get(0)));
        Car car = carsMocks.get(0);
        assertNotNull(car.getInspections());
    }

    @Test
    void verifyClientHasCars() {
        when(clientRepository.findById("12345678")).thenReturn(java.util.Optional.ofNullable(clientsMocks.get(0)));
        Client client = clientsMocks.get(0);
        assertNotNull(client.getCars());
        client.getCars().stream().forEach(car -> assertNotNull(car.getInspections()));
    }


}
