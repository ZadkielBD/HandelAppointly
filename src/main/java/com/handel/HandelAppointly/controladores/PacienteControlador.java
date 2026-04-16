package com.handel.HandelAppointly.controladores;

import com.handel.HandelAppointly.dtos.solicitud.PacienteSolicitudDto;
import com.handel.HandelAppointly.dtos.respuesta.PacienteRespuestaDto;
import com.handel.HandelAppointly.servicios.PacienteServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PacienteControlador {
    private final PacienteServicio pacienteServicio;

    @PostMapping
    public ResponseEntity<PacienteRespuestaDto> create(@Valid @RequestBody PacienteSolicitudDto pacienteSolicitudDto) {
        PacienteRespuestaDto patient = pacienteServicio.create(pacienteSolicitudDto);
        return new ResponseEntity<>(patient, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteRespuestaDto> findById(@PathVariable Long id) {
        PacienteRespuestaDto patient = pacienteServicio.findById(id);
//        return ResponseEntity.ok(patient);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<PacienteRespuestaDto>> findAll(@PageableDefault(size = 10, sort = "lastName")
                                                           Pageable pageable) {
        Page<PacienteRespuestaDto> patient = pacienteServicio.findAll(pageable);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteRespuestaDto> update(@PathVariable Long id,
                                                       @Valid @RequestBody PacienteSolicitudDto requestDto) {
        PacienteRespuestaDto patient = pacienteServicio.update(id, requestDto);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PacienteRespuestaDto> patch(@PathVariable Long id,
                                                      @Valid @RequestBody PacienteSolicitudDto requestDto) {
        PacienteRespuestaDto patient = pacienteServicio.patch(id, requestDto);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pacienteServicio.delete(id);
        return ResponseEntity.noContent().build();
    }
}
