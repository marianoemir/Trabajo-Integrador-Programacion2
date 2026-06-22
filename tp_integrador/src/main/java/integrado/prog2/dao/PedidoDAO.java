                       
package integrado.prog2.dao;


import integrado.prog2.entities.Pedido;
import integrado.prog2.entities.Usuario;
import integrado.prog2.enums.Estado;
import integrado.prog2.enums.FormaPago;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import integrado.prog2.config.ConexionDB;
import integrado.prog2.entities.Pedido;



public class PedidoDAO {

    private DetallePedidoDAO detalleDAO =
            new DetallePedidoDAO();

    public void guardar(Pedido pedido) {

        String sqlPedido = """
                INSERT INTO pedido
                (fecha,
                 estado,
                 total,
                 forma_pago,
                 usuario_id)
                VALUES (?, ?, ?, ?, ?)
                """;

        Connection conn = null;

        try {

            conn = ConexionDB.getConexion();

            conn.setAutoCommit(false);

            Long pedidoId;

            try (PreparedStatement ps = conn.prepareStatement(sqlPedido,Statement.RETURN_GENERATED_KEYS)) {

                ps.setDate(1,Date.valueOf(pedido.getFecha()));

                ps.setString(2,pedido.getEstado().name());

                ps.setDouble(3,pedido.getTotal());

                ps.setString(4,pedido.getFormaPago().name());

                ps.setLong(5,
                        pedido.getUsuario().getId());

                ps.executeUpdate();

                ResultSet rs =
                        ps.getGeneratedKeys();

                if (!rs.next()) {

                    throw new SQLException(
                            "No se pudo obtener el ID del pedido");
                }

                pedidoId =
                        rs.getLong(1);
            }

            for (var detalle :
                    pedido.getDetalles()) {

                detalleDAO.guardar(
                        conn,
                        detalle,
                        pedidoId);
            }
            
            // FORZAR ERROR
           /** if (true) {
            throw new SQLException(
            "Error de prueba para rollback");
            } **/
            
            conn.commit();

        } catch (SQLException e) {

            try {

                if (conn != null) {

                    conn.rollback();

                    System.out.println(
                            "Rollback ejecutado.");
                }

            } catch (SQLException ex) {

                ex.printStackTrace();
            }

            e.printStackTrace();

        } finally {

            try {

                if (conn != null) {

                    conn.setAutoCommit(true);

                    conn.close();
                }

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
    }
    
    public List<Pedido> listar() {

    List<Pedido> pedidos = new ArrayList<>();

    String sql = """
            SELECT p.*,
                   u.id as usuario_id,
                   u.nombre,
                   u.apellido
            FROM pedido p
            INNER JOIN usuario u
                ON p.usuario_id = u.id
            WHERE p.eliminado = 0
            """;

    try (
            Connection conn = ConexionDB.getConexion();
            PreparedStatement ps =
                    conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {

            Usuario usuario = new Usuario();

            usuario.setId(
                    rs.getLong("usuario_id"));

            usuario.setNombre(
                    rs.getString("nombre"));

            usuario.setApellido(
                    rs.getString("apellido"));

            Pedido pedido = new Pedido();

            pedido.setId(
                    rs.getLong("id"));

            pedido.setFecha(
                    rs.getDate("fecha")
                            .toLocalDate());

            pedido.setEstado(
                    Estado.valueOf(
                            rs.getString("estado")));

            pedido.setFormaPago(
                    FormaPago.valueOf(
                            rs.getString("forma_pago")));

            pedido.setTotal(
                    rs.getDouble("total"));

            pedido.setUsuario(usuario);

            pedidos.add(pedido);
        }

    } catch (SQLException e) {

        e.printStackTrace();
    }

    return pedidos;
}
    public Pedido buscarPorId(Long id) {

    String sql = """
            SELECT *
            FROM pedido
            WHERE id = ?
            AND eliminado = 0
            """;

    try (
            Connection conn = ConexionDB.getConexion();
            PreparedStatement ps =
                    conn.prepareStatement(sql)) {

        ps.setLong(1, id);

        try (ResultSet rs =
                     ps.executeQuery()) {

            if (rs.next()) {

                Pedido pedido =
                        new Pedido();

                pedido.setId(
                        rs.getLong("id"));

                pedido.setFecha(
                        rs.getDate("fecha")
                                .toLocalDate());

                pedido.setEstado(
                        Estado.valueOf(
                                rs.getString("estado")));

                pedido.setFormaPago(
                        FormaPago.valueOf(
                                rs.getString("forma_pago")));

                pedido.setTotal(
                        rs.getDouble("total"));

                return pedido;
            }
        }

    } catch (SQLException e) {

        e.printStackTrace();
    }

    return null;
}
    
    public void actualizar(Pedido pedido) {

    String sql = """
            UPDATE pedido
            SET estado = ?,
                forma_pago = ?
            WHERE id = ?
            """;

    try (
            Connection conn = ConexionDB.getConexion();
            PreparedStatement ps =
                    conn.prepareStatement(sql)) {

        ps.setString(
                1,
                pedido.getEstado().name());

        ps.setString(
                2,
                pedido.getFormaPago().name());

        ps.setLong(
                3,
                pedido.getId());

        ps.executeUpdate();

    } catch (SQLException e) {

        e.printStackTrace();
    }
}
    
    public void eliminar(Long id) {

    String sql = """
            UPDATE pedido
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