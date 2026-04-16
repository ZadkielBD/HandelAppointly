package com.handel.HandelAppointly.repositorios;

import com.handel.HandelAppointly.entidades.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepositorio extends JpaRepository<Paciente,Long> {
}
