package com.grupo3.coworkingreservas.repository;

import com.grupo3.coworkingreservas.domain.entities.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {

}