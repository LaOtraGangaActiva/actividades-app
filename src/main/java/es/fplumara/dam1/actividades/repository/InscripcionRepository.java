package es.fplumara.dam1.actividades.repository;

import es.fplumara.dam1.actividades.model.Inscripcion;
import es.fplumara.dam1.actividades.model.RolInscripcion;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InscripcionRepository extends Repository<Inscripcion,String> {

    Optional<Inscripcion> findByTallerIdAndUsuarioId(UUID tallerId, UUID usuarioId);
    List<Inscripcion> findByTallerId(UUID tallerId);
    List<Inscripcion> findByUsuarioId(UUID usuarioId);
    List<Inscripcion> findByTallerIdAndRol(UUID tallerId, RolInscripcion rol);
    void deleteByUsuarioId(UUID usuarioId);
    void deleteByTallerIdAndUsuarioId(UUID tallerId, UUID usuarioId);
    void deleteByTallerId(UUID tallerId);
}