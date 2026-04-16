package com.handel.HandelAppointly.repositorios;

import com.handel.HandelAppointly.enums.Rol;
import com.handel.HandelAppointly.entidades.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    Page<Usuario> findAllByRole(Rol rol, Pageable pageable);

}
