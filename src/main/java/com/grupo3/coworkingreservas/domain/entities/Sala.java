package com.grupo3.coworkingreservas.domain.entities;


import com.grupo3.coworkingreservas.utils.enums.EstadoSala;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
@Table(name = "salas")
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Min(value = 1, message = "La capacidad debe ser al menos 1")
    private Integer capacidad;

    private String descripcion;

    @Enumerated(EnumType.STRING)
    private EstadoSala estado = EstadoSala.DISPONIBLE;

    @OneToMany(mappedBy = "sala", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reserva> reservas = new ArrayList<>();

    @OneToMany(mappedBy = "sala")
    private List<Participante> participantes = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        if (this.estado == null) {
            this.estado = EstadoSala.DISPONIBLE;
        }
    }
}