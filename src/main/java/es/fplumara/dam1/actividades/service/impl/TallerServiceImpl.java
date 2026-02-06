package es.fplumara.dam1.actividades.service.impl;

import es.fplumara.dam1.actividades.dto.TallerCreateDto;
import es.fplumara.dam1.actividades.dto.TallerUpdateDto;
import es.fplumara.dam1.actividades.exception.BusinessRuleException;
import es.fplumara.dam1.actividades.exception.NotFoundException;
import es.fplumara.dam1.actividades.model.EstadoInscripcion;
import es.fplumara.dam1.actividades.model.RolInscripcion;
import es.fplumara.dam1.actividades.model.Taller;
import es.fplumara.dam1.actividades.repository.InscripcionRepository;
import es.fplumara.dam1.actividades.repository.TallerRepository;
import es.fplumara.dam1.actividades.repository.memory.InMemoryInscripcionRepository;
import es.fplumara.dam1.actividades.repository.memory.InMemoryTallerRepository;
import es.fplumara.dam1.actividades.service.TallerService;
import es.fplumara.dam1.actividades.util.ValidatorUtils;

import java.util.List;
import java.util.UUID;

public class TallerServiceImpl implements TallerService {

    private final TallerRepository tallerRepository = new InMemoryTallerRepository();
    private final InscripcionRepository inscripcionRepository = new InMemoryInscripcionRepository();

    // CREATE
    @Override
    public Taller crearTaller(TallerCreateDto dto) {

        ValidatorUtils.validateEntity(dto);

        Taller taller = new Taller(
                dto.titulo(),
                dto.descripcion(),
                dto.estadoInscripcion(),
                dto.url(),
                dto.cupo(),
                dto.lugar()
        );

        return tallerRepository.save(taller);
    }

    // READ
    @Override
    public List<Taller> listarTalleres() {
        return tallerRepository.findAll();
    }

    @Override
    public Taller obtenerTaller(UUID idTaller) {
        return tallerRepository.findById(idTaller)
                .orElseThrow(() ->
                        new NotFoundException("Taller no encontrado: " + idTaller)
                );
    }

    // UPDATE
    @Override
    public Taller actualizarTaller(UUID idTaller, TallerUpdateDto dto) {

        Taller taller = obtenerTaller(idTaller);

        dto.titulo().ifPresent(titulo -> {
            if (titulo.isBlank()) {
                throw new BusinessRuleException("El título no puede estar vacío");
            }
            taller.setTitulo(titulo);
        });

        dto.descripcion().ifPresent(desc -> {
            if (desc.isBlank()) {
                throw new BusinessRuleException("La descripción no puede estar vacía");
            }
            taller.setDescripcion(desc);
        });

        dto.estadoInscripcion().ifPresent(taller::setEstadoInscripcion);
        dto.url().ifPresent(taller::setUrl);
        dto.lugar().ifPresent(taller::setLugar);

        dto.cupo().ifPresent(nuevoCupo -> {

            if (nuevoCupo < 0) {
                throw new BusinessRuleException("El cupo no puede ser negativo");
            }

            long participantesActuales =
                    inscripcionRepository.findByTallerId(idTaller).stream()
                            .filter(i -> i.getRol() == RolInscripcion.PARTICIPANTE)
                            .count();

            if (nuevoCupo < participantesActuales) {
                throw new BusinessRuleException(
                        "No se puede bajar el cupo por debajo de los participantes actuales"
                );
            }

            taller.setCupo(nuevoCupo);
        });

        return tallerRepository.save(taller);
    }

    // CHANGE STATUS
    @Override
    public Taller cambiarEstadoInscripcion(UUID idTaller, EstadoInscripcion estado) {
        Taller taller = obtenerTaller(idTaller);
        taller.setEstadoInscripcion(estado);
        return tallerRepository.save(taller);
    }

    // DELETE
    @Override
    public void eliminarTaller(UUID idTaller) {
        obtenerTaller(idTaller);
        inscripcionRepository.deleteByTallerId(idTaller);
        tallerRepository.deleteById(idTaller);
    }
}
