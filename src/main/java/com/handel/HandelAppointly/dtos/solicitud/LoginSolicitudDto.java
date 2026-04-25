package com.handel.HandelAppointly.dtos.solicitud;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginSolicitudDto(
        @NotBlank(message = "El correo electrónico no puede quedar en blanco")
        @Email(message = "El formato del correo electrónico no es válido")
        String email,

        @NotBlank(message = "La contraseña no puede estar en blanco")
        @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres y un máximo de 20", max = 20)
        String contrasena
) { }
