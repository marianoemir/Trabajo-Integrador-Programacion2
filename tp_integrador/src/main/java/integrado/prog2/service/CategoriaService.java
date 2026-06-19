package integrado.prog2.service;

import integrado.prog2.dao.CategoriaDAO;
import integrado.prog2.entities.Categoria;
import integrado.prog2.exception.EntityNotFoundException;
import java.util.List;

/**
 *
 * @author FACUNDO
 */
public class CategoriaService {

    private CategoriaDAO categoriaDAO;

    public CategoriaService() {
        categoriaDAO = new CategoriaDAO();
    }
    
    public void guardar(Categoria categoria) {

        if (categoria.getNombre() == null
                || categoria.getNombre().isBlank()) {

            throw new IllegalArgumentException(
                    "El nombre es obligatorio");
        }
        
        for (Categoria c : categoriaDAO.listar()) {

            if (c.getNombre()
                    .equalsIgnoreCase(categoria.getNombre())) {

                throw new IllegalArgumentException(
                        "Ya existe una categoría con ese nombre");
            }
        }
        categoriaDAO.guardar(categoria);
    }
    
    public List<Categoria> listar() {
        return categoriaDAO.listar();
    }
    
    public Categoria buscarPorId(Long id)
        throws EntityNotFoundException {

        Categoria categoria =
                categoriaDAO.buscarPorId(id);

        if (categoria == null) {

            throw new EntityNotFoundException(
                    "Categoría no encontrada");
        }

        return categoria;
    }
    
    public void actualizar(Categoria categoria)
        throws EntityNotFoundException {

        buscarPorId(categoria.getId());

        categoriaDAO.actualizar(categoria);
    }
    
    public void eliminar(Long id)
        throws EntityNotFoundException {

        buscarPorId(id);

        categoriaDAO.eliminar(id);
    }

}
