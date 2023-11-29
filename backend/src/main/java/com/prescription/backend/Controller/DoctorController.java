package com.prescription.backend.Controller;

import com.prescription.backend.Model.Doctor;
import com.prescription.backend.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/doctors") //localhost:8080/doctors
    public List<Doctor> getAllDoctors(){
        return doctorService.getAllDoctors();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Doctor doctor){
        String email = doctor.getEmail();
        String password = doctor.getPassword();

        if (doctorService.isValidDoctor(email, password)) {
            return new ResponseEntity<>("Zalogowano pomyślnie", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Niepoprawne dane logowania", HttpStatus.UNAUTHORIZED);
        }
    }
    }

