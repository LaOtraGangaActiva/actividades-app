package es.fplumara.dam1.actividades.repository.memory;

import es.fplumara.dam1.actividades.model.Taller;
import es.fplumara.dam1.actividades.repository.TallerRepository;

import java.util.*;

public class InMemoryTallerRepository implements TallerRepository {

    private final Map<UUID, Taller> storage = new HashMap<>();

    public Taller save(Taller taller) {
        if (taller.getId() == null) {
            taller.setId(UUID.randomUUID());
        }
        storage.put(taller.getId(), taller);
        return taller;
    }

    public Optional<Taller> findById(UUID id) {
        return Optional.ofNullable(storage.get(id));
    }


    public List<Taller> findAll() {
        return new ArrayList<>(storage.values());
    }


    public void deleteById(UUID id) {
        storage.remove(id);
    }
}
