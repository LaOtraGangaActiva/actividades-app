package es.fplumara.dam1.actividades.repository;

import es.fplumara.dam1.actividades.model.Inscripcion;
import es.fplumara.dam1.actividades.model.RolInscripcion;
import es.fplumara.dam1.actividades.model.Usuario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InscripcionRepository {
    void save(Inscripcion inscripcion);
    Optional<Inscripcion> findByTallerIdAndUsuarioId(UUID tallerId, UUID usuarioId);
    List<Inscripcion> findByTallerId(UUID tallerId);
    List<Inscripcion> findByUsuarioId(UUID usuarioId);
    List<Inscripcion> findByTallerIdAndRol(UUID tallerId, RolInscripcion rol);
    void deleteByTallerIdAndUsuarioId(UUID tallerId, Usuario usuarioId);
   // void deleteByUsuarioId(UUID usuarioId);
}
