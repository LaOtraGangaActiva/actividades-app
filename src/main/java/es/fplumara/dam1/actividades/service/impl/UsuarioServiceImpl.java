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

            if (dto.nombre() == null || dto.nombre().isBlank()) {
                throw new BusinessRuleException("Nombre es obligatorio");
            }

            if (dto.curso() != null && dto.perfil() != PerfilUsuario.ALUMNO) {
                throw new BusinessRuleException("Curso solo permitido para ALUMNOS");
            }

            usuarioRepository.findAll().forEach(u -> {
                if (dto.email() != null && dto.email().equals(u.getEmail())) {
                    throw new BusinessRuleException("Email duplicado");
                }
                if (dto.discordUserId() != null &&
                        dto.discordUserId().equals(u.getDiscordUserId())) {
                    throw new BusinessRuleException("DiscordUserId duplicado");
                }
            });

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

        //leer

        public List<Usuario> listarUsuarios() {
            return usuarioRepository.findAll();
        }


        public Usuario crearUsuario(Usuario usuario) {
            return null;
        }

        @Override
        public List<Usuario> listarUsuario() {
            return List.of();
        }

        @Override
        public Optional<Usuario> obtenerUsuario(UUID id) {
            return usuarioRepository.findById(id);
        }

        @Override
        public Usuario actualizarUsuario(UUID id, Usuario usuario) {
            return null;
        }

        //update**

        public Usuario actualizarUsuario(UUID id, UsuarioUpdateDto dto) {

            Usuario usuario = usuarioRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("could not find the user"));

            if (dto.getNombre() != null) usuario.setNombre(dto.getNombre());
            if (dto.getPerfil() != null) usuario.setPerfil(dto.getPerfil());

            if (dto.getCurso() != null && usuario.getPerfil() != PerfilUsuario.ALUMNO) {
                throw new BusinessRuleException("Just for alumnos");
            }
            if (dto.getCurso() != null) usuario.setCurso(dto.getCurso());

            if (dto.getEmail() != null) {
                usuarioRepository.findAll().forEach(u -> {
                    if (!u.getId().equals(id) &&
                            dto.getEmail().equals(u.getEmail())) {
                        throw new BusinessRuleException("Email duplicado");
                    }
                });
                usuario.setEmail(dto.getEmail());
            }

            if (dto.getDiscordUserId() != null) {
                usuarioRepository.findAll().forEach(u -> {
                    if (!u.getId().equals(id) &&
                            dto.getDiscordUserId().equals(u.getDiscordUserId())) {
                        throw new BusinessRuleException("DiscordUserId duplicado");
                    }
                });
                usuario.setDiscordUserId(dto.getDiscordUserId());
            }

            return usuarioRepository.save(usuario);
        }

        //delete (cascada)

        @Override
        public void eliminarUsuario(UUID id) {

            usuarioRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
            inscripcionRepository.deleteByUsuarioId(id);

        }
    }