package com.grupo3.coworkingreservas.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UsuarioDTO {
    private Long id;

    @NotBlank(message = "Nombre Obligatorio")
    private String nombre;

    @Email(message = "Correo Obligatorio")
    private String email;

    private LocalDateTime fechaRegistro;
}