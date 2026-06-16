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
        if (usuario.getNombre() == null || usuario.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (usuario.getMail() == null || usuario.getMail().isBlank()) {
            throw new IllegalArgumentException("El mail es obligatorio");
        }
        if (usuario.getContrasenia() == null || usuario.getContrasenia().isBlank()) {
            throw new IllegalArgumentException("La contraseña es obligatoria");
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