package es.fplumara.dam1.actividades.service.impl;

import es.fplumara.dam1.actividades.dto.TallerCreateOrUpdateDto;
import es.fplumara.dam1.actividades.exception.BusinessRuleException;
import es.fplumara.dam1.actividades.exception.EmptyFieldException;
import es.fplumara.dam1.actividades.exception.NotFoundException;
import es.fplumara.dam1.actividades.model.EstadoInscripcion;
import es.fplumara.dam1.actividades.model.Taller;
import es.fplumara.dam1.actividades.repository.TallerRepository;
import es.fplumara.dam1.actividades.repository.memory.InMemoryTallerRepository;
import es.fplumara.dam1.actividades.service.TallerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

public class TallerServiceImpl implements TallerService  {
    private static final Logger LOG = LoggerFactory.getLogger(TallerServiceImpl.class);
    TallerRepository tallerRepository = new InMemoryTallerRepository();

    @Override
    public Taller crearTaller(TallerCreateOrUpdateDto dto) {
        if(dto.titulo().isEmpty() || dto.titulo().isBlank()){
            throw new EmptyFieldException("El taller debe tener un título");
        }
        if(dto.descripcion().isEmpty() || dto.descripcion().isBlank()){
            throw new EmptyFieldException("El taller debe tener descripción");
        }
        if(!(dto.estadoInscripcion().equals(EstadoInscripcion.CERRADO) || dto.estadoInscripcion().equals(EstadoInscripcion.ABIERTO))){
            throw new BusinessRuleException("El estado de la inscripción no se ha especificado correctamente");
        }
        if(dto.url().isEmpty() || dto.url().isBlank()){
            throw new EmptyFieldException("El taller debe tener url asociada");
        }
        if(dto.cupo() <= 0){
            throw new BusinessRuleException("El cupo del taller debe ser mayor a 0");
        }
        if(dto.lugar().isEmpty() || dto.lugar().isBlank()){
            throw new EmptyFieldException("El taller debe tener lugar");
        }
        return tallerRepository.save(new Taller(dto.titulo(), dto.descripcion(), dto.estadoInscripcion(), dto.url(), dto.cupo(), dto.lugar()));
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
    public Taller actualizarTaller(UUID idTaller, TallerCreateOrUpdateDto dto) {
        Taller taller = tallerRepository.findById(idTaller).orElseThrow(NotFoundException::new);
        if(dto.titulo().isEmpty() || dto.titulo().isBlank()){
            throw new EmptyFieldException("El taller debe tener un título");
        }
        if(dto.descripcion().isEmpty() || dto.descripcion().isBlank()){
            throw new EmptyFieldException("El taller debe tener descripción");
        }
        if(!(dto.estadoInscripcion().equals(EstadoInscripcion.CERRADO) || dto.estadoInscripcion().equals(EstadoInscripcion.ABIERTO))){
            throw new BusinessRuleException("El estado de la inscripción no se ha especificado correctamente");
        }
        if(dto.url().isEmpty() || dto.url().isBlank()){
            throw new EmptyFieldException("El taller debe tener url asociada");
        }
        if(dto.cupo() <= 0){
            throw new BusinessRuleException("El cupo del taller debe ser mayor a 0");
        }
        if(dto.lugar().isEmpty() || dto.lugar().isBlank()){
            throw new EmptyFieldException("El taller debe tener lugar");
        }

        taller.setTitulo(dto.titulo());
//        taller.setDescripcion(dto.descripcion());
//        taller.setTitulo(dto.titulo());
//        taller.setTitulo(dto.titulo());
//        taller.setTitulo(dto.titulo());
//        taller.setTitulo(dto.titulo());
        return null;
    }

    @Override
    public void cambiarEstadoInscripcion(UUID idTaller, EstadoInscripcion estado) {
        tallerRepository.findById(idTaller).get().setEstadoInscripcion(estado);
    }

    @Override
    public void eliminarTaller(UUID id) {
        tallerRepository.deleteById(id);
    }
}
