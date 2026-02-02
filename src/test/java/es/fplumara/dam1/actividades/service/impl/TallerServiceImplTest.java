package es.fplumara.dam1.actividades.service.impl;

import es.fplumara.dam1.actividades.dto.TallerCreateDto;
import es.fplumara.dam1.actividades.exception.EmptyFieldException;
import es.fplumara.dam1.actividades.exception.NotFoundException;
import es.fplumara.dam1.actividades.model.EstadoInscripcion;
import es.fplumara.dam1.actividades.model.Taller;
import es.fplumara.dam1.actividades.repository.TallerRepository;
import es.fplumara.dam1.actividades.service.TallerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TallerServiceImplTest {
    @Mock
    private TallerRepository tallerRepository;

    @InjectMocks
    private TallerServiceImpl tallerService;

    @Test
    void crearTallerTest() {
        TallerCreateDto dto = new TallerCreateDto(
                "titulo", "descripcion", EstadoInscripcion.ABIERTO,
                "url.com", 50, "lugar"
        );


    }}




