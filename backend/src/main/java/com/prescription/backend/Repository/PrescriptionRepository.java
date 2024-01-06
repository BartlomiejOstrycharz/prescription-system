package com.prescription.backend.Repository;

import com.prescription.backend.Model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    boolean existsByPrescriptionId(String prescriptionId);
    List<Prescription> findByPrescriptionId(String prescriptionId);
    List<Prescription> findAllByPrescriptionId(String prescriptionId);
}
