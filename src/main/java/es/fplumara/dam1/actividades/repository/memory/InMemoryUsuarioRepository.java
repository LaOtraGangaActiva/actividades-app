package es.fplumara.dam1.actividades.repository.memory;

import es.fplumara.dam1.actividades.model.Usuario;
import es.fplumara.dam1.actividades.repository.UsuarioRepository;

import java.util.*;

public class InMemoryUsuarioRepository implements UsuarioRepository {
    private Map<UUID, Usuario> storage = new HashMap<>();

    @Override
    public Usuario save(Usuario usuario) {
        if(usuario.getId() == null){
            usuario.setId(UUID.randomUUID());
        }
        storage.put(usuario.getId(), usuario);
        return usuario;
    }

    @Override
    public Optional<Usuario> findById(UUID id) {
        return Optional.of(storage.get(id));
    }

    @Override
    public List<Usuario> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return storage.values().stream().filter(i -> i.getEmail().equals(email)).findFirst();
    }

    @Override
    public Optional<Usuario> findByDiscordUserId(String discordUserId) {
        return storage.values().stream().filter(i -> i.getDiscordUserId().equals(discordUserId)).findFirst();
    }

    @Override
    public void deleteById(UUID id) {
        storage.remove(id);
    }
}
