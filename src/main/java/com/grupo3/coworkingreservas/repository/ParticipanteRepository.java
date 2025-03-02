package com.grupo3.coworkingreservas.repository;

import com.grupo3.coworkingreservas.domain.entities.Participante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipanteRepository extends JpaRepository <Participante, Long> {
}
