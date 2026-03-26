package com.handel.HandelAppointly.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "prescriptions")
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private MedicalHistory medicalHistory;

    @ManyToOne
    private Medicine medicine;

    @Column(columnDefinition = "TEXT")
    private String dosage;

    @Column(nullable = false)
    private Integer durationDays;
}
