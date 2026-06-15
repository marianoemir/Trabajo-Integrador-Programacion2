/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package integrado.prog2.dao;
import integrado.prog2.config.ConexionDB;
import integrado.prog2.entities.Categoria;
import integrado.prog2.entities.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author FACUNDO
 */
public class ProductoDAO {
    
    public void guardar(Producto producto) {

        String sql = """
                INSERT INTO producto
                (nombre, precio, descripcion,
                 stock, imagen, disponible,
                 categoria_id)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try (
                Connection conn = ConexionDB.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, producto.getNombre());
            ps.setDouble(2, producto.getPrecio());
            ps.setString(3, producto.getDescripcion());
            ps.setInt(4, producto.getStock());
            ps.setString(5, producto.getImagen());

            ps.setInt(
                    6,
                    producto.getDisponible() ? 1 : 0
            );

            ps.setLong(
                    7,
                    producto.getCategoria().getId()
            );

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Producto> listar() {

        List<Producto> productos = new ArrayList<>();

        String sql = """
                SELECT p.*,
                       c.id AS categoria_id,
                       c.nombre AS categoria_nombre,
                       c.descripcion AS categoria_descripcion
                FROM producto p
                INNER JOIN categoria c
                    ON p.categoria_id = c.id
                WHERE p.eliminado = 0
                """;

        try (
                Connection conn = ConexionDB.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Categoria categoria = new Categoria();

                categoria.setId(rs.getLong("categoria_id"));
                categoria.setNombre(rs.getString("categoria_nombre"));
                categoria.setDescripcion(
                        rs.getString("categoria_descripcion"));

                Producto producto = new Producto();

                producto.setId(rs.getLong("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setStock(rs.getInt("stock"));
                producto.setImagen(rs.getString("imagen"));
                producto.setDisponible(
                        rs.getInt("disponible") == 1);

                producto.setCategoria(categoria);

                productos.add(producto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productos;
    }
    
    public Producto buscarPorId(Long id) {

        String sql = """
                SELECT p.*,
                       c.id AS categoria_id,
                       c.nombre AS categoria_nombre,
                       c.descripcion AS categoria_descripcion
                FROM producto p
                INNER JOIN categoria c
                    ON p.categoria_id = c.id
                WHERE p.id = ?
                AND p.eliminado = 0
                """;

        try (
                Connection conn = ConexionDB.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Categoria categoria = new Categoria();

                    categoria.setId(rs.getLong("categoria_id"));
                    categoria.setNombre(rs.getString("categoria_nombre"));
                    categoria.setDescripcion(
                            rs.getString("categoria_descripcion"));

                    Producto producto = new Producto();

                    producto.setId(rs.getLong("id"));
                    producto.setNombre(rs.getString("nombre"));
                    producto.setPrecio(rs.getDouble("precio"));
                    producto.setDescripcion(rs.getString("descripcion"));
                    producto.setStock(rs.getInt("stock"));
                    producto.setImagen(rs.getString("imagen"));
                    producto.setDisponible(
                            rs.getInt("disponible") == 1);

                    producto.setCategoria(categoria);

                    return producto;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public void actualizar(Producto producto) {

        String sql = """
                UPDATE producto
                SET nombre = ?,
                    precio = ?,
                    descripcion = ?,
                    stock = ?,
                    imagen = ?,
                    disponible = ?,
                    categoria_id = ?
                WHERE id = ?
                """;
        try (
                Connection conn = ConexionDB.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {        
                ps.setString(1, producto.getNombre());
                ps.setDouble(2, producto.getPrecio());
                ps.setString(3, producto.getDescripcion());
                ps.setInt(4, producto.getStock());
                ps.setString(5, producto.getImagen());

                ps.setInt(
                        6,
                        producto.getDisponible() ? 1 : 0);

                ps.setLong(
                        7,
                        producto.getCategoria().getId());

                ps.setLong(
                        8,
                        producto.getId());
                
                
                ps.executeUpdate();
                
                
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(Long id) {

        String sql = """
                UPDATE producto
                SET eliminado = 1
                WHERE id = ?
                """;

        try (
                Connection conn = ConexionDB.getConexion();
                PreparedStatement ps =
                        conn.prepareStatement(sql)) {

            ps.setLong(1, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }    
    
    
}