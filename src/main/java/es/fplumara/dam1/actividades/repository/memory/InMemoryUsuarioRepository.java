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
        List<Usuario> usuarios = new ArrayList<>();
        for (Map.Entry<UUID, Usuario> entry : storage.entrySet()) {
            usuarios.add(entry.getValue());
        }
        return usuarios;
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        Optional<Usuario> usuario = Optional.empty();
        for (Map.Entry<UUID, Usuario> entry : storage.entrySet()) {
            if(email.equalsIgnoreCase(entry.getValue().getEmail())){
                usuario = Optional.of(entry.getValue());
            }
        }
        return usuario;
    }

    @Override
    public Optional<Usuario> findByDiscordUserId(String discordUserId) {
        Optional<Usuario> usuario = Optional.empty();
        for (Map.Entry<UUID, Usuario> entry : storage.entrySet()) {
            if(discordUserId.equalsIgnoreCase(entry.getValue().getDiscordUserId())){
                usuario = Optional.of(entry.getValue());
            }
        }
        return usuario;
    }

    @Override
    public void deleteById(UUID id) {
        storage.remove(id);
    }
}
