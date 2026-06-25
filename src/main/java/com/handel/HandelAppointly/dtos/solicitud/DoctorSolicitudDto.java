package com.handel.HandelAppointly.dtos.solicitud;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class DoctorSolicitudDto{
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío")
    private String apellido;

    @NotBlank(message = "El correo electrónico no puede estar vacío")
    @Email(message = "El formato del correo electrónico no es válido")
    private String email;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres y un máximo de 20", max = 20)
    private String contrasena;

    @NotBlank(message = "El número de teléfono no puede estar vacío")
    private String numeroTelefono;

    @NotEmpty(message = "Las especialidades no pueden estar vacías")
    private Set<Long> especialidadesIds;

    @NotNull(message = "El precio de consulta no puede estar vacío")
    @Positive(message = "El precio debe de ser positivo")
    private BigDecimal precioLocal;

    @NotBlank(message = "La divisa no puede estar vacía")
    private String codigoDivisa;
}
