/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package integrado.prog2.dao;


import integrado.prog2.config.ConexionDB;
import integrado.prog2.entities.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author FACUNDO
 */
public class CategoriaDAO {
    
        public void guardar(Categoria categoria) {

        String sql = """
                INSERT INTO categoria
                (nombre, descripcion)
                VALUES (?, ?)
                """;

        try (
                
            Connection conn = ConexionDB.getConexion();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, categoria.getNombre());
            ps.setString(2, categoria.getDescripcion());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        
    public List<Categoria> listar() {

    List<Categoria> categorias = new ArrayList<>();

    String sql = """
            SELECT *
            FROM categoria
            WHERE eliminado = 0
            """;

    try (
            Connection conn = ConexionDB.getConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {

            Categoria categoria = new Categoria();

            categoria.setId(rs.getLong("id"));
            categoria.setNombre(rs.getString("nombre"));
            categoria.setDescripcion(rs.getString("descripcion"));

            categorias.add(categoria);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return categorias;
}
    
    public Categoria buscarPorId(Long id) {

        String sql = """
                SELECT *
                FROM categoria
                WHERE id = ?
                AND eliminado = 0
                """;

        try (
                Connection conn = ConexionDB.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Categoria categoria = new Categoria();

                    categoria.setId(rs.getLong("id"));
                    categoria.setNombre(rs.getString("nombre"));
                    categoria.setDescripcion(rs.getString("descripcion"));

                    return categoria;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public void actualizar(Categoria categoria) {

        String sql = """
                UPDATE categoria
                SET nombre = ?,
                    descripcion = ?
                WHERE id = ?
                """;

        try (
                Connection conn = ConexionDB.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, categoria.getNombre());
            ps.setString(2, categoria.getDescripcion());
            ps.setLong(3, categoria.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public void eliminar(Long id) {

        String sql = """
                UPDATE categoria
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
