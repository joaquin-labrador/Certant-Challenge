package com.example.CertantChallenge.Service;

import com.example.CertantChallenge.Entities.Inspector;
import com.example.CertantChallenge.Exceptions.NotFoundException;
import com.example.CertantChallenge.Repositories.InspectorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InspectorService {

    private final InspectorRepository inspectorRepository;

    //Injecting the InspectorRepository
    public InspectorService(InspectorRepository inspectorRepository) {
        this.inspectorRepository = inspectorRepository;
    }

    //Basic CRUD operations

    public Inspector create(Inspector inspector) {
        return inspectorRepository.save(inspector);
    }

    public Inspector findById(Long id) {
        Inspector inspector = inspectorRepository.findById(id).orElse(null);
        if (inspector == null) {
            throw new NotFoundException("Inspector with id " + id + " does not exist");
        }
        return inspector;
    }

    public List<Inspector> findAll() {
        return inspectorRepository.findAll();
    }

    public Inspector update(Inspector inspector) {
        if (!inspectorRepository.existsById(inspector.getId())) {
            throw new RuntimeException("Inspector not found");
        }
        return inspectorRepository.save(inspector);
    }

    public void delete(Long id) {
        inspectorRepository.deleteById(id);
    }


}
