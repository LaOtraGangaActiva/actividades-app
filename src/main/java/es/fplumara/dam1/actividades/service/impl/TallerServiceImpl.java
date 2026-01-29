package es.fplumara.dam1.actividades.service.impl;

import es.fplumara.dam1.actividades.dto.TallerCreateDto;
import es.fplumara.dam1.actividades.exception.NotFoundException;
import es.fplumara.dam1.actividades.model.EstadoInscripcion;
import es.fplumara.dam1.actividades.model.Taller;
import es.fplumara.dam1.actividades.repository.memory.InMemoryTallerRepository;
import es.fplumara.dam1.actividades.service.TallerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

public class TallerServiceImpl implements TallerService  {
    private static final Logger LOG = LoggerFactory.getLogger(TallerServiceImpl.class);
    InMemoryTallerRepository tallerRepository = new InMemoryTallerRepository();

    @Override
    public Taller crearTaller(TallerCreateDto dto) {
        return null;
    }

    @Override
    public List<Taller> listarTalleres() {
        return tallerRepository.findAll();
    }

    @Override
    public Taller obtenerTaller(UUID id) {
        return tallerRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public Taller actualizarTaller(UUID id, Taller taller) {
        return null;
    }

    @Override
    public void cambiarEstadoInscripcion(UUID idTaller, EstadoInscripcion estado) {

    }

    @Override
    public void eliminarTaller(UUID id) {
        tallerRepository.deleteById(id);
    }
}
