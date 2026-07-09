package com.uca.pncparcialfinalhotel.repository;

import com.uca.pncparcialfinalhotel.model.entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    @Query("SELECT r FROM Reserva r WHERE r.habitacion.id = :habitacionId " +
            "AND r.estado = 'CONFIRMADA' " +
            "AND (:fechaInicio < r.fechaFin AND :fechaFin > r.fechaInicio)")
    List<Reserva> findOverlappingReservations(
            @Param("habitacionId") Long habitacionId,
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin);
}
