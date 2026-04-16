package com.handel.HandelAppointly.repositorios;

import com.handel.HandelAppointly.entidades.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorarioRepositorio extends JpaRepository<Horario,Long> {
}
