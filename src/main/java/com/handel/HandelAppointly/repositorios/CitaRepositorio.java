package com.handel.HandelAppointly.repositorios;

import com.handel.HandelAppointly.entidades.Cita;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CitaRepositorio extends JpaRepository<Cita, Long> {
    Page<Cita> findByPacienteId(Long pacienteId, Pageable pageable);
    Page<Cita> findByDoctorId(Long doctorId, Pageable pageable);

    @Query("SELECT c FROM Cita c WHERE c.doctor.id = :doctorId AND CAST(c.fechaHora AS date) = :fecha")
    List<Cita> findByDoctorIdAndFecha(@Param("doctorId") Long doctorId, @Param("fecha") LocalDate fecha);

    @Query("SELECT DISTINCT c.paciente FROM Cita c WHERE c.doctor.id = :doctorId")
    Page<com.handel.HandelAppointly.entidades.Paciente> findPacientesByDoctorId(@Param("doctorId") Long doctorId, Pageable pageable);
}