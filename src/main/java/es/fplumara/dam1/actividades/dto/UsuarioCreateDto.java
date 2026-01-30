package es.fplumara.dam1.actividades.dto;

import es.fplumara.dam1.actividades.model.PerfilUsuario;

public record UsuarioCreateDto(
        String nombre,
        PerfilUsuario perfil,
        String discordUserId,
        String curso,
        String email

) {}