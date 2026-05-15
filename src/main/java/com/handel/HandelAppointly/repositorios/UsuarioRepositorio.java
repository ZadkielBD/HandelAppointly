package com.handel.HandelAppointly.repositorios;

import com.handel.HandelAppointly.enums.Rol;
import com.handel.HandelAppointly.entidades.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    Page<Usuario> findAllByRol(Rol rol, Pageable paginable);

    Optional<Usuario> findByEmail(String email);

}
