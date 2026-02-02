package es.fplumara.dam1.actividades.service.impl;

import es.fplumara.dam1.actividades.dto.TallerCreateOrUpdateDto;
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
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TallerServiceImplTest {

    @Mock
    private TallerRepository tallerRepository;

    private static TallerService tallerService;
    private TallerCreateOrUpdateDto tallerCreateOrUpdateDto;

    @BeforeEach
    public void setUp(){
        tallerService = new TallerServiceImpl();
    }

    @ParameterizedTest
    @CsvSource({
            "titulo,'',ABIERTO,url.com,50,lugar,El taller debe tener descripción",
            "'',descripcion,ABIERTO,url.com,50,lugar,El taller debe tener un título",
            "titulo,descripcion,ABIERTO,'',50,lugar,El taller debe tener url asociada",
            "titulo,descripcion,ABIERTO,url.com,50,'',El taller debe tener lugar"
    })
    public void ValidacionesDtoTest(String titulo, String descripcion, EstadoInscripcion estado, String url, int cupo, String lugar, String mensaje) {
        tallerCreateOrUpdateDto = new TallerCreateOrUpdateDto(titulo, descripcion, estado, url, cupo, lugar);
        EmptyFieldException ex = assertThrows(EmptyFieldException.class, () -> tallerService.crearTaller(tallerCreateOrUpdateDto));
        assertEquals(mensaje, ex.getMessage());
    }

    @Test
    public void CrearTalleresTest(){
        tallerCreateOrUpdateDto = new TallerCreateOrUpdateDto("titulo", "descripcion",EstadoInscripcion.ABIERTO,"url.com",50,"lugar");
        Taller taller = tallerService.crearTaller(tallerCreateOrUpdateDto);
        when(tallerRepository.save(taller)).thenReturn(taller);
        verify(tallerRepository).save(taller);
    }

    @Test
    public void ListarTalleresTests(){
        tallerService.listarTalleres();
        verify(tallerRepository).findAll();
    }

    @Test
    public void ObtenerTallerValidoTest(){
        String s = "3f0a8d1c-2b2e-4b7a-8e91-1d25f5b7e9fa";
        UUID id = UUID.fromString(s);
        Taller taller = new Taller(id, "", "", EstadoInscripcion.ABIERTO, "", 50, "");
        when(tallerRepository.findById(id)).thenThrow(new NotFoundException());
        NotFoundException ex = assertThrows(NotFoundException.class, () ->tallerService.obtenerTaller(id));
        verify(tallerRepository).findById(id);
    }

}
