package com.prescription.backend.Model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "medications")
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medication_id")
    private Long medicationId;

    @Column(name = "medication_name")
    private String medicationName;

    @ManyToOne
    @JoinColumn(name = "purpose_id", nullable = false)
    private Purpose purpose;

    @Column(name = "volume")
    private String volume;

    @Column(name = "side_effects", length = 50)
    private String sideEffects;
}
