package com.grupo3.coworkingreservas.domain.entities;


import com.grupo3.coworkingreservas.utils.enums.EstadoSala;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "salas")
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private Integer capacidad;

    private String descripcion;

    private EstadoSala estado;

    @OneToMany(
            cascade = CascadeType.ALL, fetch = FetchType.EAGER,
            mappedBy = "sala"
    )
    private List<Reserva> reservas;


    public Sala() {
        this.estado= EstadoSala.DISPONIBLE;
    }
}
