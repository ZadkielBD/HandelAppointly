package com.handel.HandelAppointly.repositorios;

import com.handel.HandelAppointly.entidades.ConsultaMedica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConsultaMedicaRepositorio extends JpaRepository<ConsultaMedica, Long> {
    Optional<ConsultaMedica> findByCitaId(Long citaId);
}