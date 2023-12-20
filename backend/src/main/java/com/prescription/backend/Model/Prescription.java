package com.prescription.backend.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "prescriptions")
public class Prescription {

    @Id
    @Column(name = "prescription_id", unique = true)
    private String prescriptionId;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "medication_id", nullable = false)
    private Medication medication;

    @Column(name = "prescription_date")
    private String prescriptionDate;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "instructions", length = 100)
    private String instructions;
}
