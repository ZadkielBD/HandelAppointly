package com.handel.HandelAppointly.servicios;

import com.handel.HandelAppointly.entidades.Medicina;
import java.util.List;

public interface MedicinaServicio {
    List<Medicina> findAll();
    Medicina findById(Long id);
    Medicina create(Medicina medicina);
    Medicina update(Long id, Medicina medicina);
    void delete(Long id);
}