package com.grupo3.coworkingreservas.repository;

import com.grupo3.coworkingreservas.domain.entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {


}