package com.example.CertantChallenge.Service;

import com.example.CertantChallenge.Entities.Inspection;
import com.example.CertantChallenge.Enums.VTVStatus;
import com.example.CertantChallenge.Exceptions.BadRequestException;
import com.example.CertantChallenge.Exceptions.InternalServerError;
import com.example.CertantChallenge.Exceptions.NotFoundException;
import com.example.CertantChallenge.Repositories.InspectionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.CertantChallenge.Utils.NotUpdateNull.getNullPropertyNames;

@Service
public class InspectionService {

    private final InspectionRepository inspectionRepository;

    //Injecting the InspectionRepository
    public InspectionService(InspectionRepository inspectionRepository) {
        this.inspectionRepository = inspectionRepository;
    }

    //Basic CRUD operations

    public Inspection create(Inspection inspection) {
        try {
            inspection.setExpirationDate(inspection.getInspectionDate().plusYears(1)); //haria este cambio ya que no se setea la fecha de expiracion
            return inspectionRepository.save(inspection);
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }

    public Inspection findById(Long id) {
        try {
            Inspection inspection = inspectionRepository.findById(id).orElse(null);
            if (inspection == null) {
                throw new NotFoundException("Inspection with id " + id + " does not exist");
            }
            inspection = updateInspectionStatus(inspection);
            return inspection;
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }


    public List<Inspection> findAll() {
        try {
            List<Inspection> inspections = inspectionRepository.findAll();
            return inspections.stream().map(this::updateInspectionStatus).toList();
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }


    public Inspection update(Inspection inspection) {
        try {
            Inspection inspectionToUpdate = inspectionRepository.findById(inspection.getInspectionNumber()).orElse(null);
            if (inspectionToUpdate == null) {
                throw new NotFoundException("Inspection not found");
            }

            if (inspectionToUpdate.getStatus().equals(VTVStatus.VENCIDO.toString())) {
                throw new BadRequestException("Inspection is expired");
            }

            if (inspection.getStatus().equals(VTVStatus.APTO.toString())) {
                inspection.setInspectionDate(LocalDateTime.now());
                inspection.setExpirationDate(inspection.getInspectionDate().plusYears(1));
            }

            String[] nullProperties = getNullPropertyNames(inspection);
            BeanUtils.copyProperties(inspection, inspectionToUpdate, nullProperties);

            return inspectionRepository.save(inspectionToUpdate);
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }


    public void delete(Long id) {
        try {
            inspectionRepository.deleteById(id);
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }


    public Inspection updateInspectionStatus(Inspection inspection) {
        try {
            if (inspection.shouldExpire()) {
                inspection.setStatus(VTVStatus.VENCIDO.toString());
                inspectionRepository.save(inspection);
            }
            return inspection;
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }
}


