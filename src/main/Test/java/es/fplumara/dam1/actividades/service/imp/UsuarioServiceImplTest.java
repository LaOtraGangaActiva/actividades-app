package es.fplumara.dam1.actividades.service.imp;

import es.fplumara.dam1.actividades.dto.UsuarioCreateDto;
import es.fplumara.dam1.actividades.model.PerfilUsuario;
import es.fplumara.dam1.actividades.model.Usuario;
import es.fplumara.dam1.actividades.repository.InscripcionRepository;
import es.fplumara.dam1.actividades.repository.UsuarioRepository;
import es.fplumara.dam1.actividades.service.impl.UsuarioServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

            UsuarioCreateDto dto = new UsuarioCreateDto(
                    "Farzia",
                    PerfilUsuario.ALUMNO,
                    "discord123",
                    "DAM1",
                    "farzia@mail.com"
            );

            when(usuarioRepository.findAll()).thenReturn(List.of());
            when(usuarioRepository.save(any())).thenAnswer(i -> i.getArgument(0));

            Usuario usuario = service.crearUsuario(dto);

            assertEquals("Farzia", usuario.getNombre());
            verify(usuarioRepository).save(any());
        }
    }

