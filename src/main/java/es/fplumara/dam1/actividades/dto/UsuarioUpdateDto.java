package es.fplumara.dam1.actividades.dto;

import es.fplumara.dam1.actividades.model.PerfilUsuario;

public class UsuarioUpdateDto {
        private String nombre;
        private PerfilUsuario perfil;
        private String discordUserId;
        private String curso;
        private String email;
        public UsuarioUpdateDto(String nombre, PerfilUsuario perfil, String discordUserId, String curso, String email) {
            this.nombre = nombre;
            this.perfil=perfil;
            this.discordUserId=discordUserId;
            this.curso=curso;
            this.email=email;
        }

        public String getNombre() {
            return nombre;
        }

        public PerfilUsuario getPerfil() {
            return perfil;
        }

        public String getDiscordUserId() {
            return discordUserId;
        }

        public String getCurso() {
            return curso;
        }

        public String getEmail() {
            return email;
        }
        @Override
        public String toString() {
            return "UsuarioUpdateDto{" +
                    " nombre=" + nombre +
                    ", perfil=" + perfil +
                    ", discordUserId=" + discordUserId +
                    ", curso=" + curso +
                    ", email=" + email +
                    '}';
        }}


