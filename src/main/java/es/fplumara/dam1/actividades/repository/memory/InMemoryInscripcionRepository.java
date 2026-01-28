package es.fplumara.dam1.actividades.repository.memory;

import es.fplumara.dam1.actividades.model.Inscripcion;
import es.fplumara.dam1.actividades.model.RolInscripcion;
import es.fplumara.dam1.actividades.model.Usuario;
import es.fplumara.dam1.actividades.repository.InscripcionRepository;

import java.util.*;

public class InMemoryInscripcionRepository implements InscripcionRepository {
    private Map<String, Inscripcion> storage = new HashMap<>();

    @Override
    public Inscripcion save(Inscripcion inscripcion) {
        storage.put(inscripcion.getId(), inscripcion);
        return null;
    }

    @Override
    public Optional<Inscripcion> findByTallerIdAndUsuarioId(UUID tallerId, UUID usuarioId) {
        return Optional.empty();
    }

    @Override
    public List<Inscripcion> findByTallerId(UUID tallerId) {
        List<Inscripcion> inscripcions = new ArrayList<>();
        for(Map.Entry<String, Inscripcion> entry : storage.entrySet()){
            if(entry.getValue().getIdTaller() == tallerId){
                inscripcions.add(entry.getValue());
            }
        }
        return inscripcions;
    }

    @Override
    public List<Inscripcion> findByUsuarioId(UUID usuarioId) {
        List<Inscripcion> inscripcions = new ArrayList<>();
        for(Map.Entry<String, Inscripcion> entry : storage.entrySet()){
            if(entry.getValue().getIdUsuario() == usuarioId){
                inscripcions.add(entry.getValue());
            }
        }
        return inscripcions;
    }

    @Override
    public List<Inscripcion> findByTallerIdAndRol(UUID tallerId, RolInscripcion rol) {
        return List.of();
    }

    @Override
    public void deleteByTallerIdAndUsuarioId(UUID tallerId, UUID usuarioId) {

    }
}
