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

    public List<Prescription> getAllPrescriptionsByPrescriptionId(String prescriptionId) {
        return prescriptionRepository.findAllByPrescriptionId(prescriptionId);
    }

    public boolean checkPrescriptionExistence(String prescriptionId) {
        return prescriptionRepository.existsByPrescriptionId(prescriptionId);
    }
}
