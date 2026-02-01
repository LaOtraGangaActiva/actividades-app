package es.fplumara.dam1.actividades.service.impl;

import es.fplumara.dam1.actividades.model.Inscripcion;
import es.fplumara.dam1.actividades.model.PerfilUsuario;
import es.fplumara.dam1.actividades.model.RolInscripcion;
import es.fplumara.dam1.actividades.model.Usuario;
import es.fplumara.dam1.actividades.service.InscripcionService;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class InscripcionServiceImpl extends InscripcionService {
    @Override
    public void inscribirUsuario(UUID tallerId, UUID usuarioId, RolInscripcion rol) {

    }

    @Override
    public void cambiarRol(UUID tallerId, UUID usuarioId, RolInscripcion rol) {

    }

    @Override
    public void expulsarusuario(UUID tallerId, UUID usuarioID) {

    }

    @Override
    public List<Inscripcion> listarInscripcionesDeTaller(UUID tallerId) {
        return List.of();
    }

    @Override
    public List<Inscripcion> listarInscripcionesDeUsuario(UUID usuarioId) {
        return List.of();
    }

    @Override
    public Map<PerfilUsuario, List<Usuario>> verMiembrosAgrupadosPorPerfil(UUID tallerId) {
        return Map.of();
    }

    @Override
    public List<Usuario> verResponsables(UUID tallerId) {
        return List.of();
    }
}
