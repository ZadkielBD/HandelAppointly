package com.handel.HandelAppointly.repositorios;

import com.handel.HandelAppointly.entidades.Pago;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PagoRepositorio extends JpaRepository<Pago,Long> {
    Optional<Pago> findByCitaId(Long citaId);
    boolean existsByCitaId(Long citaId);
    Page<Pago> findByCitaPacienteId(Long pacienteId, Pageable pageable);
}
