package es.fplumara.dam1.actividades.service;

import es.fplumara.dam1.actividades.dto.TallerCreateOrUpdateDto;
import es.fplumara.dam1.actividades.model.EstadoInscripcion;
import es.fplumara.dam1.actividades.model.Taller;

import java.util.List;
import java.util.UUID;

public interface TallerService {
    Taller crearTaller (TallerCreateOrUpdateDto dto);
    List<Taller> listarTalleres();
    Taller obtenerTaller(UUID id);
    Taller actualizarTaller(UUID idTaller,TallerCreateOrUpdateDto dto);
    void cambiarEstadoInscripcion(UUID idTaller, EstadoInscripcion estado);
    void eliminarTaller(UUID id);
}
