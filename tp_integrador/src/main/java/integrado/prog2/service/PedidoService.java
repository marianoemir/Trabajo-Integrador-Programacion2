
package integrado.prog2.service;

/**
 *
 * @author andre
 */
import integrado.prog2.dao.PedidoDAO;
import integrado.prog2.entities.Pedido;
import integrado.prog2.exception.EntityNotFoundException;

import java.util.List;

public class PedidoService {

    private PedidoDAO pedidoDAO;

    public PedidoService() {
        pedidoDAO = new PedidoDAO();
    }

    public void guardar(Pedido pedido) {

        if (pedido.getUsuario() == null) {
            throw new IllegalArgumentException(
                    "Debe seleccionar un usuario");
        }

        if (pedido.getDetalles().isEmpty()) {
            throw new IllegalArgumentException(
                    "Debe agregar al menos un detalle");
        }

        pedido.setTotal(
                pedido.calcularTotal());

        pedidoDAO.guardar(pedido);
    }

    public List<Pedido> listar() {
        return pedidoDAO.listar();
    }

    public Pedido buscarPorId(Long id)
            throws EntityNotFoundException {

        Pedido pedido =
                pedidoDAO.buscarPorId(id);

        if (pedido == null) {

            throw new EntityNotFoundException(
                    "Pedido no encontrado");
        }

        return pedido;
    }

    public void actualizar(Pedido pedido)
            throws EntityNotFoundException {

        buscarPorId(pedido.getId());

        pedidoDAO.actualizar(pedido);
    }

    public void eliminar(Long id)
            throws EntityNotFoundException {

        buscarPorId(id);

        pedidoDAO.eliminar(id);
    }
}