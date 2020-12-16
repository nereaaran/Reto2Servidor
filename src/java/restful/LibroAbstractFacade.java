/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful;

import entidad.Libro;
import java.util.Collection;
import java.util.logging.Logger;
import javax.ws.rs.InternalServerErrorException;

/**
 * Clase que maneja las queries de la entidad "Libro".
 *
 * @author Nerea Aranguren
 */
public abstract class LibroAbstractFacade extends AbstractFacade<Libro> {

    /**
     * Atributo estático y constante que guarda los loggers de la clase.
     */
    private static final Logger LOGGER = Logger.getLogger("restful.LibroAbstractFacade");

    /**
     * Constructor que llama al constructor de la superclase (AbstractFacade).
     *
     * @param entityClass Objeto de tipo libro.
     */
    public LibroAbstractFacade(Class<Libro> entityClass) {
        super(entityClass);
    }

   
     /**
     * Método que ejecuta la query "buscarTodosLosLibros".
     *
     * @return Una coleccion de libros.
     */
    public Collection<Libro> buscarTodosLosLibros() {
        try {
            LOGGER.info("LibroAbstractFacade: Buscando todos los libros");
            return getEntityManager().createNamedQuery("buscarTodosLosLibros").getResultList();
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e);
        }
    }

    /**
     * Método que ejecuta la query "buscarLibrosPorTitulo".
     *
     * @param titulo El titulo del libro que se quiere buscar.
     * @return Una coleccion de libros.
     */
    public Collection<Libro> buscarLibrosPorTitulo(String titulo) {
        try {
            LOGGER.info("LibroAbstractFacade: Buscando libro por titulo");
            return getEntityManager().createNamedQuery("buscarLibrosPorTitulo").setParameter("titulo", titulo).getResultList();
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e);
        }
    }

    /**
     * Método que ejecuta la query "buscarLibrosPorAutor".
     *
     * @param autor El autor del libro que se quiere buscar.
     * @return Una coleccion de libros.
     */
    public Collection<Libro> buscarLibrosPorAutor(String autor) {
        try {
            LOGGER.info("LibroAbstractFacade: Buscando libro por autor");
            return getEntityManager().createNamedQuery("buscarLibrosPorAutor").setParameter("autor", autor).getResultList();
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e);
        }
    }


}
