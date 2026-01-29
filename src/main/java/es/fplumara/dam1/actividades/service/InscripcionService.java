package es.fplumara.dam1.actividades.service;

import es.fplumara.dam1.actividades.model.Inscripcion;
import es.fplumara.dam1.actividades.model.PerfilUsuario;
import es.fplumara.dam1.actividades.model.RolInscripcion;
import es.fplumara.dam1.actividades.model.Usuario;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface InscripcionService {
    void inscribirUsuario(UUID tallerId, UUID usuarioId, RolInscripcion rol);
    void cambiarRol(UUID tallerId, UUID usuarioId, RolInscripcion rol);
    void expulsarusuario(UUID tallerId, UUID usuarioID);
    List<Inscripcion> listarInscripcionesDeTaller(UUID tallerId);
    List<Inscripcion> listarInscripcionesDeUsuario(UUID usuarioId);
    Map<PerfilUsuario, List<Usuario>> verMiembrosAgrupadosPorPerfil(UUID tallerId);
    List<Usuario> verResponsables(UUID tallerId);
}
