package com.prescription.backend.Repository;

import com.prescription.backend.Model.Purpose;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurposeRepository extends JpaRepository<Purpose, Long> {
    // Additional query methods if needed
}