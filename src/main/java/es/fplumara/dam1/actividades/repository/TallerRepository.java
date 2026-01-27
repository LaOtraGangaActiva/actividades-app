package es.fplumara.dam1.actividades.repository;

import es.fplumara.dam1.actividades.model.Usuario;

import java.util.Optional;

public interface TallerRepository {
    Usuario save(Usuario usuario);
    Optional<Usuario> getFindByyId(Long id);
    //Optional<Usuario> findByDiscordUserId(String discordUserId);
    void deleteBy(Long id);
}
