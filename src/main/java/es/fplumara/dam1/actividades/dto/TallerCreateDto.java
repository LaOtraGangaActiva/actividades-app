package es.fplumara.dam1.actividades.dto;

import es.fplumara.dam1.actividades.model.EstadoInscripcion;
import es.fplumara.dam1.actividades.model.Taller;
import jakarta.validation.constraints.*;

public record TallerCreateDto(

        @NotBlank
        @Size(max=25)
        String titulo,

        @NotBlank
        String descripcion,

        @NotNull
        EstadoInscripcion estadoInscripcion,

        @NotBlank
        String url,

        @PositiveOrZero
        @Max(100)
        int cupo,

        @NotBlank
        String lugar
) {
}
