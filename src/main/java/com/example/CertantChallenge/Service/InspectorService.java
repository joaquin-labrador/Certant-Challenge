package com.example.CertantChallenge.Service;

import com.example.CertantChallenge.Entities.Inspector;
import com.example.CertantChallenge.Exceptions.NotFoundException;
import com.example.CertantChallenge.Repositories.InspectorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.CertantChallenge.Utils.NotUpdateNull.getNullPropertyNames;

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
        try {
            Inspector inspector = inspectorRepository.findById(id).orElse(null);
            if (inspector == null) {
                throw new NotFoundException("Inspector with id " + id + " does not exist");
            }
            return inspector;
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    public List<Inspector> findAll() {
        try {
            return inspectorRepository.findAll();
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    public Inspector update(Inspector inspector) {
        try {
            Inspector inspectorToUpdate = findById(inspector.getId());
            if (inspectorToUpdate == null) {
                throw new RuntimeException("Inspector not found");
            }
            BeanUtils.copyProperties(inspector, inspectorToUpdate, getNullPropertyNames(inspector));
            return inspectorRepository.save(inspectorToUpdate);
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    public void delete(Long id) {
        try {
            inspectorRepository.deleteById(id);
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
    }


}
