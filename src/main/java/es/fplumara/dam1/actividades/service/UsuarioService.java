package es.fplumara.dam1.actividades.service;

import es.fplumara.dam1.actividades.model.Usuario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioService {
Usuario crearUsuario(Usuario usuario);
List<Usuario> listarUsuario();
Optional<Usuario> obtenerUsuario(UUID id);
Usuario actualizarUsuario(UUID id, Usuario usuario);
 //   Borrar con cascada
void eliminarUsuario(UUID id);
}