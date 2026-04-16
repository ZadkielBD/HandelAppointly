package com.handel.HandelAppointly.servicios.impl;

import com.handel.HandelAppointly.dtos.respuesta.UsuarioRespuestaDto;
import com.handel.HandelAppointly.enums.Rol;
import com.handel.HandelAppointly.excepciones.ResourcesNotFoundException;
import com.handel.HandelAppointly.entidades.Usuario;
import com.handel.HandelAppointly.repositorios.UsuarioRepositorio;
import com.handel.HandelAppointly.servicios.UsuarioServicio;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioServicioImpl implements UsuarioServicio {
    private final UsuarioRepositorio usuarioRepositorio;
    private final ModelMapper modelMapper;


    @Override
    @Transactional(readOnly = true)
    public UsuarioRespuestaDto getById(Long id) {
        Usuario user = findUserById(id);

        return modelMapper.map(user, UsuarioRespuestaDto.class);
    }

    /*
    @Override
    @Transactional(readOnly = true)
    public Page<UserResponseDto> getAllByRole(Role role, Pageable pageable) {
        Page<User> users;

        if (role != null) {
            users = userRepository.findAllByRole(role, pageable);
        } else {
            users = userRepository.findAll(pageable);
        }

        return users.map(user -> modelMapper.map(user, UserResponseDto.class));

//        return userRepository.findAllByRole(role).stream().map(user -> modelMapper.map(user, UserResponseDto.class))
//          .collect(Collectors.toList());
    }
     */

    @Override
    @Transactional(readOnly = true)
    public Page<UsuarioRespuestaDto> getAll(Rol rol, Pageable pageable) {
        Page<Usuario> users;

        if (rol != null) {
            users = usuarioRepositorio.findAllByRole(rol, pageable);
        } else {
            users = usuarioRepositorio.findAll(pageable);
        }

        return users.map(user -> modelMapper.map(user, UsuarioRespuestaDto.class));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Usuario user = findUserById(id);

        usuarioRepositorio.delete(user);
    }

    private Usuario findUserById(Long id) {
        return usuarioRepositorio.findById(id)
                        .orElseThrow(() -> new ResourcesNotFoundException("User with id " + id + " not found"));
    }

}
