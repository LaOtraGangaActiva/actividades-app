package es.fplumara.dam1.actividades.config;

import es.fplumara.dam1.actividades.service.*;
import es.fplumara.dam1.actividades.service.impl.InscripcionServiceImpl;
import es.fplumara.dam1.actividades.service.impl.TallerServiceImpl;
import es.fplumara.dam1.actividades.service.impl.UsuarioServiceImpl;

public class AppFactory {

    public static AppContext createAppContext() {
        // aquí montamos repos in-memory + service.impl como ya hemos hecho
        UsuarioService usuarioService = new UsuarioServiceImpl();
        TallerService tallerService = new TallerServiceImpl();
        InscripcionService inscripcionService = new InscripcionServiceImpl();

        return new AppContext(usuarioService, tallerService, inscripcionService);
    }
}