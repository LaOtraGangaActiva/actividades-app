package es.fplumara.dam1.actividades.service;

import es.fplumara.dam1.actividades.model.EstadoInscripcion;
import es.fplumara.dam1.actividades.model.Taller;
import es.fplumara.dam1.actividades.model.Usuario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TallerService {
    Taller crearTaller (Taller taller);
    List<Taller> listarTalleres();
    Optional<Taller> obtenerTaller(UUID id);
    Taller actualizarTaller(UUID id, Taller taller);
    void cambiarEstadoInscripcion(UUID idTaller, EstadoInscripcion estado);
    void eliminarTaller(UUID id);
}
