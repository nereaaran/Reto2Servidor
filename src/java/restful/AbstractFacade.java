/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.ws.rs.InternalServerErrorException;

/**
 * Clase que realiza toda la gestión que tiene que ver con el acceso a datos.
 *
 * @param <T> parámetro de una clase genérica. TODO: Hay que añadir control de
 * exceptions y queries y aqui habra que hacer las llamadas a lo de
 * encriptacion.
 *
 * @author Cristina Milea
 */
public abstract class AbstractFacade<T> {

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

    /**
     * Método que ejecuta la sentencia INSERT de SQL.
     *
     * @param entity cualquier entidad.
     */
    public void create(T entity) {
        try {
            getEntityManager().persist(entity);
        } catch (EntityExistsException | IllegalArgumentException e) {
            throw new InternalServerErrorException(e);
        }
    }

    /**
     * Método que ejecuta la sentencia UPDATE de SQL.
     *
     * @param entity cualquier entidad.
     */
    public void edit(T entity) {
        try {
            getEntityManager().merge(entity);
        } catch (IllegalArgumentException e) {
            throw new InternalServerErrorException(e);
        }
    }

    /**
     * Método que ejecuta la sentencia DELETE de SQL.
     *
     * @param entity cualquier entidad.
     */
    public void remove(T entity) {
        try {
            getEntityManager().remove(getEntityManager().merge(entity));
        } catch (IllegalArgumentException e) {
            throw new InternalServerErrorException(e);
        }
    }

    /**
     * Método que ejecuta la sentencia SELECT de SQL.
     *
     * @param id el id por el que va a buscar en la base de datos.
     * @return lo que encuentra.
     */
    public T find(Object id) {
        try {
            return getEntityManager().find(entityClass, id);
        } catch (IllegalArgumentException e) {
            throw new InternalServerErrorException(e);
        }
    }
}
