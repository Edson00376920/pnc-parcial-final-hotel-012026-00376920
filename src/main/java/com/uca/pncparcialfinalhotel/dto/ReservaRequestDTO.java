package com.uca.pncparcialfinalhotel.dto;


import lombok.Data;
import java.time.LocalDate;

@Data
public class ReservaRequestDTO {
    private Long habitacionId;
    private Long usuarioId;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}