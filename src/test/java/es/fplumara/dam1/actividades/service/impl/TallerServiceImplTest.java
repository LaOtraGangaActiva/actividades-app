package es.fplumara.dam1.actividades.service.impl;

import es.fplumara.dam1.actividades.model.Taller;
import es.fplumara.dam1.actividades.repository.TallerRepository;
import es.fplumara.dam1.actividades.repository.memory.InMemoryTallerRepository;
import es.fplumara.dam1.actividades.service.TallerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TallerServiceImplTest {

    @Mock
    private TallerRepository tallerRepository;

    private TallerService tallerService;

    @BeforeEach
    public void setUp(){
        tallerRepository = new InMemoryTallerRepository();
        tallerService = new TallerServiceImpl();
    }
    public void crearTallerTest(){

    }
}
