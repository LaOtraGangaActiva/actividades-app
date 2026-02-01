package es.fplumara.dam1.actividades.service.impl;

import es.fplumara.dam1.actividades.dto.InscripcionCreateDto;
import es.fplumara.dam1.actividades.model.*;
import es.fplumara.dam1.actividades.repository.*;
import es.fplumara.dam1.actividades.repository.memory.*;
import es.fplumara.dam1.actividades.service.InscripcionService;

import java.util.*;

public class InscripcionServiceImpl implements InscripcionService {
    private InscripcionRepository inscripcionRepository = new InMemoryInscripcionRepository();
    private UsuarioRepository usuarioRepository = new InMemoryUsuarioRepository();

    @Override
    public void inscribirUsuario(InscripcionCreateDto dto) {

    }

    @Override
    public void cambiarRol(InscripcionCreateDto dto) {

    }

    @Override
    public void expulsarusuario(UUID tallerId, UUID usuarioId) {
        inscripcionRepository.deleteByTallerIdAndUsuarioId(tallerId, usuarioId);
    }

    @Override
    public List<Inscripcion> listarInscripcionesDeTaller(UUID tallerId) {
        return inscripcionRepository.findByTallerId(tallerId);
    }

    @Override
    public List<Inscripcion> listarInscripcionesDeUsuario(UUID usuarioId) {
        return inscripcionRepository.findByUsuarioId(usuarioId);
    }

    @Override
    public Map<PerfilUsuario, List<Usuario>> verMiembrosAgrupadosPorPerfil(UUID tallerId) {
        Map<PerfilUsuario, List<Usuario>> miembros = new HashMap<>();
        Arrays.stream(PerfilUsuario.values()).forEach(v -> miembros.put(v, new ArrayList<>()));
        List<Inscripcion> inscripciones = inscripcionRepository.findByTallerId(tallerId);
        for (Inscripcion i : inscripciones){
            Usuario user = usuarioRepository.findById(i.getIdUsuario()).get();
            miembros.get(user.getPerfil()).add(user);
        }
        return miembros;
    }

    @Override
    public List<Usuario> verResponsables(UUID tallerId) {
        List<Usuario> responsables = new ArrayList<>();
        inscripcionRepository.findByTallerId(tallerId).stream().filter(t -> t.getRol().equals(RolInscripcion.RESPONSABLE)).forEach(t -> responsables.add(usuarioRepository.findById(t.getIdUsuario()).get()));
        return responsables;
    }
}
