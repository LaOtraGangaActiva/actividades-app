package es.fplumara.dam1.actividades.repository.memory;

import es.fplumara.dam1.actividades.model.Inscripcion;
import es.fplumara.dam1.actividades.model.RolInscripcion;
import es.fplumara.dam1.actividades.repository.InscripcionRepository;

import java.util.*;

public class InMemoryInscripcionRepository extends InMemoryRepository<Inscripcion,String> implements InscripcionRepository {

    @Override
    public Optional<Inscripcion> findByTallerIdAndUsuarioId(UUID tallerId, UUID usuarioId) {
        return this.findById(tallerId.toString() + "/" + usuarioId.toString());
    }

    @Override
    public List<Inscripcion> findByTallerId(UUID tallerId) {
        List<Inscripcion> inscripcions = new ArrayList<>();
        storage.values().stream().filter(i -> i.getIdTaller().equals(tallerId)).forEach(inscripcions::add);
        return inscripcions;
    }

    @Override
    public List<Inscripcion> findByUsuarioId(UUID usuarioId) {
        List<Inscripcion> inscripcions = new ArrayList<>();
        storage.values().stream().filter(i -> i.getIdUsuario().equals(usuarioId)).forEach(inscripcions::add);
        return inscripcions;
    }

    @Override
    public List<Inscripcion> findByTallerIdAndRol(UUID tallerId, RolInscripcion rol) {
        List<Inscripcion> inscripciones = new ArrayList<>();
        storage.values().stream().filter(i -> i.getIdTaller().equals(tallerId) && i.getRol().equals(rol)).forEach(inscripciones::add);
        return inscripciones;
    }

    @Override
    public void deleteByUsuarioId(UUID usuarioId){
        storage.values().removeIf(i -> i.getIdUsuario().equals(usuarioId));

    }

    @Override
    public void deleteByTallerIdAndUsuarioId(UUID tallerId, UUID usuarioId) {
       this.deleteById(tallerId.toString() + "/" + usuarioId.toString());
    }

    @Override
    public void deleteByTallerId(UUID tallerId) {

    }
}
