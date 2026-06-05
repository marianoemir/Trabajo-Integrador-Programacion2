package integrado.prog2.dao;

import integrado.prog2.config.ConexionDB;
import integrado.prog2.entities.Usuario;
import integrado.prog2.enums.Rol;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public void guardar(Usuario usuario) {

        String sql = """
                INSERT INTO usuario
                (nombre, apellido, mail, celular, contrasenia, rol)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (
                Connection conn = ConexionDB.getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getMail());
            ps.setString(4, usuario.getCelular());
            ps.setString(5, usuario.getContrasenia());
            ps.setString(6, usuario.getRol().name());

            ps.executeUpdate();

        } catch (SQLException e) {
        if (e.getMessage().contains("UNIQUE constraint failed")) {
            System.out.println("Error: el mail ya está registrado.");
        } else {
            System.out.println("Error al guardar el usuario.");
            e.printStackTrace();
        }
    }
    }

    public List<Usuario> listar() {

        List<Usuario> usuarios = new ArrayList<>();

        String sql = """
                SELECT *
                FROM usuario
                WHERE eliminado = 0
                """;

        try (
                Connection conn = ConexionDB.getConexion(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Usuario usuario = new Usuario();

                usuario.setId(rs.getLong("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setMail(rs.getString("mail"));
                usuario.setCelular(rs.getString("celular"));
                usuario.setContrasenia(rs.getString("contrasenia"));
                usuario.setRol(Rol.valueOf(rs.getString("rol")));

                usuarios.add(usuario);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    public Usuario buscarPorId(Long id) {

    String sql = """
            SELECT *
            FROM usuario
            WHERE id = ?
            AND eliminado = 0
            """;

    try (
            Connection conn = ConexionDB.getConexion();
            PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setLong(1, id);

        try (ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {

                Usuario usuario = new Usuario();
                usuario.setId(rs.getLong("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setMail(rs.getString("mail"));
                usuario.setCelular(rs.getString("celular"));
                usuario.setContrasenia(rs.getString("contrasenia"));
                usuario.setRol(Rol.valueOf(rs.getString("rol")));

                return usuario;
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return null;
}

    public void actualizar(Usuario usuario) {

        String sql = """
                UPDATE usuario
                SET nombre = ?,
                    apellido = ?,
                    mail = ?,
                    celular = ?,
                    contrasenia = ?,
                    rol = ?
                WHERE id = ?
                """;

        try (
                Connection conn = ConexionDB.getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getMail());
            ps.setString(4, usuario.getCelular());
            ps.setString(5, usuario.getContrasenia());
            ps.setString(6, usuario.getRol().name());
            ps.setLong(7, usuario.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(Long id) {

        String sql = """
                UPDATE usuario
                SET eliminado = 1
                WHERE id = ?
                """;

        try (
                Connection conn = ConexionDB.getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
