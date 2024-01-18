package com.prescription.backend.Controller;

import com.prescription.backend.Model.Patient;
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

    @GetMapping("/exists/{prescriptionName}")
    public ResponseEntity<Boolean> checkPrescriptionExistence(@PathVariable String prescriptionName) {
        boolean exists = prescriptionService.checkPrescriptionExistence(prescriptionName);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }

    @GetMapping("/{prescriptionName}")
    public ResponseEntity<List<Prescription>> getAllPrescriptionsByPrescriptionName(@PathVariable String prescriptionName) {
        List<Prescription> prescriptions = prescriptionService.getAllPrescriptionsByPrescriptionName(prescriptionName);
        if (!prescriptions.isEmpty()) {
            return new ResponseEntity<>(prescriptions, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{prescriptionName}/patient")
    public ResponseEntity<Patient> getPatientByPrescriptionName(@PathVariable String prescriptionName) {
        return patientService.getPatientByPrescriptionId(prescriptionName)
                .map(patient -> new ResponseEntity<>(patient, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{prescriptionId}")
    public ResponseEntity<Void> deletePrescriptionByPrescriptionId(@PathVariable Long prescriptionId){
        try {
            prescriptionService.deletePrescriptionByPrescriptionId(prescriptionId);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
