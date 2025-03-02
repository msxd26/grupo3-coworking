package com.grupo3.coworkingreservas.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ParticipanteDTO {
    private Long id;


    private Long salaId;

    @NotNull(message = "Id de Reserva Obligatorio")
    private Long reservaId;

    @NotNull(message = "Id de Usuario Obligatorio")
    private Long usuarioId;
}