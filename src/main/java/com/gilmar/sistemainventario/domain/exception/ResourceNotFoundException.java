package com.gilmar.sistemainventario.domain.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resource, Object identifier) {
        super(resource + " no encontrado con identificador: " + identifier);
    }
}
