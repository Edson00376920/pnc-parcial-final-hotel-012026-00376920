package com.uca.pncparcialfinalhotel.dto;


import lombok.Data;
import java.time.LocalDate;

@Data
public class ReservaResponseDTO {
    private Long id;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String estado;
    private Long habitacionId;
    private String numeroHabitacion;
    private String nombreHotel;
    private String nombreUsuario;
}