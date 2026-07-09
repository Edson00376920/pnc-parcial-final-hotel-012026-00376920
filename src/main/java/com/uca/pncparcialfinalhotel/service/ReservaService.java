package com.uca.pncparcialfinalhotel.service;

import com.uca.pncparcialfinalhotel.model.entities.Habitacion;
import com.uca.pncparcialfinalhotel.model.entities.Reserva;
import com.uca.pncparcialfinalhotel.repository.HabitacionRepository;
import com.uca.pncparcialfinalhotel.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private HabitacionRepository habitacionRepository;

    public Reserva crearReserva(Reserva nuevaReserva) {
        // Regla 1: Validar consistencia de fechas
        if (nuevaReserva.getFechaInicio().isAfter(nuevaReserva.getFechaFin()) ||
                nuevaReserva.getFechaInicio().isEqual(nuevaReserva.getFechaFin())) {
            throw new IllegalArgumentException("La fecha de inicio debe ser anterior a la fecha de fin.");
        }

        if (nuevaReserva.getFechaInicio().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("No se pueden hacer reservas en fechas pasadas.");
        }

        // Regla 2: Verificar existencia de la habitación
        Habitacion habitacion = habitacionRepository.findById(nuevaReserva.getHabitacion().getId())
                .orElseThrow(() -> new RuntimeException("La habitación solicitada no existe."));

        // Regla 3: Verificar disponibilidad (Cruces de fechas)
        List<Reserva> cruces = reservaRepository.findOverlappingReservations(
                habitacion.getId(),
                nuevaReserva.getFechaInicio(),
                nuevaReserva.getFechaFin()
        );

        if (!cruces.isEmpty()) {
            throw new IllegalStateException("La habitación no está disponible para el rango de fechas seleccionado.");
        }

        // Si pasa todas las reglas, guardamos la reserva
        nuevaReserva.setHabitacion(habitacion);
        nuevaReserva.setEstado("CONFIRMADA");
        return reservaRepository.save(nuevaReserva);
    }
}