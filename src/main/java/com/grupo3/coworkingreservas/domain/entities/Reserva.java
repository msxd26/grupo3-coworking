package com.grupo3.coworkingreservas.domain.entities;

import com.grupo3.coworkingreservas.utils.enums.EstadoReserva;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reservas")
public class Reserva {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sala")
    private Sala sala;

    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDateTime fechaInicio;

    @NotNull(message = "La fecha de fin es obligatoria")
    private LocalDateTime fechaFin;

    @Enumerated(EnumType.STRING)
    private EstadoReserva estado = EstadoReserva.PENDIENTE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Participante> participantes = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        if (this.estado == null) {
            this.estado = EstadoReserva.PENDIENTE;
        }
    }

    @PostUpdate
    public void postUpdate() {
        if (this.estado == EstadoReserva.CANCELADA || this.estado == EstadoReserva.FINALIZADA) {
            this.fechaFin = LocalDateTime.now();
        }
    }
}