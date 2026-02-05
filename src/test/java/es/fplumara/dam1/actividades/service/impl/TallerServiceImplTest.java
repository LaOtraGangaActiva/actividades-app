package es.fplumara.dam1.actividades.service.impl;

import es.fplumara.dam1.actividades.dto.TallerUpdateDto;
import es.fplumara.dam1.actividades.exception.BusinessRuleException;
import es.fplumara.dam1.actividades.model.EstadoInscripcion;
import es.fplumara.dam1.actividades.model.Taller;
import es.fplumara.dam1.actividades.repository.InscripcionRepository;
import es.fplumara.dam1.actividades.repository.TallerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TallerServiceImplTest {

    @Mock
    private TallerRepository tallerRepository;

    @Mock
    private InscripcionRepository inscripcionRepository;

    @InjectMocks
    private TallerServiceImpl tallerService;

    private UUID tallerId;
    private Taller tallerExistente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        tallerId = UUID.randomUUID();

        tallerExistente = new Taller(
                "Java",
                "Taller de Java",
                EstadoInscripcion.ABIERTO,
                "https://example.com",
                10,
                "Madrid"
        );
        tallerExistente.setId(tallerId);
    }

    @Test
    void actualizarTaller_cupoValido_ok() {
        // GIVEN
        when(tallerRepository.findById(tallerId))
                .thenReturn(Optional.of(tallerExistente));

        TallerUpdateDto dto = new TallerUpdateDto(
                Optional.empty(),   // nombre
                Optional.empty(),   // descripcion
                Optional.empty(),   // estadoInscripcion
                Optional.empty(),   // enlace
                Optional.of(8),  // nuevo cupo
                Optional.empty()    // lugar
        );

        when(tallerRepository.save(any(Taller.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // WHEN
        Taller actualizado = tallerService.actualizarTaller(tallerId, dto);

        // THEN
        assertEquals(8, actualizado.getCupo());
        verify(tallerRepository).findById(tallerId);
        verify(tallerRepository).save(tallerExistente);
        verifyNoMoreInteractions(tallerRepository);
    }

    @Test
    void actualizarTaller_cupoMenorQueParticipant_lanzaExcepcion() {
        // GIVEN
        when(tallerRepository.findById(tallerId))
                .thenReturn(Optional.of(tallerExistente));

        TallerUpdateDto dto = new TallerUpdateDto(
                Optional.of(""),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.of(4), // menor que participantes actuales
                Optional.empty()
        );

        // WHEN + THEN
        assertThrows(
                BusinessRuleException.class,
                () -> tallerService.actualizarTaller(tallerId, dto)
        );

        verify(tallerRepository).findById(tallerId);
        verify(tallerRepository, never()).save(any());
    }
}


