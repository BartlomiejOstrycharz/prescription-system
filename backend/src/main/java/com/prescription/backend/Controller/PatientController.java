package com.prescription.backend.Controller;

import com.prescription.backend.Model.Patient;
import com.prescription.backend.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
