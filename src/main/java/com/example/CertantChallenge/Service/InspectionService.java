package com.example.CertantChallenge.Service;

import com.example.CertantChallenge.Entities.Inspection;
import com.example.CertantChallenge.Enums.VTVStatus;
import com.example.CertantChallenge.Exceptions.NotFoundException;
import com.example.CertantChallenge.Repositories.InspectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InspectionService {

    private final InspectionRepository inspectionRepository;

    //Injecting the InspectionRepository
    public InspectionService(InspectionRepository inspectionRepository) {
        this.inspectionRepository = inspectionRepository;
    }

    //Basic CRUD operations

    public Inspection create(Inspection inspection) {
        return inspectionRepository.save(inspection);
    }

    public Inspection findById(Long id) {
        Inspection inspection = inspectionRepository.findById(id).orElse(null);
        if (inspection == null) {
            throw new NotFoundException("Inspection with id " + id + " does not exist");
        }
        inspection = updateInspectionStatus(inspection);
        return inspection;
    }


    public List<Inspection> findAll() {
        List<Inspection> inspections = inspectionRepository.findAll();

        return updateInspectionsStatus(inspections);

    }

    public Inspection update(Inspection inspection) {
        if (!inspectionRepository.existsById(inspection.getInspectionNumber())) {
            throw new RuntimeException("Inspection not found");
        }
        return inspectionRepository.save(inspection);
    }

    public void delete(Long id) {
        inspectionRepository.deleteById(id);
    }

    public List<Inspection> updateInspectionsStatus(List<Inspection> inspections) {
        for (Inspection inspection : inspections) {
            if (inspection.shouldExpire()) {
                inspection.setStatus(VTVStatus.VENCIDO.toString());
                inspectionRepository.save(inspection);
            }
        }
        return inspections;
    }

    public Inspection updateInspectionStatus(Inspection inspection) {
        if (inspection.shouldExpire()) {
            inspection.setStatus(VTVStatus.VENCIDO.toString());
            inspectionRepository.save(inspection);
        }
        return inspection;
    }
}


