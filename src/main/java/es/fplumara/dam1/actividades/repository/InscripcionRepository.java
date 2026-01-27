package es.fplumara.dam1.actividades.repository;

public interface InscripcionRepository {


}





















/*package es.fplumara.dam1.actividades.repository;

import es.fplumara.dam1.actividades.model.Inscripcion;
import es.fplumara.dam1.actividades.model.RolInscripcion;
import java.util.List;
import java.util.Optional;

public interface InscripcionRepository {
    Inscripcion save(Inscripcion inscripcion);
    Optional<Inscripcion> findById(Long id);
    Optional<Inscripcion> findByTallerIdAndUsuarioId(Long tallerId, Long usuarioId);
    List<Inscripcion> findByTallerId(Long tallerId);
    List<Inscripcion> findByUsuarioId(Long usuarioId);
    List<Inscripcion> findByTallerIdAndRol(Long tallerId, RolInscripcion rol);
    void deleteById(Long id);
    void deleteByTallerIdAndUsuarioId(Long tallerId, Long usuarioId);
}*/