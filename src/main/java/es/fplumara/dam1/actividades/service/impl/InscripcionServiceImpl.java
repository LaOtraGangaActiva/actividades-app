package es.fplumara.dam1.actividades.service.impl;

import es.fplumara.dam1.actividades.dto.InscripcionCreateDto;
import es.fplumara.dam1.actividades.exception.BusinessRuleException;
import es.fplumara.dam1.actividades.exception.NotFoundException;
import es.fplumara.dam1.actividades.model.*;
import es.fplumara.dam1.actividades.repository.*;
import es.fplumara.dam1.actividades.repository.memory.*;
import es.fplumara.dam1.actividades.service.InscripcionService;
import es.fplumara.dam1.actividades.util.ValidatorUtils;

import java.util.*;

public class InscripcionServiceImpl implements InscripcionService {
    private InscripcionRepository inscripcionRepository = new InMemoryInscripcionRepository();
    private UsuarioRepository usuarioRepository = new InMemoryUsuarioRepository();
    private TallerRepository tallerRepository = new InMemoryTallerRepository();

    @Override
    public void inscribirUsuario(InscripcionCreateDto dto) {
        ValidatorUtils.validateEntity(dto);
        Taller taller = tallerRepository.findById(dto.idTaller()).orElseThrow(()-> new NotFoundException("El taller no existe"));
        Usuario usuario = usuarioRepository.findById(dto.idUsuario()).orElseThrow(()-> new NotFoundException("El usuario no existe"));
        if(inscripcionRepository.findByTallerIdAndUsuarioId(dto.idTaller(), dto.idUsuario()).isPresent()){
            throw new BusinessRuleException("Inscripción ya creada");
        }
        if(taller.getEstadoInscripcion().equals(EstadoInscripcion.CERRADO)) {
            throw new BusinessRuleException("EL taller no admite nuevas inscripciones");
        }
        if(!(dto.rol().equals(RolInscripcion.RESPONSABLE) && usuario.getPerfil().equals(PerfilUsuario.PROFESOR))){
            throw new BusinessRuleException("El responsable debe ser un profesor");
        }
        if(dto.rol().equals(RolInscripcion.PARTICIPANTE)) {
            if (taller.getCupo() <= inscripcionRepository.findByTallerId(dto.idTaller()).stream().filter(i -> i.getRol().equals(RolInscripcion.PARTICIPANTE)).count()) {
                throw new BusinessRuleException("EL cupo del taller está completo");
            }
        }
        inscripcionRepository.save(new Inscripcion(dto.idTaller(), dto.idUsuario(), dto.rol()));
    }

    @Override
    public void cambiarRol(InscripcionCreateDto dto) {
        ValidatorUtils.validateEntity(dto);
        Taller taller = tallerRepository.findById(dto.idTaller()).orElseThrow(()-> new NotFoundException("El taller no existe"));
        Usuario usuario = usuarioRepository.findById(dto.idUsuario()).orElseThrow(()-> new NotFoundException("El usuario no existe"));
        if(!(dto.rol().equals(RolInscripcion.RESPONSABLE) && usuario.getPerfil().equals(PerfilUsuario.PROFESOR))){
            throw new BusinessRuleException("El responsable debe ser un profesor");
        }
        if(dto.rol().equals(RolInscripcion.PARTICIPANTE)) {
            if (taller.getCupo() >= inscripcionRepository.findByTallerId(dto.idTaller()).stream().filter(i -> i.getRol().equals(RolInscripcion.PARTICIPANTE)).count()) {
                throw new BusinessRuleException("EL cupo del taller está completo");
            }
        }
        inscripcionRepository.findByTallerIdAndUsuarioId(dto.idTaller(), dto.idUsuario()).orElseThrow(NotFoundException::new).setRol(dto.rol());
    }

    @Override
    public void expulsarusuario(UUID tallerId, UUID usuarioId) {
        inscripcionRepository.findByTallerIdAndUsuarioId(tallerId, usuarioId).orElseThrow(()-> new NotFoundException("La inscripción no existe"));
        inscripcionRepository.deleteByTallerIdAndUsuarioId(tallerId, usuarioId);
    }

    @Override
    public List<Inscripcion> listarInscripcionesDeTaller(UUID tallerId) {
        tallerRepository.findById(tallerId).orElseThrow(()-> new NotFoundException("El taller no existe"));
        return inscripcionRepository.findByTallerId(tallerId);
    }

    @Override
    public List<Inscripcion> listarInscripcionesDeUsuario(UUID usuarioId) {
        usuarioRepository.findById(usuarioId).orElseThrow(()-> new NotFoundException("El usuario no existe"));
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
