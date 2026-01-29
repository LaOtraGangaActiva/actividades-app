package es.fplumara.dam1.actividades.dto;

import es.fplumara.dam1.actividades.model.PerfilUsuario;

public class UsuarioCreateDto {
    private String nombre;
    private PerfilUsuario perfil;
    private String discordUserId;
    private String curso;
    private String email;
    public UsuarioCreateDto(String nombre, PerfilUsuario perfil, String discordUserId, String curso, String email) {
        this.nombre = nombre;
this.perfil=perfil;
this.discordUserId=discordUserId;
this.curso=curso;
this.email=email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public PerfilUsuario getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilUsuario perfil) {
        this.perfil = perfil;
    }

}
