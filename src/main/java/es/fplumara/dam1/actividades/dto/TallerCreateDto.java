package es.fplumara.dam1.actividades.dto;

import es.fplumara.dam1.actividades.model.EstadoInscripcion;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

public record TallerCreateDto(String titulo,
@NotBlank(message = "El descripcion no puede estar vacío")
                              String descripcion,
@NotNull(message = "El EstadoInscripcion es obligatorio")
                              EstadoInscripcion estadoInscripcion,
@URL
@NotBlank(message = "La url no puede estar vacío")
                              String url,
@Min(value = 1, message ="El cupo debe ser al menos 1" )
                              int cupo,
@NotBlank(message = "el lugar no puede estar vacío")
                              String lugar) {
}
