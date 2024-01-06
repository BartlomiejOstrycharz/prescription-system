package com.prescription.backend.Controller;

import com.prescription.backend.Model.Prescription;
import com.prescription.backend.Service.PrescriptionService;
import com.prescription.backend.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private PatientService patientService;

    @GetMapping("/exists/{prescriptionId}")
    public ResponseEntity<Boolean> checkPrescriptionExistence(@PathVariable String prescriptionId) {
        boolean exists = prescriptionService.checkPrescriptionExistence(prescriptionId);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }

    @GetMapping("/{prescriptionId}")
    public ResponseEntity<List<Prescription>> getAllPrescriptionsByPrescriptionId(@PathVariable String prescriptionId) {
        List<Prescription> prescriptions = prescriptionService.getAllPrescriptionsByPrescriptionId(prescriptionId);
        if (!prescriptions.isEmpty()) {
            return new ResponseEntity<>(prescriptions, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }




    @GetMapping("/{prescriptionId}/patient")
    public ResponseEntity<?> getPatientByPrescriptionId(@PathVariable String prescriptionId) {
        return patientService.getPatientByPrescriptionId(prescriptionId)
                .map(patient -> new ResponseEntity<>(patient, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
