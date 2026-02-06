package es.fplumara.dam1.actividades.service.impl;

import es.fplumara.dam1.actividades.dto.UsuarioCreateDto;
import es.fplumara.dam1.actividades.dto.UsuarioUpdateDto;
import es.fplumara.dam1.actividades.exception.BusinessRuleException;
import es.fplumara.dam1.actividades.exception.NotFoundException;
import es.fplumara.dam1.actividades.model.PerfilUsuario;
import es.fplumara.dam1.actividades.model.Usuario;
import es.fplumara.dam1.actividades.repository.InscripcionRepository;
import es.fplumara.dam1.actividades.repository.UsuarioRepository;
import es.fplumara.dam1.actividades.repository.memory.InMemoryInscripcionRepository;
import es.fplumara.dam1.actividades.repository.memory.InMemoryUsuarioRepository;
import es.fplumara.dam1.actividades.service.UsuarioService;
import es.fplumara.dam1.actividades.util.ValidatorUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final InscripcionRepository inscripcionRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, InscripcionRepository inscripcionRepository) {
        this.usuarioRepository = usuarioRepository;
        this.inscripcionRepository = inscripcionRepository;
    }

    // CREATE
    @Override
    public Usuario crearUsuario(UsuarioCreateDto dto) {

        // Jakarta Validation
        ValidatorUtils.validateEntity(dto);

        // curso only para alumno
        if (dto.curso() != null && dto.perfil() != PerfilUsuario.ALUMNO) {
            throw new BusinessRuleException("The course is only for students");
        }

        //  Duplicate email
        if (dto.Email() != null && !dto.Email().isBlank()) {
            usuarioRepository.findByEmail(dto.Email())
                    .ifPresent(u -> {
                        throw new BusinessRuleException("The email is already registered");
                    });
        }

        // Duplicate Discord ID
        if (dto.discordUserId() != null && !dto.discordUserId().isBlank()) {
            usuarioRepository.findByDiscordUserId(dto.discordUserId())
                    .ifPresent(u -> {
                        throw new BusinessRuleException("El ID de Discord ya existe");
                    });
        }

        Usuario usuario = new Usuario(
                null,
                dto.nombre(),
                dto.perfil(),
                dto.discordUserId(),
                dto.curso(),
                dto.Email()
        );

        return usuarioRepository.save(usuario);
    }

    // LIST
    @Override
    public List<Usuario> listarUsuario() {
        return usuarioRepository.findAll();
    }

    // GET
    @Override
    public Optional<Usuario> obtenerUsuario(UUID id) {
        return usuarioRepository.findById(id);
    }

    // UPDATE
    @Override
    public Usuario actualizarUsuario(UUID id, UsuarioUpdateDto dto) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

        if (dto.nombre().isPresent()) {
            String newName = dto.nombre().get();
            if (newName.isBlank()) {
                throw new BusinessRuleException("El nombre no puede estar vacío");
            }
            usuario.setNombre(newName);
        }

        if (dto.perfil().isPresent()) {
            usuario.setPerfil(dto.perfil().get());
            if (dto.perfil().get() != PerfilUsuario.ALUMNO) {
                usuario.setCurso(null);
            }
        }

        if (dto.curso().isPresent()) {
            if (usuario.getPerfil() != PerfilUsuario.ALUMNO) {
                throw new BusinessRuleException("The curse is only for students");
            }
            usuario.setCurso(dto.curso().get());
        }

        if (dto.email().isPresent()) {
            String newEmail = dto.email().get();
            if (!newEmail.isBlank()) {
                usuarioRepository.findByEmail(newEmail)
                        .ifPresent(other -> {
                            if (!other.getId().equals(id)) {
                                throw new BusinessRuleException("The email is already used");
                            }
                        });
                usuario.setEmail(newEmail);
            }
        }

        if (dto.discordUserId().isPresent()) {
            String newDiscordId = dto.discordUserId().get();
            if (!newDiscordId.isBlank()) {
                usuarioRepository.findByDiscordUserId(newDiscordId)
                        .ifPresent(other -> {
                            if (!other.getId().equals(id)) {
                                throw new BusinessRuleException("The discord ID in use");
                            }
                        });
                usuario.setDiscordUserId(newDiscordId);
            }
        }

        return usuarioRepository.save(usuario);
    }

    // DELETE
    @Override
    public void eliminarUsuario(UUID id) {

        usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("can not find the user"));

        inscripcionRepository.deleteByUsuarioId(id);
        usuarioRepository.deleteById(id);
    }
}
/*
    // If I need this method, I can rename it to something else
    public List<Usuario> listarUsuarios() {
        return listarUsuario();  // Call the existing method
    }*/
