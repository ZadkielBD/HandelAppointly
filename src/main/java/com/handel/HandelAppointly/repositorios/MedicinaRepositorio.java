package com.handel.HandelAppointly.repositorios;

import com.handel.HandelAppointly.entidades.Medicina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicinaRepositorio extends JpaRepository<Medicina, Long> {
}
