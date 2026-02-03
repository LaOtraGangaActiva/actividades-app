package es.fplumara.dam1.actividades.service;

import es.fplumara.dam1.actividades.dto.TallerCreateDto;
import es.fplumara.dam1.actividades.dto.TallerUpdateDto;
import es.fplumara.dam1.actividades.model.EstadoInscripcion;
import es.fplumara.dam1.actividades.model.Taller;

import java.util.List;
import java.util.UUID;

public interface TallerService {
    Taller crearTaller (TallerCreateDto dto);
    List<Taller> listarTalleres();
    Taller obtenerTaller(UUID id);
    Taller actualizarTaller(UUID idTaller, TallerUpdateDto dto);
    Taller cambiarEstadoInscripcion(UUID idTaller, EstadoInscripcion estado);
    void eliminarTaller(UUID id);
}
