package com.prescription.backend.Repository;

import com.prescription.backend.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByFirstNameContainingOrLastNameContainingOrDateOfBirthContainingOrAddressContainingOrPhoneNumberContainingOrEmailContaining(
            String firstName, String lastName, String dateOfBirth, String address, String phoneNumber, String email);
}
