package com.prescription.backend.Controller;

import com.prescription.backend.Model.Prescription;
import com.prescription.backend.Service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @GetMapping("/exists/{prescriptionId}")
    public ResponseEntity<Boolean> checkPrescriptionExistence(@PathVariable String prescriptionId) {
        boolean exists = prescriptionService.checkPrescriptionExistence(prescriptionId);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }

    @GetMapping("/{prescriptionId}")
    public ResponseEntity<Prescription> getPrescriptionById(@PathVariable String prescriptionId) {
        Prescription prescription = prescriptionService.getPrescriptionById(prescriptionId);
        if (prescription != null) {
            return new ResponseEntity<>(prescription, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
