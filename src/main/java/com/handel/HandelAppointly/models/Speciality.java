package com.handel.HandelAppointly.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "specialities")
public class Speciality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private String description;
}
