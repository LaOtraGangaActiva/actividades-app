package es.fplumara.dam1.actividades.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Inscripcion {
    private UUID idTaller;
    private UUID idUsuario;
    private RolInscripcion rol;
    private LocalDateTime fechaInscripcion;
    private static final String FORMAT_DATE_TIME = "dd-MM-yyyy / HH:mm:ss";

    public Inscripcion(UUID idTaller, UUID idUsuario, RolInscripcion rol) {
        this.idTaller = idTaller;
        this.idUsuario = idUsuario;
        this.rol = rol;
        this.fechaInscripcion = LocalDateTime.now();
    }

    public String getId() {
        return idTaller.toString() + "/" + idUsuario.toString();
    }

    public UUID getIdTaller() {
        return idTaller;
    }

    public void setIdTaller(UUID idTaller) {
        this.idTaller = idTaller;
    }

    public UUID getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UUID idUsuario) {
        this.idUsuario = idUsuario;
    }

    public RolInscripcion getRol() {
        return rol;
    }

    public void setRol(RolInscripcion rol) {
        this.rol = rol;
    }

    public LocalDateTime getFechaInscripcion() {
        return fechaInscripcion;
    }

    @Override
    public String toString() {
        return "Inscripcion{" +
                ", idTaller=" + idTaller +
                ", idUsuario=" + idUsuario +
                ", rol=" + rol +
                ", fechaInscripcion=" + fechaInscripcion.format(DateTimeFormatter.ofPattern(FORMAT_DATE_TIME)) +
                '}';
    }
}
