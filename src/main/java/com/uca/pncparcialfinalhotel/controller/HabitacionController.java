package com.uca.pncparcialfinalhotel.controller;


import com.uca.pncparcialfinalhotel.model.entities.Habitacion;
import com.uca.pncparcialfinalhotel.service.HabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/habitaciones")
public class HabitacionController {

    @Autowired
    private HabitacionService habitacionService;

    // Cualquier usuario autenticado (incluso huéspedes) puede ver las habitaciones
    @GetMapping
    public ResponseEntity<?> obtenerHabitaciones() {
        return ResponseEntity.ok(habitacionService.obtenerTodas());
    }

    // SOLO los Administradores pueden agregar nuevas habitaciones al sistema
    @PostMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> crearHabitacion(@RequestBody Habitacion habitacion) {
        Habitacion nueva = habitacionService.guardar(habitacion);
        return ResponseEntity.ok(nueva);
    }

    // Administradores y Recepcionistas pueden actualizar el precio o disponibilidad
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'RECEPCIONISTA')")
    public ResponseEntity<?> actualizarHabitacion(@PathVariable Long id, @RequestBody Habitacion habitacion) {
        Habitacion actualizada = habitacionService.actualizar(id, habitacion);
        return ResponseEntity.ok(actualizada);
    }
}
