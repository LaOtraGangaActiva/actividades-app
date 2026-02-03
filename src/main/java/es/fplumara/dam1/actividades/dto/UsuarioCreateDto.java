package es.fplumara.dam1.actividades.dto;

import es.fplumara.dam1.actividades.model.PerfilUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioCreateDto(

        @NotBlank(message = "El nombre no puede estar vacío")
        String nombre,
        @NotNull(message = "El perfil es obligatorio")
        PerfilUsuario perfil,
        @NotBlank(message = "DiscordUserId es obligatorio")
        String discordUserId,
        String curso,
        @Email(message = "Email no válido")
        String email
) {}
/* So notBlank ensure the String is not null, empty and whitespace
notNull ensurees the field is not null but allows empty String
 */

