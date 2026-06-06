package com.handel.HandelAppointly.repositorios;

import com.handel.HandelAppointly.entidades.HistorialMedico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HistorialMedicoRepositorio extends JpaRepository<HistorialMedico,Long> {
    Optional<HistorialMedico> findByPacienteId(Long pacienteId);
}
