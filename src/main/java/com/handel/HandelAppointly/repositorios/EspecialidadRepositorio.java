package com.handel.HandelAppointly.repositorios;

import com.handel.HandelAppointly.entidades.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EspecialidadRepositorio extends JpaRepository<Especialidad,Long> {
    Optional<Especialidad> findByNombre(String name);
}
