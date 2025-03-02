package com.grupo3.coworkingreservas.domain.dto;


import com.grupo3.coworkingreservas.utils.enums.EstadoSala;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SalaDTO {


    private Long id;

    @NotBlank(message = "Nombre Obligatorio")
    private String nombre;

    @Min(value = 1, message = "Capacidad de al menos Uno")
    private Integer capacidad;

    private String descripcion;

    private EstadoSala estadoSala;
}