package com.prescription.backend.Service;

import com.prescription.backend.Model.Prescription;
import com.prescription.backend.Repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;

    @Autowired
    public PrescriptionService(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    public List<Prescription> getAllPrescriptionsByPrescriptionName(String prescriptionName) {
        return prescriptionRepository.findAllByPrescriptionName(prescriptionName);
    }

    public boolean checkPrescriptionExistence(String prescriptionName) {
        return prescriptionRepository.existsByPrescriptionName(prescriptionName);
    }
}
