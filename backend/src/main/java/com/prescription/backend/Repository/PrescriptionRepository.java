package com.prescription.backend.Repository;

import com.prescription.backend.Model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository extends JpaRepository<Prescription, String> {
    // You can add custom query methods if needed
}
