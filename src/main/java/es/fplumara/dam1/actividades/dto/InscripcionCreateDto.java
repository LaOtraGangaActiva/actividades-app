package es.fplumara.dam1.actividades.dto;

import es.fplumara.dam1.actividades.model.RolInscripcion;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record InscripcionCreateDto(

        UUID idTaller,

        UUID idUsuario,

        RolInscripcion rol
) {

}
