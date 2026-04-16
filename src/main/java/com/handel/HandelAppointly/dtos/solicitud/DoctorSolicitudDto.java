package com.handel.HandelAppointly.dtos.solicitud;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DoctorSolicitudDto(
        @NotBlank(message = "Doctor can't be blank")
        @Pattern(regexp = "^[0-9]{7,8}$")
        String profesionalId, //Matricula, la cual debe de seguir un patron: 87648390

        @NotBlank(message = "El nombre no puede dejarse en blanco")
        String nombre,

        @NotBlank(message = "El apellido no puede dejarse en blanco")
        String apellido,

        @NotBlank(message = "El correo electrónico no puede quedar en blanco")
        @Email(message = "El formato del correo electrónico no es válido")
        String email,

        @NotBlank(message = "La contraseña no puede estar en blanco")
        @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres y un máximo de 20", max = 20)
        String contrasena,

        @NotBlank(message = "El número de teléfono no puede dejarse en blanco")
        String numeroTelefono,

        @NotBlank(message = "Speciality can't be blank")
        String especialidad, // Nombre de la especialidad
        @NotNull(message = "Price local can't be blank")
        BigDecimal precioLocal, // Precio de la cita médica en la divisa especificada
        @NotBlank(message = "Currency Code can't be blank")
        String codigoDivisa // Codigo de la divisa
) {}
