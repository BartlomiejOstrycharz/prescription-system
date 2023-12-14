package com.prescription.backend.Service;

import com.prescription.backend.Model.Patient;
import com.prescription.backend.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public List<Patient> searchPatients(String searchTerm) {
        return patientRepository.findByFirstNameContainingOrLastNameContainingOrDateOfBirthContainingOrAddressContainingOrPhoneNumberContainingOrEmailContaining(
                searchTerm, searchTerm, searchTerm, searchTerm, searchTerm, searchTerm);
    }
}
