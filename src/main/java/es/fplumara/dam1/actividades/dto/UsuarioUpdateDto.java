package es.fplumara.dam1.actividades.dto;

import es.fplumara.dam1.actividades.model.PerfilUsuario;

public class UsuarioUpdateDto {

    private String nombre;
    private PerfilUsuario perfil;
    private String discordUserId;
    private String curso;
    private String email;

    public UsuarioUpdateDto() {}

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public PerfilUsuario getPerfil() { return perfil; }
    public void setPerfil(PerfilUsuario perfil) { this.perfil = perfil; }

    public String getDiscordUserId() { return discordUserId; }
    public void setDiscordUserId(String discordUserId) { this.discordUserId = discordUserId; }

    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}