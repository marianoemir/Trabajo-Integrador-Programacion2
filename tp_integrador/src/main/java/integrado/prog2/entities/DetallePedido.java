package integrado.prog2.entities;


public class DetallePedido extends Base {

    private int cantidad;

    private double subtotal;

    private Pedido pedido;

    private Producto producto;

    public DetallePedido() {
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {

        return "DetallePedido{"
                + "producto="
                + producto.getNombre()
                + ", cantidad="
                + cantidad
                + ", subtotal="
                + subtotal
                + '}';
    }
}