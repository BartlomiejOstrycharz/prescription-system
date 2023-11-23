package com.prescription.backend.Model;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patient_id;

    @Column(name = "first_name")
    String first_name;

    @Column(name = "last_name")
    String last_name;

    @Column(name = "date_of_birth")
    String date_of_birth;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    Gender gender;

    @Column(name = "address")
    String address;

    @Column(name = "phone_number")
    String phone_number;

    @Column(name = "email")
    String email;
}
