package com.prescription.backend.Service;

import com.prescription.backend.Model.Patient;
import com.prescription.backend.Model.Prescription;
import com.prescription.backend.Repository.PatientRepository;
import com.prescription.backend.Repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final PrescriptionRepository prescriptionRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository, PrescriptionRepository prescriptionRepository) {
        this.patientRepository = patientRepository;
        this.prescriptionRepository = prescriptionRepository;
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public List<Patient> searchPatients(String searchTerm) {
        return patientRepository.findByFirstNameContainingOrLastNameContainingOrDateOfBirthContainingOrAddressContainingOrPhoneNumberContainingOrEmailContaining(
                searchTerm, searchTerm, searchTerm, searchTerm, searchTerm, searchTerm);
    }

    public void deletePatient(Long patientId) {
        patientRepository.deleteById(patientId);
    }

    public Optional<Patient> getPatientByPrescriptionId(String prescriptionId) {
        List<Prescription> prescriptions = prescriptionRepository.findByPrescriptionId(prescriptionId);
        return prescriptions.stream().findFirst().map(Prescription::getPatient);
    }

}
