package integrado.prog2.service;

import integrado.prog2.dao.UsuarioDAO;
import integrado.prog2.entities.Usuario;
import integrado.prog2.exception.EntityNotFoundException;

import java.util.List;

public class UsuarioService {

    private UsuarioDAO usuarioDAO;

    public UsuarioService() {
        usuarioDAO = new UsuarioDAO();
    }

    public void guardar(Usuario usuario) {

        if (usuario.getNombre() == null
                || usuario.getNombre().isBlank()) {

            throw new IllegalArgumentException(
                    "El nombre es obligatorio");
        }

        if (usuario.getNombre().length() < 2) {

            throw new IllegalArgumentException(
                    "El nombre debe tener al menos 2 caracteres");
        }

        if (!usuario.getNombre()
                .matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {

            throw new IllegalArgumentException(
                    "El nombre solo puede contener letras");
        }

        if (usuario.getApellido() == null
                || usuario.getApellido().isBlank()) {

            throw new IllegalArgumentException(
                    "El apellido es obligatorio");
        }

        if (usuario.getApellido().length() < 2) {

            throw new IllegalArgumentException(
                    "El apellido debe tener al menos 2 caracteres");
        }

        if (usuario.getRol() == null) {

            throw new IllegalArgumentException(
                    "Debe seleccionar un rol");
        }

        if (usuario.getMail() == null
                || usuario.getMail().isBlank()) {

            throw new IllegalArgumentException(
                    "El mail es obligatorio");
        }

        if (!usuario.getMail()
                .matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {

            throw new IllegalArgumentException(
                    "Formato de mail inválido");
        }

        for (Usuario u : usuarioDAO.listar()) {

            if (u.getMail()
                    .equalsIgnoreCase(usuario.getMail())) {

                throw new IllegalArgumentException(
                        "Ya existe un usuario con ese mail");
            }
        }

        if (usuario.getContrasenia() == null
                || usuario.getContrasenia().isBlank()) {

            throw new IllegalArgumentException(
                    "La contraseña es obligatoria");
        }

        if (usuario.getContrasenia().length() < 4) {

            throw new IllegalArgumentException(
                    "La contraseña debe tener al menos 4 caracteres");
        }

        usuarioDAO.guardar(usuario);
    }

    public List<Usuario> listar() {
        return usuarioDAO.listar();
    }

    public Usuario buscarPorId(Long id)
            throws EntityNotFoundException {

        Usuario usuario = usuarioDAO.buscarPorId(id);

        if (usuario == null) {
            throw new EntityNotFoundException(
                    "Usuario no encontrado");
        }

        return usuario;
    }

    public void actualizar(Usuario usuario)
            throws EntityNotFoundException {

        buscarPorId(usuario.getId());

        usuarioDAO.actualizar(usuario);
    }

    public void eliminar(Long id)
            throws EntityNotFoundException {

        buscarPorId(id);

        usuarioDAO.eliminar(id);
    }
}
