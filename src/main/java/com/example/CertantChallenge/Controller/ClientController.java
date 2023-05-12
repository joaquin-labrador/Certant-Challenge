package com.example.CertantChallenge.Controller;

import com.example.CertantChallenge.Entities.Client;
import com.example.CertantChallenge.Service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    @ResponseBody
    public ResponseEntity<List<Client>> getClients() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @GetMapping("/clients/{dni}")
    @ResponseBody
    public ResponseEntity<Client> getClientById(@PathVariable String dni) {
        return ResponseEntity.ok(clientService.findByDni(dni));
    }

    @PostMapping("/clients")
    @ResponseBody
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        return ResponseEntity.ok(clientService.create(client));
    }

    @PutMapping("/clients")
    @ResponseBody
    public ResponseEntity<Client> updateClient(@RequestBody Client client) {
        return ResponseEntity.ok(clientService.update(client));
    }

    @DeleteMapping("/clients/{dni}")
    @ResponseBody
    public ResponseEntity<Client> deleteClient(@PathVariable String dni) {
        clientService.delete(dni);
        return ResponseEntity.ok().build();
    }
}
