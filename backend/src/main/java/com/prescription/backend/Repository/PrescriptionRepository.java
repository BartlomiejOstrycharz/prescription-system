package com.prescription.backend.Repository;

import com.prescription.backend.Model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrescriptionRepository extends JpaRepository<Prescription, String> {
    Optional<Prescription> findByPrescriptionId(String prescriptionId);
    boolean existsByPrescriptionId(String prescriptionId);


}
