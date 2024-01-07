package com.prescription.backend.Controller;

import com.prescription.backend.Model.Patient;
import com.prescription.backend.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientController {

 private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService){
        this.patientService = patientService;
    }

    @GetMapping("/patients") //http://localhost:8080/patients
    public List<Patient> getAllPatients() {
        return  patientService.getAllPatients();
    }

    @GetMapping("/search")
    public List<Patient> searchPatients(@RequestParam String searchTerm) {
        return patientService.searchPatients(searchTerm);
    }

    @DeleteMapping("/patients/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.ok("Patient deleted successfully");
    }
    @PostMapping("/add")
    public ResponseEntity<String> addPatient(@RequestBody Patient patient) {
        try {
            patientService.savePatient(patient);
            return new ResponseEntity<>("Patient added successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error adding patient", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
