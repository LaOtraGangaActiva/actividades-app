package es.fplumara.dam1.actividades.dto;

import es.fplumara.dam1.actividades.model.PerfilUsuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioCreateDto(
       @NotBlank(message =  "el nombre can not be blank")
       @NotNull
       String nombre,
        PerfilUsuario perfil,
        @NotNull
        String discordUserId,
        String curso,
        String email
) {}

