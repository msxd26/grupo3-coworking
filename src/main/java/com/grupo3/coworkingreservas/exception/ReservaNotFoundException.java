package com.grupo3.coworkingreservas.exception;

public class ReservaNotFoundException extends RuntimeException{

    public ReservaNotFoundException(Long id) {
        super("Reserva no encontrada con ID: " + id);
    }

}
