package com.grupo3.coworkingreservas.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private Long id;

    @NotBlank(message = "Nombre Obligatorio")
    private String nombre;

    @Email(message = "Correo Obligatorio")
    private String email;

    private LocalDateTime fechaRegistro;
    @Email(message = "El correo electrónico debe ser válido")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime fechaNacimiento;

    private boolean isAdmin;

}