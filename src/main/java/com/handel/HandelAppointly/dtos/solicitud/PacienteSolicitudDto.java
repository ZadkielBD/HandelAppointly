package com.handel.HandelAppointly.dtos.solicitud;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PacienteSolicitudDto {
    @NotBlank(message = "El nombre no puede dejarse en blanco")
    private String nombre;

    @NotBlank(message = "El apellido no puede dejarse en blanco")
    private String apellido;

    @NotBlank(message = "El correo electrónico no puede quedar en blanco")
    @Email(message = "El formato del correo electrónico no es válido")
    private String email;

    @NotBlank(message = "La contraseña no puede estar en blanco")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres y un máximo de 20", max = 20)
    private String contrasena;

    @NotBlank(message = "El número de teléfono no puede dejarse en blanco")
    private String numeroTelefono;

    @NotBlank(message = "El número de identificación nacional no puede estar en blanco")
    private String nacionalId;

    private LocalDate fechaNacimiento;

    private String contactoEmergenciaNombre;
    private String contactoEmergenciaNoTelefono;
}
