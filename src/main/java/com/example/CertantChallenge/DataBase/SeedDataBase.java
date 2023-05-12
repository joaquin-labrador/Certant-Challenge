package com.example.CertantChallenge.DataBase;

import com.example.CertantChallenge.Entities.Car;
import com.example.CertantChallenge.Entities.Client;
import com.example.CertantChallenge.Entities.Inspection;
import com.example.CertantChallenge.Entities.Inspector;
import com.example.CertantChallenge.Enums.VTVStatus;
import com.example.CertantChallenge.Repositories.CarRepository;
import com.example.CertantChallenge.Repositories.ClientRepository;
import com.example.CertantChallenge.Repositories.InspectionRepository;
import com.example.CertantChallenge.Repositories.InspectorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class SeedDataBase implements CommandLineRunner {
    private final CarRepository carRepository;
    private final InspectorRepository inspectorRepository;
    private final InspectionRepository inspectionRepository;
    private final ClientRepository clientRepository;

    public SeedDataBase(CarRepository carRepository, InspectorRepository inspectorRepository, InspectionRepository inspectionRepository, ClientRepository clientRepository) {
        this.carRepository = carRepository;
        this.inspectorRepository = inspectorRepository;
        this.inspectionRepository = inspectionRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //seed data for the database

        Inspector inspector1 = new Inspector(null, "Juan", "Perez");
        Inspector inspector2 = new Inspector(null, "Pedro", "Gomez");
        Inspector inspector3 = new Inspector(null, "Maria", "Lopez");
        Inspector inspector4 = new Inspector(null, "Jose", "Gonzalez");
        Inspector inspector5 = new Inspector(null, "Lucia", "Rodriguez");
        Inspector inspector6 = new Inspector(null, "Manuel", "Martinez");
        Inspector inspector7 = new Inspector(null, "Ana", "Sanchez");
        Inspector inspector8 = new Inspector(null, "Luis", "Fernandez");
        Inspector inspector9 = new Inspector(null, "Laura", "Diaz");
        Inspector inspector10 = new Inspector(null, "Carlos", "Alvarez");
        List<Inspector> inspectors = List.of(inspector1, inspector2, inspector3, inspector4, inspector5, inspector6, inspector7, inspector8, inspector9, inspector10);
        inspectorRepository.saveAll(inspectors);

        Client client1 = new Client("34789678", "Manuel", "Perez");
        Client client2 = new Client("23571621", "María", "González");
        Client client3 = new Client("37213429", "Juan", "López");
        Client client4 = new Client("15296430", "Ana", "Martínez");
        Client client5 = new Client("29436187", "Luis", "Rodríguez");
        Client client6 = new Client("36879125", "Lucía", "Sánchez");
        Client client7 = new Client("29867867", "Daniel", "Labrador");
        Client client8 = new Client("36874689", "Santiago", "Sánchez");
        Client client9 = new Client("24353556", "Marcos", "Rodríguez");
        Client client10 = new Client("36879178", "Lucía", "Marquez");
        List<Client> clients = List.of(client1, client2, client3, client4, client5, client6, client7, client8, client9, client10);
        clientRepository.saveAll(clients);

        Car car1 = new Car("ABC123", "Ford", "Fiesta");
        Car car2 = new Car("DEF456", "Toyota", "Corolla");
        Car car3 = new Car("GHI789", "Chevrolet", "Camaro");
        Car car4 = new Car("JKL012", "Honda", "Civic");
        Car car5 = new Car("MNO345", "BMW", "X5");
        Car car6 = new Car("PQR678", "Nissan", "Sentra");
        Car car7 = new Car("STU901", "Jeep", "Wrangler");
        Car car8 = new Car("VWX234", "Mazda", "CX-5");
        Car car9 = new Car("YZA567", "Hyundai", "Elantra");
        Car car10 = new Car("BCD890", "Kia", "Sportage");
        Car car11 = new Car("EFG123", "Ford", "Focus");
        Car car12 = new Car("HIJ456", "Toyota", "Yaris");
        Car car13 = new Car("KLM789", "Chevrolet", "Onix");
        Car car14 = new Car("NOP012", "Honda", "Fit");
        Car car15 = new Car("QRS345", "BMW", "Serie 3");
        List<Car> cars = List.of(car1, car2, car3, car4, car5, car6, car7, car8, car9, car10, car11, car12, car13, car14, car15);
        carRepository.saveAll(cars);


        Inspection inspection1 = new Inspection(LocalDateTime.of(2022, 5, 1, 10, 0), VTVStatus.toString(VTVStatus.APTO), true);
        Inspection inspection2 = new Inspection(LocalDateTime.of(2022, 6, 3, 14, 30), VTVStatus.toString(VTVStatus.RECHAZADO), false);
        Inspection inspection3 = new Inspection(LocalDateTime.of(2022, 7, 5, 9, 0), VTVStatus.toString(VTVStatus.CONDICIONAL), true);
        Inspection inspection4 = new Inspection(LocalDateTime.of(2022, 8, 7, 11, 30), VTVStatus.toString(VTVStatus.APTO), false);
        Inspection inspection5 = new Inspection(LocalDateTime.of(2022, 9, 9, 13, 0), VTVStatus.toString(VTVStatus.RECHAZADO), true);
        Inspection inspection6 = new Inspection(LocalDateTime.of(2022, 10, 11, 15, 30), VTVStatus.toString(VTVStatus.CONDICIONAL), false);
        Inspection inspection7 = new Inspection(LocalDateTime.of(2022, 11, 13, 8, 0), VTVStatus.toString(VTVStatus.APTO), true);
        Inspection inspection8 = new Inspection(LocalDateTime.of(2022, 12, 15, 10, 30), VTVStatus.toString(VTVStatus.RECHAZADO), false);
        Inspection inspection9 = new Inspection(LocalDateTime.of(2022, 1, 17, 12, 0), VTVStatus.toString(VTVStatus.CONDICIONAL), true);
        Inspection inspection10 = new Inspection(LocalDateTime.of(2022, 2, 19, 13, 30), VTVStatus.toString(VTVStatus.APTO), false);
        Inspection inspection11 = new Inspection(LocalDateTime.of(2022, 3, 21, 15, 0), VTVStatus.toString(VTVStatus.RECHAZADO), true);
        Inspection inspection12 = new Inspection(LocalDateTime.of(2022, 4, 23, 8, 30), VTVStatus.toString(VTVStatus.CONDICIONAL), false);
        Inspection inspection13 = new Inspection(LocalDateTime.of(2022, 5, 25, 10, 0), VTVStatus.toString(VTVStatus.APTO), true);
        Inspection inspection14 = new Inspection(LocalDateTime.of(2022, 6, 27, 11, 30), VTVStatus.toString(VTVStatus.RECHAZADO), false);
        Inspection inspection15 = new Inspection(LocalDateTime.of(2022, 7, 29, 13, 0), VTVStatus.toString(VTVStatus.CONDICIONAL), true);
        Inspection inspection16 = new Inspection(LocalDateTime.of(2022, 8, 31, 14, 30), VTVStatus.toString(VTVStatus.APTO), false);
        Inspection inspection17 = new Inspection(LocalDateTime.of(2022, 9, 2, 8, 0), VTVStatus.toString(VTVStatus.RECHAZADO), true);
        Inspection inspection18 = new Inspection(LocalDateTime.of(2022, 10, 4, 9, 30), VTVStatus.toString(VTVStatus.CONDICIONAL), false);

        List<Inspection> inspections = List.of(inspection1, inspection2, inspection3, inspection4, inspection5, inspection6, inspection7, inspection8, inspection9, inspection10, inspection11, inspection12, inspection13, inspection14, inspection15, inspection16, inspection17, inspection18);
        inspectionRepository.saveAll(inspections);

        inspector1.setInspections(List.of(inspection2, inspection15));
        inspector2.setInspections(List.of(inspection4, inspection5, inspection6));
        inspector3.setInspections(List.of(inspection7, inspection8, inspection9));
        inspector4.setInspections(List.of(inspection3, inspection16));
        inspector5.setInspections(List.of(inspection1));
        inspector6.setInspections(List.of(inspection10));
        inspector7.setInspections(List.of(inspection11, inspection17, inspection18));
        inspector8.setInspections(List.of(inspection12));
        inspector9.setInspections(List.of(inspection13));
        inspector10.setInspections(List.of(inspection14));


        inspectorRepository.saveAll(inspectors);

        car1.setInspections(List.of(inspection1));
        car2.setInspections(List.of(inspection2));
        car3.setInspections(List.of(inspection3, inspection4));
        car4.setInspections(List.of(inspection5, inspection6));
        car5.setInspections(List.of(inspection7));
        car6.setInspections(List.of(inspection9));
        car7.setInspections(List.of(inspection10));
        car8.setInspections(List.of(inspection11));
        car9.setInspections(List.of(inspection12, inspection13));
        car10.setInspections(List.of(inspection14));
        car11.setInspections(List.of(inspection15));
        car12.setInspections(List.of(inspection16));
        car13.setInspections(List.of(inspection17));
        car14.setInspections(List.of(inspection18));
        car15.setInspections(List.of(inspection8));

        carRepository.saveAll(cars);

        client1.setCars(List.of(car1, car2));
        client2.setCars(List.of(car3, car4));
        client3.setCars(List.of(car5));
        client4.setCars(List.of(car6));
        client5.setCars(List.of(car7));
        client6.setCars(List.of(car8, car9));
        client7.setCars(List.of(car10));
        client8.setCars(List.of(car11));
        client9.setCars(List.of(car12));
        client10.setCars(List.of(car13, car14, car15));
        clientRepository.saveAll(clients);


    }
}
