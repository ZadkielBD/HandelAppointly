package com.handel.HandelAppointly.repositorios;

import com.handel.HandelAppointly.entidades.Divisa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DivisaRepositorio extends JpaRepository<Divisa, String> {
}
