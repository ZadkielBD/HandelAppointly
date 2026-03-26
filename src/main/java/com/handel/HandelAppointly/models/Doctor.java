package com.handel.HandelAppointly.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Entity
@Data
@EqualsAndHashCode(callSuper=true)
@Table(name = "doctors")
public class Doctor extends User {

    @Column(unique = true ,nullable = false, name = "professional_id")
    private String professionalId;

    @ManyToOne
    @JoinColumn(name = "speciality_id")
    private Speciality speciality;

    @Column(nullable = false, name = "price_appointment")
    private BigDecimal priceAppointment;
}
