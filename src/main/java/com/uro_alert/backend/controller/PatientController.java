package com.uro_alert.backend.controller;

import com.uro_alert.backend.model.PatientDto;
import com.uro_alert.backend.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<PatientDto>> viewAllPatients() {

        return ResponseEntity.ok(patientService.viewAllPatients());
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<PatientDto> viewPatientById(@PathVariable int id) {
        return ResponseEntity.ok(patientService.viewPatientById(id));
    }

    @PostMapping()
    public ResponseEntity<PatientDto> createPatient(@RequestBody PatientDto patientDto) {
        return ResponseEntity.ok(patientService.save(patientDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletePatient(@PathVariable int id) {
        return ResponseEntity.ok(patientService.deletePatientById(id));
    }

}
