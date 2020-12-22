/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful;

import excepcion.*;
import javax.persistence.EntityManager;

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
     * @throws excepcion.CreateException excepción al crear una entidad.
     */
    public void create(T entity) throws CreateException {
        try {
            getEntityManager().persist(entity);
        } catch (Exception e) {
            throw new CreateException(e.getMessage());
        }
    }

    /**
     * Método que ejecuta la sentencia UPDATE de SQL.
     *
     * @param entity cualquier entidad.
     * @throws excepcion.UpdateException excepción al editar una entidad.
     */
    public void edit(T entity) throws UpdateException {
        try {
            getEntityManager().merge(entity);
        } catch (Exception e) {
            throw new UpdateException(e.getMessage());
        }
    }

    /**
     * Método que ejecuta la sentencia DELETE de SQL.
     *
     * @param entity cualquier entidad.
     * @throws excepcion.DeleteException excepción al borrar una entidad.
     */
    public void remove(T entity) throws DeleteException {
        try {
            getEntityManager().remove(getEntityManager().merge(entity));
        } catch (Exception e) {
            throw new DeleteException(e.getMessage());
        }
    }

    /**
     * odo * Método que ejecuta la sentencia SELECT de SQL.
     *
     * @param id el id por el que va a buscar en la base de datos.
     * @return lo que encuentra.
     * @throws excepcion.ReadException excepción al buscar una entidad.
     */
    public T find(Object id) throws ReadException {
        try {
            return getEntityManager().find(entityClass, id);
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
    }

}
