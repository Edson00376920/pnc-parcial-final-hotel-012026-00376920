package com.uca.pncparcialfinalhotel.service;

import com.uca.pncparcialfinalhotel.model.entities.Habitacion;
import com.uca.pncparcialfinalhotel.repository.HabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitacionService {

    @Autowired
    private HabitacionRepository habitacionRepository;

    public List<Habitacion> obtenerTodas() {
        return habitacionRepository.findAll();
    }

    public Habitacion guardar(Habitacion habitacion) {
        return habitacionRepository.save(habitacion);
    }

    public Habitacion actualizar(Long id, Habitacion datosActualizados) {
        Habitacion existente = habitacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Habitación no encontrada"));

        existente.setPrecioPorNoche(datosActualizados.getPrecioPorNoche());
        existente.setDisponible(datosActualizados.isDisponible());

        return habitacionRepository.save(existente);
    }
}