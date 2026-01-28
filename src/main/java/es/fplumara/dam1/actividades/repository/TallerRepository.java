package es.fplumara.dam1.actividades.repository;

import es.fplumara.dam1.actividades.model.Taller;
import es.fplumara.dam1.actividades.model.Usuario;

import java.util.Optional;
import java.util.UUID;


public interface TallerRepository {
    Taller save(Taller taller);
    Optional<Taller> findById(UUID id);
    void deleteById(UUID id);
}

