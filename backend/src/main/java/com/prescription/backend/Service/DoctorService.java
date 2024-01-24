package com.prescription.backend.Service;

import com.prescription.backend.Model.Doctor;
import com.prescription.backend.Repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public boolean isValidDoctor(String email, String password) {

        Doctor doctor = doctorRepository.findByEmailAndPassword(email, password);
        return doctor != null;
    }

}
