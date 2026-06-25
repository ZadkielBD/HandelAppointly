package com.handel.HandelAppointly.excepciones;

import org.springframework.web.bind.MethodArgumentNotValidException;
import com.handel.HandelAppointly.dtos.respuesta.ErrorRespuestaDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourcesNotFoundException.class)
    public ResponseEntity<ErrorRespuestaDto> handleResourceNotFound(RuntimeException ex,
                                                                    WebRequest request) {
        ErrorRespuestaDto error = new ErrorRespuestaDto(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Recurso no encontrado",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", ""),
                null
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodNotAllowedException.class)
    public ResponseEntity<ErrorRespuestaDto> handleIllegalArgumentException(MethodNotAllowedException ex,
                                                                            WebRequest request) {
        ErrorRespuestaDto error = new ErrorRespuestaDto(
                LocalDateTime.now(),
                HttpStatus.METHOD_NOT_ALLOWED.value(),
                "Método no permitido",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", ""),
                null
        );
        return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorRespuestaDto>  handleValidation(MethodArgumentNotValidException ex,
                                                               WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(f -> errors.put(f.getField(), f.getDefaultMessage()));

        ErrorRespuestaDto error = new ErrorRespuestaDto(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Error de validación",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", ""),
                errors
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorRespuestaDto> handleException(Exception ex,
                                                             WebRequest request) {
        ErrorRespuestaDto error = new ErrorRespuestaDto(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Error interno del servidor",
                "Se ha producido un error inesperado: " + ex.getMessage(),
                request.getDescription(false).replace("uri=", ""),
                null
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorRespuestaDto> handleRuntimeException(Exception ex,
                                                                    WebRequest request) {
        ErrorRespuestaDto error = new ErrorRespuestaDto(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Error interno del servidor",
                "Se ha producido un error inesperado: " + ex.getMessage(),
                request.getDescription(false).replace("uri=", ""),
                null
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EmailDuplicadoException.class)
    public ResponseEntity<ErrorRespuestaDto> handleEmailDuplicado(EmailDuplicadoException ex,
                                                                  WebRequest request) {
        ErrorRespuestaDto error = new ErrorRespuestaDto(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                "Email duplicado",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", ""),
                null
        );
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
}
