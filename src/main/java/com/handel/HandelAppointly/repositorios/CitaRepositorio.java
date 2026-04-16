package com.handel.HandelAppointly.repositorios;

import com.handel.HandelAppointly.entidades.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitaRepositorio extends JpaRepository<Cita,Long> {
}
