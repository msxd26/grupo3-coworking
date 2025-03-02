package com.grupo3.coworkingreservas.domain.dto;

import com.grupo3.coworkingreservas.domain.entities.Sala;
import com.grupo3.coworkingreservas.utils.enums.EstadoReserva;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservaDTO {

    private Long id;

    @NotNull(message = "Id de Sala Obligatorio")
    private SalaDTO sala;

    @NotNull(message = "Fecha de Inicio obligatoria")
    private LocalDateTime fechaInicio;

    @NotNull(message = "Fecha de Finalizacion obligatoria")
    private LocalDateTime fechaFin;

    private UsuarioDTO usuario;

    private EstadoReserva estadoReserva;
}