package es.fplumara.dam1.actividades.service.impl;

import es.fplumara.dam1.actividades.dto.UsuarioCreateDto;
import es.fplumara.dam1.actividades.exception.BusinessRuleException;
import es.fplumara.dam1.actividades.exception.ObjectValidationError;
import es.fplumara.dam1.actividades.model.PerfilUsuario;
import es.fplumara.dam1.actividades.model.Usuario;
import es.fplumara.dam1.actividades.repository.InscripcionRepository;
import es.fplumara.dam1.actividades.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class UsuarioServiceImplTest {

    @Mock
    UsuarioRepository usuarioRepository;

    @Mock
    InscripcionRepository inscripcionRepository;

    @InjectMocks
    UsuarioServiceImpl service;

    @Test
    void crearUsuario_OK() {
        // Given
        UsuarioCreateDto dto = new UsuarioCreateDto(
                "Farzia",
                PerfilUsuario.ALUMNO,
                "discord123",
                "DAM1",
                "farzia@mail.com"
        );

        // Mock: findByEmail returns empty (no duplicate)
        when(usuarioRepository.findByEmail("farzia@mail.com")).thenReturn(Optional.empty());

        // Mock: findByDiscordUserId returns empty (no duplicate)
        when(usuarioRepository.findByDiscordUserId("discord123")).thenReturn(Optional.empty());

        // Mock: save returns the user with ID
        Usuario savedUser = new Usuario(
                UUID.randomUUID(),  // Auto-generated ID
                "Farzia",
                PerfilUsuario.ALUMNO,
                "discord123",
                "DAM1",
                "farzia@mail.com"
        );
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(savedUser);

        // When
        Usuario usuario = service.crearUsuario(dto);

        // Then
        assertEquals("Farzia", usuario.getNombre());
        assertEquals(PerfilUsuario.ALUMNO, usuario.getPerfil());
        assertEquals("DAM1", usuario.getCurso());

        // Verify the method was called
        verify(usuarioRepository).save(any(Usuario.class));

        // Verify duplicate checks were called
        verify(usuarioRepository).findByEmail("farzia@mail.com");
        verify(usuarioRepository).findByDiscordUserId("discord123");
    }

    @Test
    void crearUsuario_EmailDuplicado_ThrowsException() {
        // Given
        UsuarioCreateDto dto = new UsuarioCreateDto(
                "Farzia",
                PerfilUsuario.ALUMNO,
                "discord123",
                "DAM1",
                "farzia@mail.com"
        );

        // Mock: A user already has this email
        Usuario existingUser = new Usuario(
                UUID.randomUUID(),
                "Existing User",
                PerfilUsuario.ALUMNO,
                "discord456",
                "DAM2",
                "farzia@mail.com"
        );
        when(usuarioRepository.findByEmail("farzia@mail.com")).thenReturn(Optional.of(existingUser));

        // When/Then
        assertThrows(BusinessRuleException.class, () -> {
            service.crearUsuario(dto);
        });

        // Verify save was NOT called
        verify(usuarioRepository, never()).save(any());
    }

    @Test
    void crearUsuario_DiscordIdDuplicado_ThrowsException() {
        // Given
        UsuarioCreateDto dto = new UsuarioCreateDto(
                "Farzia",
                PerfilUsuario.ALUMNO,
                "discord123",  // Duplicate Discord ID
                "DAM1",
                "farzia@mail.com"
        );

        // Mock: Email check passes
        when(usuarioRepository.findByEmail("farzia@mail.com")).thenReturn(Optional.empty());

        // Mock: Discord ID already exists
        Usuario existingUser = new Usuario(
                UUID.randomUUID(),
                "Existing User",
                PerfilUsuario.ALUMNO,
                "discord123",  // Same Discord ID
                "DAM2",
                "other@mail.com"
        );
        when(usuarioRepository.findByDiscordUserId("discord123")).thenReturn(Optional.of(existingUser));

        // When/Then
        assertThrows(BusinessRuleException.class, () -> {
            service.crearUsuario(dto);
        });

        // Verify save was NOT called
        verify(usuarioRepository, never()).save(any());
    }

    @Test
    void crearUsuario_CursoSinSerAlumno_ThrowsException() {
        // Given: PROFESOR profile but has a course (invalid!)
        UsuarioCreateDto dto = new UsuarioCreateDto(
                "Professor",
                PerfilUsuario.PROFESOR,  // NOT ALUMNO
                "discord123",
                "DAM1",  // Has course but not ALUMNO
                "prof@mail.com"
        );

        // When and the
        assertThrows(BusinessRuleException.class, () -> {
            service.crearUsuario(dto);
        });

        // Verify no repository calls were made
        verify(usuarioRepository, never()).save(any());
        verify(usuarioRepository, never()).findByEmail(any());
    }

    @Test
    void crearUsuario_NombreVacio_ThrowsException() {
        // Given- Empty name
        UsuarioCreateDto dto = new UsuarioCreateDto(
                "",  // Empty name!
                PerfilUsuario.ALUMNO,
                "discord123",
                "DAM1",
                "farzia@mail.com"
        );

        assertThrows(ObjectValidationError.class, () -> {
            service.crearUsuario(dto);
        });

        // No repo interaction
        verify(usuarioRepository, never()).save(any());
    }
}