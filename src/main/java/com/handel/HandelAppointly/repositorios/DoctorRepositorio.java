package com.handel.HandelAppointly.repositorios;

import com.handel.HandelAppointly.entidades.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepositorio extends JpaRepository<Doctor,Long> {
}

