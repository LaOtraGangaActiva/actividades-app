package es.fplumara.dam1.actividades.model;

import java.util.UUID;

public class Usuario {
    private UUID id;
    private String nombre;
    private PerfilUsuario perfil;
    private String discordUserId;
    private String curso;
    private String email;
// do we need to put an empty constructor o not? also for taller?
    public Usuario(String nombre, PerfilUsuario perfil, String discordUserId, String curso, String email){
        this.nombre= nombre;
        this.perfil=perfil;
        this.discordUserId=discordUserId;
        this.curso=curso;
        this.email=email;
    }

    public void setId(UUID id){
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public PerfilUsuario getPerfil(){
        return perfil;
    }

    public void setPerfil(PerfilUsuario perfil) {
        this.perfil = perfil;
    }

    public String getDiscordUserId() {
        return discordUserId;
    }

    public void setDiscordUserId(String discordUserId) {
        this.discordUserId = discordUserId;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getCurso() {
        return curso;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString(){
        return "Usuario{"+
            "id=" + id +
            ", nombre=" + nombre +
            ", perfil=" + perfil + ", discordUserId=" + discordUserId + ", curso=" + curso + ", email=" + email;
        }
    }





