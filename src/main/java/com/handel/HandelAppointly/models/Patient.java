package com.handel.HandelAppointly.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=true)
@Table(name = "patients")
public class Patient extends User {

    @Column(unique = true ,nullable = false, name = "national_id")
    private String nationalId;

    @Column(nullable = true, name = "emergency_contact_name")
    private String emergencyContactName;
    @Column(nullable = true, name = "emergency_contact_phone_number")
    private String emergencyContactPhoneNumber;
}
