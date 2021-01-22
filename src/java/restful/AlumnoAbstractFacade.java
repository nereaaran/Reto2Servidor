/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful;

import entidad.Alumno;
import excepcion.ReadException;
import java.util.Collection;
import java.util.logging.Logger;

/**
 * Clase que realiza toda la gestión que tiene que ver con el acceso a datos de
 * la entidad "Alumno".
 *
 * @author Cristina Milea
 */
public abstract class AlumnoAbstractFacade extends AbstractFacade<Alumno> {

    /**
     * Atributo estático y constante que guarda los loggers de la clase.
     */
    private static final Logger LOGGER = Logger.getLogger("restful.AlumnoAbstractFacade");

    /**
     * Constructor que llama al constructor de la superclase (AbstractFacade).
     *
     * @param entityClass
     */
    public AlumnoAbstractFacade(Class<Alumno> entityClass) {
        super(entityClass);
    }

    /**
     * Método que ejecuta la query "consultarAlumnoPorNombre".
     *
     * @param fullName el nombre completo que se quiere buscar.
     * @return una colección de alumnos.
     * @throws excepcion.ReadException excepción al buscar un alumno.
     */
    public Collection<Alumno> consultarAlumnoPorNombre(String fullName) throws ReadException {
        try {
            LOGGER.info("AlumnoAbstractFacade: Buscando alumno por nombre");
            return getEntityManager().createNamedQuery("consultarAlumnoPorNombre").setParameter("fullName", fullName).getResultList();
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new ReadException(e.getMessage());
        }
    }

    /**
     * Método que ejecuta la query "consultarTodosAlumnos".
     *
     * @return una colección de todos los alumnos.
     * @throws excepcion.ReadException excepción al buscar un alumno.
     */
    public Collection<Alumno> consultarTodosAlumnos() throws ReadException {
        try {
            LOGGER.info("AlumnoAbstractFacade: Buscando todos los alumnos");
            return getEntityManager().createNamedQuery("consultarTodosAlumnos").getResultList();
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
    }

}
