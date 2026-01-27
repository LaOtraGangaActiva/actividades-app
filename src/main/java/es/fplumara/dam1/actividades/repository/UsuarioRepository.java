package es.fplumara.dam1.actividades.repository;

import es.fplumara.dam1.actividades.model.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository {
    Usuario save(Usuario usuario);
    Optional<Usuario> findById(long id);
    List<Usuario> findByEmail(String email);
    Optional<Usuario> findByDiscordUserId(String discordUserId);
    void deleteById(Long id);
}
