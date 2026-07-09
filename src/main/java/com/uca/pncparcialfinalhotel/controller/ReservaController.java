package com.uca.pncparcialfinalhotel.controller;


import com.uca.pncparcialfinalhotel.model.entities.Reserva;
import com.uca.pncparcialfinalhotel.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping
    public ResponseEntity<?> crearReserva(@RequestBody Reserva reserva) {
        try {
            Reserva nuevaReserva = reservaService.crearReserva(reserva);
            return new ResponseEntity<>(nuevaReserva, HttpStatus.CREATED);
        } catch (IllegalArgumentException | IllegalStateException e) {
            // Captura errores de lógica de negocio (fechas incorrectas u ocupadas)
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            // Captura errores genéricos internos
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor.");
        }
    }
}
