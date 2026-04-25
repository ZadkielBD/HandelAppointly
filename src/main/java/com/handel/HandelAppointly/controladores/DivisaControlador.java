package com.handel.HandelAppointly.controladores;

import com.handel.HandelAppointly.dtos.solicitud.DivisaSolicitudDto;
import com.handel.HandelAppointly.dtos.respuesta.DivisaRespuestaDto;
import com.handel.HandelAppointly.servicios.DivisaServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/currencies")
@RequiredArgsConstructor
public class DivisaControlador {
    private final DivisaServicio divisaServicio;

    @PostMapping
    public ResponseEntity<DivisaRespuestaDto> add(@Valid @RequestBody DivisaSolicitudDto requestDto) {
        DivisaRespuestaDto currency = divisaServicio.add(requestDto);
        return new ResponseEntity<>(currency, HttpStatus.CREATED);
    }

    @GetMapping("{code}")
    public ResponseEntity<DivisaRespuestaDto> findByCode(@PathVariable String code) {
        DivisaRespuestaDto currency = divisaServicio.findByCodigo(code);
        return new ResponseEntity<>(currency, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<DivisaRespuestaDto>> findAll(@PageableDefault(size = 10, sort = "code") Pageable pageable) {
        Page<DivisaRespuestaDto> currency = divisaServicio.findAll(pageable);
        return new ResponseEntity<>(currency, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> update() {
        divisaServicio.updateTipoCambio();
        return new ResponseEntity<>("Updated Successfully at " + LocalDateTime.now(), HttpStatus.OK);
    }

    public ResponseEntity<Void> delete(String code) {
        divisaServicio.delete(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
