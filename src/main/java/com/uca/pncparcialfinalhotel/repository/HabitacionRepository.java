package com.uca.pncparcialfinalhotel.repository;

import com.uca.pncparcialfinalhotel.model.entities.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {

    List<Habitacion> findByHotelId(Long hotelId);

    List<Habitacion> findByDisponibleTrue();

    List<Habitacion> findByHotelIdAndTipo(Long hotelId, String tipo);
}