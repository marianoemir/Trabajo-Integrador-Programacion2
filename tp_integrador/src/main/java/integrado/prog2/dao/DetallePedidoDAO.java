
package integrado.prog2.dao;


import integrado.prog2.entities.DetallePedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DetallePedidoDAO {

    public void guardar(
            Connection conn,
            DetallePedido detalle,
            Long pedidoId)
            throws SQLException {

        String sql = """
                INSERT INTO detalle_pedido
                (cantidad,
                 subtotal,
                 pedido_id,
                 producto_id)
                VALUES (?, ?, ?, ?)
                """;

        try (PreparedStatement ps =
                     conn.prepareStatement(sql)) {

            ps.setInt(
                    1,
                    detalle.getCantidad());

            ps.setDouble(
                    2,
                    detalle.getSubtotal());

            ps.setLong(
                    3,
                    pedidoId);

            ps.setLong(
                    4,
                    detalle.getProducto().getId());

            ps.executeUpdate();
        }
    }
}
