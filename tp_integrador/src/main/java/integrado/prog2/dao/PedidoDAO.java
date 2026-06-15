
package integrado.prog2.dao;

/**
 *
 * @author andre
 */
import integrado.prog2.config.ConexionDB;
import integrado.prog2.entities.Pedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    public void guardar(Pedido pedido) {

        String sql = """
                INSERT INTO pedido
                (fecha, estado, total, forma_pago, usuario_id)
                VALUES (?, ?, ?, ?, ?)
                """;

        // TODO insertar detalles
    }

    public List<Pedido> listar() {

        List<Pedido> pedidos = new ArrayList<>();

        // TODO

        return pedidos;
    }

    public Pedido buscarPorId(Long id) {

        // TODO

        return null;
    }

    public void actualizar(Pedido pedido) {

        String sql = """
                UPDATE pedido
                SET estado = ?,
                    forma_pago = ?
                WHERE id = ?
                """;

        // TODO
    }

    public void eliminar(Long id) {

        String sql = """
                UPDATE pedido
                SET eliminado = 1
                WHERE id = ?
                """;

        // TODO
    }
}
