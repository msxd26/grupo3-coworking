package com.grupo3.coworkingreservas.exception;

public class ParticipanteNotFoundException extends RuntimeException {
    public ParticipanteNotFoundException(Long id) {
        super("Participante no encontrado con ID: " + id);
    }
}
