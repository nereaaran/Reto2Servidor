/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful;

import entidad.Bibliotecario;
import excepcion.ReadException;
import java.util.Collection;
import java.util.logging.Logger;

/**
 * Clase que realiza toda la gestión que tiene que ver con el acceso a datos de
 * la entidad "Bibliotecario".
 *
 * @author Cristina Milea
 */
public abstract class BibliotecarioAbstractFacade extends AbstractFacade<Bibliotecario> {

    /**
     * Atributo estático y constante que guarda los loggers de la clase.
     */
    private static final Logger LOGGER = Logger.getLogger("restful.BibliotecarioAbstractFacade");

    /**
     * Constructor que llama al constructor de la superclase (AbstractFacade).
     *
     * @param entityClass
     */
    public BibliotecarioAbstractFacade(Class<Bibliotecario> entityClass) {
        super(entityClass);
    }

    /**
     * Método que ejecuta la query "buscarTodosLosBibliotecarios".
     *
     * @return una colección de todos los bibliotecarios.
     * @throws excepcion.ReadException excepción al buscar un bibliotecario.
     */
    public Collection<Bibliotecario> buscarTodosLosBibliotecarios() throws ReadException {
        try {
            LOGGER.info("BibliotecarioAbstractFacade: Buscando todos los bibliotecarios");
            return getEntityManager().createNamedQuery("buscarTodosLosBibliotecarios").getResultList();
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
    }
}
