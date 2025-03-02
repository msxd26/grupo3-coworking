package com.grupo3.coworkingreservas.domain.dto;

import com.grupo3.coworkingreservas.utils.enums.EstadoReserva;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservaDTO {
    private Long id;

    @NotNull(message = "Id de Sala Obligatorio")
    private Long salaId;

    @NotNull(message = "Fecha de Inicio obligatoria")
    private LocalDateTime fechaInicio;

    @NotNull(message = "Fecha de Finalizacion obligatoria")
    private LocalDateTime fechaFin;

    @Email(message = "Email debe ser Valido")
    private String email;
    private EstadoReserva estadoReserva;
}