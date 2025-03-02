package com.grupo3.coworkingreservas.exception;

public class UsuarioNotFoundException extends RuntimeException{
    public UsuarioNotFoundException(Long id) {
        super("El usuario con el id " + id + " no existe");
    }
}
