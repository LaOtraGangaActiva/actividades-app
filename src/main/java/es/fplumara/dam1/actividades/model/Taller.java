package es.fplumara.dam1.actividades.model;

import java.net.URL;
import java.util.UUID;

public class Taller {
    private UUID id;
    private String titulo;
    private String descripcion;
    private EstadoInscripcion estadoInscripcion;
    private String url; //He investigado y no sé si podríamos poner esto o String
    private int cupo;
    private String lugar;


    // constructor with id for existing talleres
    public Taller(UUID id, String titulo, String descripcion, EstadoInscripcion estadoInscripcion, String url, int cupo, String lugar) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estadoInscripcion = estadoInscripcion;
        this.url = url;
        this.cupo = cupo;
        this.lugar = lugar;
    }
    // constructor with id for creating new talleres
    public Taller(String titulo, String descripcion, EstadoInscripcion estadoInscripcion, String url, int cupo, String lugar) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estadoInscripcion = estadoInscripcion;
        this.url = url;
        this.cupo = cupo;
        this.lugar = lugar;
    }

    public Taller(String titulo, String descripcion, EstadoInscripcion estadoInscripcion, String url, int cupo, String lugar) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estadoInscripcion = estadoInscripcion;
        this.url = url;
        this.cupo = cupo;
        this.lugar = lugar;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EstadoInscripcion getEstadoInscripcion() {
        return estadoInscripcion;
    }

    public void setEstadoInscripcion(EstadoInscripcion estadoInscripcion) {
        this.estadoInscripcion = estadoInscripcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCupo() {
        return cupo;
    }

    public void setCupo(int cupo) {
        this.cupo = cupo;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    @Override
    public String toString() {
        return "Taller{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estadoInscripcion=" + estadoInscripcion +
                ", url=" + url +
                ", cupo=" + cupo +
                ", lugar='" + lugar + '\'' +
                '}';
    }
}
