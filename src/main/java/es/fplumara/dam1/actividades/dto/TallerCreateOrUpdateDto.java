package es.fplumara.dam1.actividades.dto;

import es.fplumara.dam1.actividades.model.EstadoInscripcion;
import jakarta.validation.constraints.*;

public record TallerCreateOrUpdateDto(

        String titulo,

        String descripcion,

        EstadoInscripcion estadoInscripcion,

        String url,

        int cupo,

        String lugar
) {
}
