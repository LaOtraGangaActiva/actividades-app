package es.fplumara.dam1.actividades.repository.memory;

import es.fplumara.dam1.actividades.model.Taller;
import es.fplumara.dam1.actividades.repository.TallerRepository;

import java.util.*;

public class InMemoryTallerRepository implements TallerRepository {

    private Map<UUID, Taller> storage = new HashMap<>();

    @Override
    public Taller save(Taller taller) {
        if (taller.getId() == null) {
            taller.setId(UUID.randomUUID());
        }
        storage.put(taller.getId(), taller);
        return taller;
    }

    @Override
    public Optional<Taller> findById(UUID id) {
        return Optional.of(storage.get(id));
    }

    @Override
    public List<Taller> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void deleteById(UUID id) {
        storage.remove(id);
    }
}
