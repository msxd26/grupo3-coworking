package com.grupo3.coworkingreservas.repository;

import com.grupo3.coworkingreservas.domain.entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {


}