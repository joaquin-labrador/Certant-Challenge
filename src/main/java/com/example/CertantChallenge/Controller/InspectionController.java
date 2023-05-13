package com.example.CertantChallenge.Controller;

import com.example.CertantChallenge.Entities.Inspection;
import com.example.CertantChallenge.Service.InspectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InspectionController {

    private final InspectionService inspectionService;


    public InspectionController(InspectionService inspectionService) {
        this.inspectionService = inspectionService;
    }


    //basic CRUD operations for the Inspection entity


    @GetMapping("/inspections")
    @ResponseBody
    public ResponseEntity<List<Inspection>> getInspections() {
        return ResponseEntity.ok(inspectionService.findAll());
    }


    @GetMapping("/inspections/{id}")
    @ResponseBody
    public ResponseEntity<Inspection> getInspectionById(@PathVariable Long id) {
        return ResponseEntity.ok(inspectionService.findById(id));
    }


    @PostMapping("/inspections")
    @ResponseBody
    public ResponseEntity<Inspection> createInspection(@RequestBody Inspection inspection) {
        return ResponseEntity.ok(inspectionService.create(inspection));
    }

    @PutMapping("/inspections")
    @ResponseBody
    public ResponseEntity<Inspection> updateInspection(@RequestBody Inspection inspection) {
        return ResponseEntity.ok(inspectionService.update(inspection));
    }

    @DeleteMapping("/inspections/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteInspection(@PathVariable Long id) {
        inspectionService.delete(id);
        return ResponseEntity.ok().build();
    }


}
