package com.handel.HandelAppointly.models;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "admins")
@EqualsAndHashCode(callSuper=true)
@Data
public class Admin extends User {

    @Column(unique = true ,nullable = false, name = "employee_id")
    private Long employeeId;
}
