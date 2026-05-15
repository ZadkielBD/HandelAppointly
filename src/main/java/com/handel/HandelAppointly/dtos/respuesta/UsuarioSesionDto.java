package com.handel.HandelAppointly.dtos.respuesta;

import com.handel.HandelAppointly.enums.Rol;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioSesionDto {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String numeroTelefono;
    private Rol rol;
}
