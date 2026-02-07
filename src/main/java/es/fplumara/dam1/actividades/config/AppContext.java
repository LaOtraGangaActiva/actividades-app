package es.fplumara.dam1.actividades.config;

import es.fplumara.dam1.actividades.service.*;

public class AppContext {
    public final UsuarioService usuarioService;
    public final TallerService tallerService;
    public final InscripcionService inscripcionService;

    public AppContext(UsuarioService u, TallerService t, InscripcionService i) {
        this.usuarioService = u;
        this.tallerService = t;
        this.inscripcionService = i;
    }
}
