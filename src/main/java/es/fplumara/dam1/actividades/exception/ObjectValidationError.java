package es.fplumara.dam1.actividades.exception;

import java.util.List;

public class ObjectValidationError extends RuntimeException {

    List<String> fieldErrors;

    public ObjectValidationError(List<String> fieldErrors) {
        super("Error de validación: "+fieldErrors);
        this.fieldErrors = fieldErrors;
    }


}