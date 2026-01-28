package es.fplumara.dam1.actividades.repository.memory;

import es.fplumara.dam1.actividades.model.Usuario;
import es.fplumara.dam1.actividades.repository.UsuarioRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class InMemoryUsuarioRepository implements UsuarioRepository {
    private Map<UUID, Usuario> almacen;

    @Override
    public Usuario save(Usuario usuario) {
        return null;
    }

    @Override
    public Optional<Usuario> findById(long id) {
        return Optional.empty();
    }

    @Override
    public List<Usuario> findByEmail(String email) {
        return List.of();
    }

    @Override
    public Optional<Usuario> findByDiscordUserId(String discordUserId) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }
}
