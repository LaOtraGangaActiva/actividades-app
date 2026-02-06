package es.fplumara.dam1.actividades.config;

import es.fplumara.dam1.actividades.repository.InscripcionRepository;
import es.fplumara.dam1.actividades.repository.TallerRepository;
import es.fplumara.dam1.actividades.repository.UsuarioRepository;
import es.fplumara.dam1.actividades.service.*;
import es.fplumara.dam1.actividades.service.impl.InscripcionServiceImpl;
import es.fplumara.dam1.actividades.service.impl.TallerServiceImpl;
import es.fplumara.dam1.actividades.service.impl.UsuarioServiceImpl;

public class AppFactory {

    static UsuarioRepository usuarioRepository;
    static TallerRepository tallerRepository;
    static InscripcionRepository inscripcionRepository;

    public static AppContext createAppContext() {
        // aquí montamos repos in-memory + service.impl como ya hemos hecho
        UsuarioService usuarioService = new UsuarioServiceImpl(usuarioRepository, inscripcionRepository);
        TallerService tallerService = new TallerServiceImpl(tallerRepository, inscripcionRepository);
        InscripcionService inscripcionService = new InscripcionServiceImpl(inscripcionRepository,usuarioRepository, tallerRepository );

        return new AppContext(usuarioService, tallerService, inscripcionService);
    }
}