package es.fplumara.dam1.actividades.model;
import java.util.UUID;

public class Usuario implements Identificable<UUID>{
    private UUID id;
    private String nombre;
    private PerfilUsuario perfil;
    private String discordUserId;
    private String curso;
    private String email;
    public Usuario(UUID id, String nombre, PerfilUsuario perfil, String discordUserId, String curso, String email) {
        this.id = id;
        this.nombre = nombre;
        this.perfil = perfil;
        this.discordUserId = discordUserId;
        this.curso = curso;
        this.email = email;
    }

    public Usuario(String nombre, PerfilUsuario perfil, String discordUserId, String curso, String email) {
        this.nombre = nombre;
        this.perfil = perfil;
        this.discordUserId = discordUserId;
        this.curso = curso;
        this.email = email;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
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

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre=" + nombre +
                ", perfil=" + perfil +
                ", discordUserId=" + discordUserId +
                ", curso=" + curso +
                ", email=" + email +
                '}';
    }
}




