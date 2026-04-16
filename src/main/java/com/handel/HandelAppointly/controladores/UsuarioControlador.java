package com.handel.HandelAppointly.controladores;

import com.handel.HandelAppointly.dtos.respuesta.UsuarioRespuestaDto;
import com.handel.HandelAppointly.enums.Rol;
import com.handel.HandelAppointly.servicios.UsuarioServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsuarioControlador {

    private final UsuarioServicio usuarioServicio;

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioRespuestaDto> getById(@PathVariable Long id) {
        UsuarioRespuestaDto user = usuarioServicio.getById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioRespuestaDto>> getAll(
            @RequestParam(required = false) Rol rol,
            @PageableDefault(size = 10, sort = "lastName") Pageable pageable) {
        Page<UsuarioRespuestaDto> users = usuarioServicio.getAll(rol, pageable);
//        return ResponseEntity.ok(users);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

//    @GetMapping("/roles")
//    public ResponseEntity<List<UserResponseDto>> findByRole(@RequestBody Role role) {
//        List<UserResponseDto> users = userService.getAllByRole(role);
//        return new ResponseEntity<>(users, HttpStatus.OK);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        usuarioServicio.delete(id);
        return ResponseEntity.noContent().build();
    }
}
