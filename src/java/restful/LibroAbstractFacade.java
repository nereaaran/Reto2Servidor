/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful;

import entidad.Libro;
import excepcion.ReadException;
import java.util.Collection;
import java.util.logging.Logger;

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
     * @throws excepcion.ReadException Excepción al buscar un libro.
     */
    public Collection<Libro> buscarTodosLosLibros() throws ReadException {
        try {
            LOGGER.info("LibroAbstractFacade: Buscando todos los libros");
            return getEntityManager().createNamedQuery("buscarTodosLosLibros").getResultList();
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new ReadException(e.getMessage());
        }
    }

    /**
     * Método que ejecuta la query "buscarLibrosPorTitulo".
     *
     * @param titulo El titulo del libro que se quiere buscar.
     * @return Una coleccion de libros.
     * @throws excepcion.ReadException Excepción al buscar un libro.
     */
    public Collection<Libro> buscarLibrosPorTitulo(String titulo) throws ReadException {
        try {
            LOGGER.info("LibroAbstractFacade: Buscando libro por titulo");
            return getEntityManager().createNamedQuery("buscarLibrosPorTitulo").setParameter("titulo", titulo).getResultList();
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new ReadException(e.getMessage());
        }
    }

    /**
     * Método que ejecuta la query "buscarLibrosPorAutor".
     *
     * @param autor El autor del libro que se quiere buscar.
     * @return Una coleccion de libros.
     * @throws excepcion.ReadException Excepción al buscar un libro.
     */
    public Collection<Libro> buscarLibrosPorAutor(String autor) throws ReadException {
        try {
            LOGGER.info("LibroAbstractFacade: Buscando libro por autor");
            return getEntityManager().createNamedQuery("buscarLibrosPorAutor").setParameter("autor", autor).getResultList();
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new ReadException(e.getMessage());
        }
    }

    /**
     * Método que ejecuta la query "buscarLibrosPorIsbn".
     *
     * @param isbn El isbn del libro que se quiere buscar.
     * @return Una coleccion de libros.
     * @throws excepcion.ReadException Excepción al buscar un libro.
     */
    public Collection<Libro> buscarLibrosPorIsbn(Long isbn) throws ReadException {
        try {
            LOGGER.info("LibroAbstractFacade: Buscando libros por isbn");
            return getEntityManager().createNamedQuery("buscarLibrosPorIsbn").setParameter("isbn", isbn).getResultList();
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new ReadException(e.getMessage());
        }
    }

}
