package com.handel.HandelAppointly.repositorios;

import com.handel.HandelAppointly.entidades.RecetaMedica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecetaMedicaRepositorio extends JpaRepository<RecetaMedica,Long> {
}
