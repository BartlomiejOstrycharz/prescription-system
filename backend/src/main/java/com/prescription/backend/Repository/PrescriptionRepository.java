package com.prescription.backend.Repository;

import com.prescription.backend.Model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
     boolean existsByPrescriptionName(String prescriptionId);
     List<Prescription> findAllByPrescriptionName(String prescriptionName);
}
