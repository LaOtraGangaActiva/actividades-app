package es.fplumara.dam1.actividades.repository;

import es.fplumara.dam1.actividades.model.Usuario;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository {
        Usuario save(Usuario usuario);
        Optional<Usuario> findById(UUID id);
        List<Usuario> findAll();
        Optional<Usuario> findByEmail(String email);
        Optional<Usuario> findByDiscordUserId(String discordUserId);
        void deleteById(UUID usuarioId); // deleted the other one
    }


