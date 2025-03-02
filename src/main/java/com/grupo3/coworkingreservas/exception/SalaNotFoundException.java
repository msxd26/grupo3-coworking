package com.grupo3.coworkingreservas.exception;

public class SalaNotFoundException extends RuntimeException {

    public SalaNotFoundException(Long id) {
        super("Sala no encontrada con ID: " + id);
    }
}