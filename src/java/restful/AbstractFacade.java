/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful;

import java.util.Collection;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 * Clase que realiza toda la gestión que tiene que ver con el acceso a datos.
 * TODO: Hay que añadir control de exceptions y queries y aqui habra que hacer
 * las llamadas a lo de encriptacion.
 *
 * @author Cristina Milea
 */
public abstract class AbstractFacade<T> {
    
    private static final Logger LOGGER = Logger.getLogger("restful.AlumnoFacadeREST");
    
    /**
     * Atributo que define cualquiera de las entidades.
     */
    private Class<T> entityClass;

    /**
     * Constructor que guarda cualquiera de las entidades.
     *
     * @param entityClass cualquier entidad.
     */
    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * El Entity Manager que se comunica con el hibernate.
     *
     * @return el Entity Manager.
     */
    protected abstract EntityManager getEntityManager();

    
    /*public Collection<T> consultar(parametro que recibe) {
    return getEntityManager().createNamedQuery("consultarTodosBibliotecarios", parametro que se le envia).getResultList();
    logger
    throw new InternalServerError(e)
    }*/
    
    /**
     * Método que ejecuta la sentencia INSERT de SQL.
     *
     * @param entity cualquier entidad.
     */
    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    /**
     * Método que ejecuta la sentencia UPDATE de SQL.
     *
     * @param entity cualquier entidad.
     */
    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    /**
     * Método que ejecuta la sentencia DELETE de SQL.
     *
     * @param entity cualquier entidad.
     */
    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    /**
     * Método que ejecuta la sentencia SELECT de SQL.
     *
     * @param id el id por el que va a buscar en la base de datos.
     * @return lo que encuentra.
     */
    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }
}
