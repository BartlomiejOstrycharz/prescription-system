package com.prescription.backend.Repository;// MedicationRepository.java
import com.prescription.backend.Model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<Medication, Long> {
    // Additional query methods if needed
}
