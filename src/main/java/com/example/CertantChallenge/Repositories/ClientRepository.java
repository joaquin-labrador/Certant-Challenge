package com.example.CertantChallenge.Repositories;

import com.example.CertantChallenge.Entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, String> {
}
