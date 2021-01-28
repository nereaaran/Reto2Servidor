/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful;

import entidad.Profesor;
import excepcion.ReadException;
import java.util.Collection;
import java.util.logging.Logger;

/**
 * Clase que realiza toda la gestión que tiene que ver con el acceso a datos de
 * la entidad "Profesor".
 *
 * @author Cristina Milea
 */
public abstract class ProfesorAbstractFacade extends AbstractFacade<Profesor> {

    /**
     * Atributo estático y constante que guarda los loggers de la clase.
     */
    private static final Logger LOGGER = Logger.getLogger("restful.ProfesorAbstractFacade");

    /**
     * Constructor que llama al constructor de la superclase (AbstractFacade).
     *
     * @param entityClass cualquier entidad.
     */
    public ProfesorAbstractFacade(Class<Profesor> entityClass) {
        super(entityClass);
    }

    /**
     * Método que ejecuta la query "consultarTodosProfesores".
     *
     * @return una colección de todos los profesores.
     * @throws excepcion.ReadException excepción al buscar un profesor.
     */
    public Collection<Profesor> buscarTodosLosProfesores() throws ReadException {
        try {
            LOGGER.info("ProfesorAbstractFacade: Buscando todos los profesores");
            return getEntityManager().createNamedQuery("buscarTodosLosProfesores").getResultList();
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
    }
}
