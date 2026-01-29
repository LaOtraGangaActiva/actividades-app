package es.fplumara.dam1.actividades.dto;

import es.fplumara.dam1.actividades.model.EstadoInscripcion;
import es.fplumara.dam1.actividades.model.Taller;

public record TallerCreateDto(

        String titulo,

        String descripcion,

        EstadoInscripcion estadoInscripcion,

        String url,

        int cupo,

        String lugar
) {

}
