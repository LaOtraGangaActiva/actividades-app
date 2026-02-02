package es.fplumara.dam1.actividades.dto;

import es.fplumara.dam1.actividades.model.EstadoInscripcion;
import es.fplumara.dam1.actividades.model.PerfilUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

import java.util.Optional;

public record TallerUpdateDto(Optional<String> titulo,
Optional<@NotBlank String> descripcion,
Optional<@NotNull EstadoInscripcion> estadoInscripcion,
Optional<@URL String> url,
Optional<@Min(1) Integer> cupo,
Optional<@NotBlank String> lugar
) {}
/*@NotNull ----- The value must exist
@NotBlank --------@NotBlank ---Use when  required AND must contain meaningful text


 */
