package com.prescription.backend.Service;// PurposeService.java
import com.prescription.backend.Model.Purpose;
import com.prescription.backend.Repository.PurposeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurposeService {
    private final PurposeRepository purposeRepository;

    @Autowired
    public PurposeService(PurposeRepository purposeRepository) {
        this.purposeRepository = purposeRepository;
    }

    public List<Purpose> getAllPurposes() {
        return purposeRepository.findAll();
    }

    // Additional methods as needed
}
