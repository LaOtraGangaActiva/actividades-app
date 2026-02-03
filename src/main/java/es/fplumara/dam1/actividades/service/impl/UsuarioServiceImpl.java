package es.fplumara.dam1.actividades.service.impl;

import es.fplumara.dam1.actividades.dto.UsuarioCreateDto;
import es.fplumara.dam1.actividades.dto.UsuarioUpdateDto;
import es.fplumara.dam1.actividades.exception.BusinessRuleException;
import es.fplumara.dam1.actividades.exception.NotFoundException;
import es.fplumara.dam1.actividades.model.PerfilUsuario;
import es.fplumara.dam1.actividades.model.Usuario;
import es.fplumara.dam1.actividades.repository.InscripcionRepository;
import es.fplumara.dam1.actividades.repository.UsuarioRepository;
import es.fplumara.dam1.actividades.service.UsuarioService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

    public class UsuarioServiceImpl implements UsuarioService {

        private final UsuarioRepository usuarioRepository;
        private final InscripcionRepository inscripcionRepository;

        public UsuarioServiceImpl(
                UsuarioRepository usuarioRepository,
                InscripcionRepository inscripcionRepository
        ) {
            this.usuarioRepository = usuarioRepository;
            this.inscripcionRepository = inscripcionRepository;
        }

    // CREATE
    @Override
    public Usuario crearUsuario(UsuarioCreateDto dto) {
        // Validate name
        if (dto.nombre() == null || dto.nombre().isBlank()) {
            throw new BusinessRuleException("El nombre es obligatorio");
        }

        // Validate course/profile rule
        if (dto.curso() != null && dto.perfil() != PerfilUsuario.ALUMNO) {
            throw new BusinessRuleException("El curso solo está disponible para estudiantes");
        }

        // Check for duplicate email
        if (dto.email() != null && !dto.email().isBlank()) {
            usuarioRepository.findByEmail(dto.email())
                    .ifPresent(u -> {
                        throw new BusinessRuleException("El correo electrónico ya está registrado");
                    });
        }

        // Check for duplicate Discord id
        if (dto.discordUserId() != null && !dto.discordUserId().isBlank()) {
            usuarioRepository.findByDiscordUserId(dto.discordUserId())
                    .ifPresent(u -> {
                        throw new BusinessRuleException("El ID de Discord ya existe");
                    });
        }

        // Create user with null ID (in repository with uuid generate / generate it)
        Usuario usuario = new Usuario(
                null,
                dto.nombre(),
                dto.perfil(),
                dto.discordUserId(),
                dto.curso(),
                dto.email()
        );

        return usuarioRepository.save(usuario);
    }

    // LIST ALL USERS - FIXED
    @Override
    public List<Usuario> listarUsuario() {
        return usuarioRepository.findAll();  // NOT List.of()
    }

    // GET USER BY ID
    @Override
    public Optional<Usuario> obtenerUsuario(UUID id) {
        return usuarioRepository.findById(id);
    }

    // UPDATE - FIXED (using record methods, not getters)
    @Override
    public Usuario actualizarUsuario(UUID id, UsuarioUpdateDto dto) {
        // Find existing user
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

        // Update name if provided
        if (dto.nombre().isPresent()) {
            String newName = dto.nombre().get();
            if (newName.isBlank()) {
                throw new BusinessRuleException("El nombre no puede estar vacío");
            }
            usuario.setNombre(newName);
        }

        // Update profile if provided
        if (dto.perfil().isPresent()) {
            usuario.setPerfil(dto.perfil().get());

            // If changing from STUDENT to another profile, clear course
            if (dto.perfil().get() != PerfilUsuario.ALUMNO) {
                usuario.setCurso(null);
            }
        }

        // Update course if provided (only for students)
        if (dto.curso().isPresent()) {
            if (usuario.getPerfil() != PerfilUsuario.ALUMNO) {
                throw new BusinessRuleException("El curso solo está disponible para estudiantes");
            }
            usuario.setCurso(dto.curso().get());
        }

        // Update email if provided
        if (dto.email().isPresent()) {
            String newEmail = dto.email().get();
            if (!newEmail.isBlank()) {
                // Check for duplicate email (excluding current user)
                usuarioRepository.findByEmail(newEmail)
                        .ifPresent(otherUser -> {
                            if (!otherUser.getId().equals(id)) {
                                throw new BusinessRuleException("El correo electrónico ya está en uso");
                            }
                        });
                usuario.setEmail(newEmail);
            }
        }

        // Update Discord ID if provided
        if (dto.discordUserId().isPresent()) {
            String newDiscordId = dto.discordUserId().get();
            if (!newDiscordId.isBlank()) {
                // Check for duplicate Discord ID *excluding current user)
                usuarioRepository.findByDiscordUserId(newDiscordId)
                        .ifPresent(otherUser -> {
                            if (!otherUser.getId().equals(id)) {
                                throw new BusinessRuleException("El ID de Discord ya está en uso");
                            }
                        });
                usuario.setDiscordUserId(newDiscordId);
            }
        }

        return usuarioRepository.save(usuario);
    }

    // Delete - fix (delete the user)
    @Override
    public void eliminarUsuario(UUID id) {
        // Check if user exists
        usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado. "));

        //delete all userinscriptions first (cascade)
        inscripcionRepository.deleteByUsuarioId(id);

        // delete the user
        usuarioRepository.deleteById(id);
    }

/*
    // If I need this method, I can rename it to something else
    public List<Usuario> listarUsuarios() {
        return listarUsuario();  // Call the existing method
    }*/
}