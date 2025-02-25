package com.grupo3.coworkingreservas.domain.entities;

import com.grupo3.coworkingreservas.utils.enums.EstadoReserva;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sala")
    private Sala sala;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    @Enumerated(EnumType.STRING)
    private EstadoReserva estado;


    @OneToMany(
            cascade = CascadeType.ALL, fetch = FetchType.EAGER,
            mappedBy = "reserva"
    )
    private List<Participante> participantes;

    public Reserva() {
        this.estado = EstadoReserva.ACTIVO;
    }

    @PostUpdate
    public void postUpdate() {
        fechaFin = LocalDate.now();
    }
}
