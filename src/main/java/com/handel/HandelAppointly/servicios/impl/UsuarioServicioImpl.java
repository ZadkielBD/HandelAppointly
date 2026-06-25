package com.handel.HandelAppointly.servicios.impl;

import com.handel.HandelAppointly.dtos.respuesta.UsuarioRespuestaDto;
import com.handel.HandelAppointly.dtos.respuesta.UsuarioSesionDto;
import com.handel.HandelAppointly.enums.Rol;
import com.handel.HandelAppointly.excepciones.ResourcesNotFoundException;
import com.handel.HandelAppointly.entidades.Usuario;
import com.handel.HandelAppointly.mappers.UsuarioMapper;
import com.handel.HandelAppointly.repositorios.UsuarioRepositorio;
import com.handel.HandelAppointly.servicios.UsuarioServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioServicioImpl implements UsuarioServicio {
    private final UsuarioRepositorio usuarioRepositorio;
    private final UsuarioMapper usuarioMapper;

    @Override
    @Transactional(readOnly = true)
    public UsuarioRespuestaDto findById(Long id) {
        Usuario user = findUserById(id);

        return usuarioMapper.aRespuestaDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioSesionDto login(String email, String contrasena) {
        Usuario usuario = usuarioRepositorio.findByEmail(email)
                .orElseThrow(() -> new ResourcesNotFoundException("Email o contraseña incorrectos"));

        if (!usuario.getContrasena().equals(contrasena)) {
            throw new ResourcesNotFoundException("Email o contraseña incorrectos");
        }

        return usuarioMapper.aUsuarioSesionDto(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UsuarioRespuestaDto> findAll(Rol rol, Pageable paginable) {
        Page<Usuario> users;

        if (rol != null) {
            users = usuarioRepositorio.findAllByRol(rol, paginable);
        } else {
            users = usuarioRepositorio.findAll(paginable);
        }

        return users.map(usuarioMapper::aRespuestaDto);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Usuario user = findUserById(id);

        usuarioRepositorio.delete(user);
    }

    private Usuario findUserById(Long id) {
        return usuarioRepositorio.findById(id)
                        .orElseThrow(() -> new ResourcesNotFoundException("Usuario con id " + id + " no encontrado"));
    }

}
