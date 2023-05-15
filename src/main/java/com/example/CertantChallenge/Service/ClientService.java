package com.example.CertantChallenge.Service;

import com.example.CertantChallenge.Entities.Car;
import com.example.CertantChallenge.Entities.Client;
import com.example.CertantChallenge.Exceptions.BadRequestException;
import com.example.CertantChallenge.Exceptions.InternalServerError;
import com.example.CertantChallenge.Exceptions.NotFoundException;
import com.example.CertantChallenge.Repositories.ClientRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.CertantChallenge.Utils.NotUpdateNull.getNullPropertyNames;

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
        try {
            return clientRepository.save(client);
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }

    }

    public Client findByDni(String dni) {
        try {
            Client client = clientRepository.findById(dni).orElse(null);
            if (client == null) {
                throw new NotFoundException("Client with dni " + dni + " does not exist");
            }
            for (Car car : client.getCars()) {
                car.getInspections().forEach(inspectionService::updateInspectionStatus);
            }
            return client;
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }

    public List<Client> findAll() {
        try {
            List<Client> clientList = clientRepository.findAll();
            for (Client client : clientList) {
                for (Car car : client.getCars()) {
                    car.getInspections().forEach(inspectionService::updateInspectionStatus);
                }
            }
            return clientList;
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }

    public Client update(Client client) {
        try {
            if (client.getDni() == null) {
                throw new BadRequestException("Client must have a dni");
            }
            Client clientToUpdate = clientRepository.findById(client.getDni()).orElse(null);
            if (clientToUpdate == null) {
                throw new NotFoundException("Client with dni " + client.getDni() + " does not exist");
            }

            BeanUtils.copyProperties(client, clientToUpdate, getNullPropertyNames(client)); // solo actualiza los atributos que no son null e ignora el id
            return clientRepository.save(clientToUpdate);
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }

    public void delete(String dni) {
        try {
            if (!clientRepository.existsById(dni))
                throw new IllegalStateException("Client with dni " + dni + " does not exist");
            clientRepository.deleteById(dni);
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }


}
