package com.example.CertantChallenge.Controller;

import com.example.CertantChallenge.Entities.Inspector;
import com.example.CertantChallenge.Service.InspectorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InspectorController {

    private final InspectorService inspectorService;

    public InspectorController(InspectorService inspectorService) {
        this.inspectorService = inspectorService;
    }

    //basic CRUD operations for the Inspector entity
    @GetMapping("/inspectors")
    @ResponseBody
    public ResponseEntity<List<Inspector>> getInspectors() {
        return ResponseEntity.ok(inspectorService.findAll());
    }

    @GetMapping("/inspectors/{id}")
    @ResponseBody
    public ResponseEntity<Inspector> getInspectorById(@PathVariable Long id) {
        return ResponseEntity.ok(inspectorService.findById(id));
    }

    @PostMapping("/inspectors")
    @ResponseBody
    public ResponseEntity<Inspector> createInspector(@RequestBody Inspector inspector) {
        return ResponseEntity.ok(inspectorService.create(inspector));
    }

    @PutMapping("/inspectors")
    @ResponseBody
    public ResponseEntity<Inspector> updateInspector(@RequestBody Inspector inspector) {
        return ResponseEntity.ok(inspectorService.update(inspector));
    }

    @DeleteMapping("/inspectors/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteInspector(@PathVariable Long id) {
        inspectorService.delete(id);
        return ResponseEntity.ok().build();
    }
}

