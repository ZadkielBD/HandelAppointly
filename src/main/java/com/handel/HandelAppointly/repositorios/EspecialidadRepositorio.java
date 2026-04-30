package com.handel.HandelAppointly.repositorios;

import com.handel.HandelAppointly.entidades.Especialidad;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface EspecialidadRepositorio extends JpaRepository<Especialidad,Long> {

}
