package com.example.CertantChallenge.Service;

import com.example.CertantChallenge.Entities.Car;
import com.example.CertantChallenge.Entities.Client;
import com.example.CertantChallenge.Exceptions.BadRequestException;
import com.example.CertantChallenge.Exceptions.NotFoundException;
import com.example.CertantChallenge.Repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final InspectionService inspectionService;

    public ClientService(ClientRepository clientRepository, InspectionService inspectionService) {
        this.clientRepository = clientRepository;
        this.inspectionService = inspectionService;
    }

    //basic CRUD operations

    public Client create(Client client) {
        return clientRepository.save(client);
    }

    public Client findByDni(String dni) {
        Client client = clientRepository.findById(dni).orElse(null);
        if (client == null) {
            throw new NotFoundException("Client with dni " + dni + " does not exist");
        }
        for (Car car : client.getCars()) {
            car.getInspections().forEach(inspectionService::updateInspectionStatus);
        }
        return client;
    }

    public List<Client> findAll() {
        List<Client> clientList = clientRepository.findAll();
        for (Client client : clientList) {
            for (Car car : client.getCars()) {
                car.getInspections().forEach(inspectionService::updateInspectionStatus);
            }
        }
        return clientList;
    }

    public Client update(Client client) {
        if (client.getDni() == null) {
            throw new BadRequestException("Client must have a dni");
        } Client clientToUpdate = clientRepository.findById(client.getDni()).orElse(null);
        if (clientToUpdate != null) {
            clientToUpdate.setName(client.getName());
            clientToUpdate.setLastName(client.getLastName());
            return clientRepository.save(clientToUpdate);
        }
        return null;
    }

    public void delete(String dni) {
        if (!clientRepository.existsById(dni))
            throw new IllegalStateException("Client with dni " + dni + " does not exist");

        clientRepository.deleteById(dni);
    }


}
