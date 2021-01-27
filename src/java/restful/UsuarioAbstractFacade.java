/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful;

import entidad.Usuario;
import excepcion.ReadException;
import java.util.Collection;
import java.util.logging.Logger;
import mail.Mail;
import seguridad.CifradoHash;

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
     * @throws excepcion.ReadException excepción al buscar un usuario.
     */
    public Collection<Usuario> buscarUsuarioPorLogin(String login) throws ReadException {
        try {
            LOGGER.info("UsuarioAbstractFacade: Buscando usuario por login");
            return getEntityManager().createNamedQuery("buscarUsuarioPorLogin").setParameter("login", login).getResultList();
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new ReadException(e.getMessage());
        }
    }

    /**
     * Método que ejecuta la query "buscarUsuarioPorEmail".
     *
     * @param email el email que se quiere buscar.
     * @return una colección de usuarios.
     * @throws excepcion.ReadException excepción al buscar un usuario.
     */
    public Collection<Usuario> buscarUsuarioPorEmail(String email) throws ReadException {
        try {
            LOGGER.info("UsuarioAbstractFacade: Buscando usuario por email");
            return getEntityManager().createNamedQuery("buscarUsuarioPorEmail").setParameter("email", email).getResultList();
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new ReadException(e.getMessage());
        }
    }

    /**
     * Método que ejecuta la query "buscarUsuarioPorEmail" para enviar un mail.
     *
     * @param usuario la entidad Usuario.
     * @throws excepcion.ReadException excepción al buscar un usuario.
     */
    public void buscarUsuarioParaEnviarMailRecuperarContrasenia(Usuario usuario) throws ReadException {
        try {
            LOGGER.info("UsuarioAbstractFacade: Buscando usuario por email para enviar mail de recuperación de contraseña");

            Collection<Usuario> usuarioCol = getEntityManager().createNamedQuery("buscarUsuarioPorEmail").setParameter("email", usuario.getEmail()).getResultList();

            if (!usuarioCol.isEmpty()) {
                String nuevaContrasenia = Mail.enviarMailRecuperarContrasenia(usuario);
                CifradoHash cifradoHash = new CifradoHash();
                nuevaContrasenia = cifradoHash.cifrarTextoEnHash(nuevaContrasenia);

                for (Usuario u : usuarioCol) {
                    u.setPassword(nuevaContrasenia);
                }
            }
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new ReadException(e.getMessage());
        }
    }

    /**
     * Método que ejecuta la query "buscarUsuarioPorEmail" para enviar un mail.
     *
     * @param email el email del usuario.
     * @throws excepcion.ReadException excepción al buscar un usuario.
     */
    public void buscarEmailParaEnviarMailCambiarContrasenia(String email) throws ReadException {
        try {
            LOGGER.info("UsuarioAbstractFacade: Buscando email para enviar mail de cambio de contraseña");

            getEntityManager().createNamedQuery("buscarUsuarioPorEmail").setParameter("email", email).getResultList();

            Mail.enviarMailCambiarContrasenia(email);
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new ReadException(e.getMessage());
        }
    }

    /**
     * Método que ejecuta la query "buscarLoginYContrasenia".
     *
     * @param login el login que se quiere buscar.
     * @param password la contraseña que se quiere buscar.
     * @return una colección de usuarios.
     * @throws excepcion.ReadException excepción al buscar un usuario.
     */
    public Collection<Usuario> buscarUsuarioPorLoginYContrasenia(String login, String password) throws ReadException {
        try {
            LOGGER.info("UsuarioAbstractFacade: Buscando usuario por login y contraseña");
            return getEntityManager().createNamedQuery("buscarUsuarioPorLoginYContrasenia")
                    .setParameter("login", login)
                    .setParameter("password", password)
                    .getResultList();
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new ReadException(e.getMessage());
        }
    }

    /**
     * Método que ejecuta la query "buscarTodosLosUsuarios".
     *
     * @return una colección de todos los usuarios.
     * @throws excepcion.ReadException excepción al buscar un usuario.
     */
    public Collection<Usuario> buscarTodosLosUsuarios() throws ReadException {
        try {
            LOGGER.info("UsuarioAbstractFacade: Buscando todos los usuarios");
            return getEntityManager().createNamedQuery("buscarTodosLosUsuarios").getResultList();
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new ReadException(e.getMessage());
        }
    }
}
