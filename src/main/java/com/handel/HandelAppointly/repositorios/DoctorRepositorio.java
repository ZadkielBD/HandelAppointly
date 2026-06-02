package com.handel.HandelAppointly.repositorios;

import com.handel.HandelAppointly.entidades.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepositorio extends JpaRepository<Doctor,Long> {
    @Query("SELECT DISTINCT d FROM Doctor d JOIN d.especialidades e WHERE LOWER(e.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    Page<Doctor> findByEspecialidadNombre(@Param("nombre") String nombre, Pageable pageable);
}

