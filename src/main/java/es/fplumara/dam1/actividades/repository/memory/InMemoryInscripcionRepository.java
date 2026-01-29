package es.fplumara.dam1.actividades.repository.memory;

import es.fplumara.dam1.actividades.model.Inscripcion;
import es.fplumara.dam1.actividades.model.RolInscripcion;
import es.fplumara.dam1.actividades.repository.InscripcionRepository;

import java.util.*;

public class InMemoryInscripcionRepository implements InscripcionRepository {
    private Map<String, Inscripcion> storage = new HashMap<>();

    @Override
    public void save(Inscripcion inscripcion) {
        storage.put(inscripcion.getId(), inscripcion);
    }

    @Override
    public Optional<Inscripcion> findByTallerIdAndUsuarioId(UUID tallerId, UUID usuarioId) {
        return storage.values().stream()
                .filter(i -> i.getIdTaller().equals(tallerId) && i.getIdUsuario().equals(usuarioId)
                ).findFirst();
    }

    @Override
    public List<Inscripcion> findByTallerId(UUID tallerId) {
        List<Inscripcion> inscripcions = new ArrayList<>();
        for (Map.Entry<String, Inscripcion> entry : storage.entrySet()) {
            if (entry.getValue().getIdTaller() == tallerId) {
                inscripcions.add(entry.getValue());
            }
        }
        return inscripcions;
    }

    @Override
    public List<Inscripcion> findByUsuarioId(UUID usuarioId) {
        List<Inscripcion> inscripcions = new ArrayList<>();
        for (Map.Entry<String, Inscripcion> entry : storage.entrySet()) {
            if (entry.getValue().getIdUsuario() == usuarioId) {
                inscripcions.add(entry.getValue());
            }
        }
        return inscripcions;
    }

    @Override
    public List<Inscripcion> findByTallerIdAndRol(UUID tallerId, RolInscripcion rol) {
        List<Inscripcion> inscripciones = new ArrayList<>();
        for (Map.Entry<String, Inscripcion> entry : storage.entrySet()) {
            if (entry.getValue().getIdTaller() == tallerId) {
                if (entry.getValue().getRol() == rol) {
                    inscripciones.add(entry.getValue());
                }
            }
        }
        return inscripciones;
    }

    @Override
    public void deleteByTallerIdAndUsuarioId(UUID tallerId, UUID usuarioId) {
        for (Map.Entry<String, Inscripcion> entry : storage.entrySet()) {
            if (entry.getValue().getIdTaller() == tallerId) {
                if (entry.getValue().getIdUsuario() == usuarioId) {
                    storage.remove(entry.getKey());
                }
            }
        }
    }
}
