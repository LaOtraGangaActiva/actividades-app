package es.fplumara.dam1.actividades.dto;

import es.fplumara.dam1.actividades.model.RolInscripcion;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record InscripcionCreateDto(
        @NotNull(message = "Debe tener un taller asociado")
        UUID idTaller,
        @NotNull(message = "El id del usuario no puede estar vacio")
        UUID idUsuario,
        @NotNull(message = "Debe tener un rol")
        RolInscripcion rol
) {

}
