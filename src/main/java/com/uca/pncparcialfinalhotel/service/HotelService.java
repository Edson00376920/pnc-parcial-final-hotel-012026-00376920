package com.uca.pncparcialfinalhotel.service;

import com.uca.pncparcialfinalhotel.model.entities.Hotel;
import com.uca.pncparcialfinalhotel.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    public List<Hotel> obtenerTodos() {
        return hotelRepository.findAll();
    }

    public Hotel guardar(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public Hotel obtenerPorId(Long id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel no encontrado con ID: " + id));
    }

    public void eliminar(Long id) {
        hotelRepository.deleteById(id);
    }
}
