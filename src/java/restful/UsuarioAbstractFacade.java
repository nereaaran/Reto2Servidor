/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful;

import entidad.Usuario;
import java.util.Collection;
import java.util.logging.Logger;
import javax.ws.rs.InternalServerErrorException;

/**
 * Clase que realiza toda la gestión que tiene que ver con el acceso a datos de
 * la entidad "Usuario".
 *
 * @author Cristina Milea
 */
public abstract class UsuarioAbstractFacade extends AbstractFacade<Usuario> {

    /**
     * Atributo estático y constante que guarda los loggers de la clase.
     */
    private static final Logger LOGGER = Logger.getLogger("restful.UsuarioAbstractFacade");

    /**
     * Constructor que llama al constructor de la superclase (AbstractFacade).
     *
     * @param entityClass
     */
    public UsuarioAbstractFacade(Class<Usuario> entityClass) {
        super(entityClass);
    }

    /**
     * Método que ejecuta la query "buscarUsuarioPorLogin".
     *
     * @param login el login que se quiere buscar.
     * @return una colección de usuarios.
     */
    public Collection<Usuario> buscarUsuarioPorLogin(String login) {
        try {
            LOGGER.info("UsuarioAbstractFacade: Buscando usuario por login");
            return getEntityManager().createNamedQuery("buscarUsuarioPorLogin").setParameter("login", login).getResultList();
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e);
        }
    }

    /**
     * Método que ejecuta la query "buscarUsuarioPorEmail".
     *
     * @param email el email que se quiere buscar.
     * @return una colección de usuarios.
     */
    public Collection<Usuario> buscarUsuarioPorEmail(String email) {
        try {
            LOGGER.info("UsuarioAbstractFacade: Buscando usuario por email");
            return getEntityManager().createNamedQuery("buscarUsuarioPorEmail").setParameter("email", email).getResultList();
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e);
        }
    }

    /**
     * Método que ejecuta la query "buscarLoginYContrasenia".
     *
     * @param login el login que se quiere buscar.
     * @param password la contraseña que se quiere buscar.
     * @return una colección de usuarios.
     */
    public Collection<Usuario> buscarLoginYContrasenia(String login, String password) { //descifrado de pass
        try {
            LOGGER.info("UsuarioAbstractFacade: Buscando usuario por login y contraseña");
            return getEntityManager().createNamedQuery("buscarLoginYContrasenia")
                    .setParameter("login", login)
                    .setParameter("password", password)
                    .getResultList();
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e);
        }
    }

    /**
     * Método que ejecuta la query "consultarTodosAlumnos".
     *
     * @return una colección de todos los usuarios.
     */
    public Collection<Usuario> consultarTodosAlumnos() {
        try {
            LOGGER.info("UsuarioAbstractFacade: Buscando todos los alumnos");
            return getEntityManager().createNamedQuery("consultarTodosAlumnos").getResultList();
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e);
        }
    }

    /**
     * Método que ejecuta la query "consultarAlumnoPorNombre".
     *
     * @param fullName el nombre completo que se quiere buscar.
     * @return una colección de alumnos.
     */
    public Collection<Usuario> consultarAlumnoPorNombre(String fullName) {
        try {
            LOGGER.info("UsuarioAbstractFacade: Buscando alumno por nombre");
            return getEntityManager().createNamedQuery("consultarAlumnoPorNombre").setParameter("fullName", fullName).getResultList();
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e);
        }
    }
}