/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package integrado.prog2.service;

import integrado.prog2.dao.ProductoDAO;
import integrado.prog2.entities.Producto;
import integrado.prog2.exception.EntityNotFoundException;
import java.util.List;


/**
 *
 * @author FACUNDO
 */
public class ProductoService {
    
    private ProductoDAO productoDAO;

    public ProductoService() {
        productoDAO = new ProductoDAO();
    }    
    
    public void guardar(Producto producto) {

        if (producto.getNombre() == null
                || producto.getNombre().isBlank()) {

            throw new IllegalArgumentException(
                    "El nombre es obligatorio");
        }

        if (producto.getPrecio() == null
                || producto.getPrecio() <= 0) {

            throw new IllegalArgumentException(
                    "El precio debe ser mayor a cero");
        }

        if (producto.getStock() == null
                || producto.getStock() < 0) {

            throw new IllegalArgumentException(
                    "El stock no puede ser negativo");
        }

        if (producto.getCategoria() == null) {

            throw new IllegalArgumentException(
                    "Debe seleccionar una categoria");
        }
        
        for (Producto p : productoDAO.listar()) {

            if (p.getNombre()
                    .equalsIgnoreCase(
                            producto.getNombre())) {

                throw new IllegalArgumentException(
                        "Ya existe un producto con ese nombre");
            }
        }        

        productoDAO.guardar(producto);
    }

    public List<Producto> listar() {
        return productoDAO.listar();
    }

    public Producto buscarPorId(Long id)
        throws EntityNotFoundException {

        Producto producto =
                productoDAO.buscarPorId(id);

        if (producto == null) {

            throw new EntityNotFoundException(
                    "Producto no encontrado");
        }

        return producto;
    }

    public void actualizar(Producto producto)
        throws EntityNotFoundException {

        buscarPorId(producto.getId());

        productoDAO.actualizar(producto);
    }

    public void eliminar(Long id)
        throws EntityNotFoundException {

        buscarPorId(id);

        productoDAO.eliminar(id);
    }    
    
}
