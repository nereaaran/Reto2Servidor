/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful;

import entidad.Usuario;
import excepcion.*;
import java.util.Collection;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Clase que ejecuta las operaciones CRUD en la entidad "Usuario".
 *
 * @author Cristina Milea
 */
@Stateless
@Path("usuario")
public class UsuarioFacadeREST extends UsuarioAbstractFacade {

    /**
     * Atributo estático y constante que guarda los loggers de la clase.
     */
    private static final Logger LOGGER = Logger.getLogger("restful.UsuarioFacadeREST");

    /**
     * El Entity Manager gestiona los datos que llegan a la base de datos.
     */
    @PersistenceContext(unitName = "Reto2ServidorPU")
    private EntityManager em;

    /**
     * Constructor que llama al constructor de la superclase
     * (UsuarioAbstractFacade).
     */
    public UsuarioFacadeREST() {
        super(Usuario.class);
    }

    /**
     * Método que crea un usuario cuando le llegue una petición de tipo POST por
     * HTTP.
     *
     * @param entity la entidad "Usuario".
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML})
    @Override
    public void create(Usuario entity) {
        try {
            LOGGER.info("UsuarioFacadeREST: Creando usuario");
            super.create(entity);
        } catch (CreateException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    /**
     * Método que edita un usuario cuando le llegue una petición de tipo PUT por
     * HTTP.
     *
     * @param entity la entidad "Usuario".
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML})
    @Override
    public void edit(Usuario entity) {
        try {
            LOGGER.info("UsuarioFacadeREST: Editando usuario");
            super.edit(entity);
        } catch (UpdateException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    /**
     * Método que borra un usuario cuando le llegue una petición de tipo DELETE
     * por HTTP.
     *
     * @param id el id que se usará para buscar a un usuario.
     */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        try {
            LOGGER.info("UsuarioFacadeREST: Borrando usuario");
            super.remove(super.find(id));
        } catch (ReadException | DeleteException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    /**
     * Método que busca un usuario cuando le llegue una petición de tipo GET por
     * HTTP.
     *
     * @param id el id que se usará para buscar a un usuario.
     * @return el id que encuentre.
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML})
    public Usuario find(@PathParam("id") Integer id) {
        try {
            LOGGER.info("UsuarioFacadeREST: Buscando usuario");
            return super.find(id);
        } catch (ReadException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    /**
     * Método que busca un usuario por su login.
     *
     * @param login el login que se usará para buscar a un usuario.
     * @return hace una llamada a la superclase UsuarioAbstractFacade.
     */
    @GET
    @Path("login/{login}")
    @Produces({MediaType.APPLICATION_XML})
    @Override
    public Collection<Usuario> buscarUsuarioPorLogin(@PathParam("login") String login) {
        try {
            LOGGER.info("UsuarioFacadeREST: Buscando usuario por login");
            return super.buscarUsuarioPorLogin(login);
        } catch (ReadException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    /**
     * Método que busca un usuario por su email.
     *
     * @param email el email que se usará para buscar a un usuario.
     * @return hace una llamada a la superclase UsuarioAbstractFacade.
     */
    @GET
    @Path("email/{email}")
    @Produces({MediaType.APPLICATION_XML})
    @Override
    public Collection<Usuario> buscarUsuarioPorEmail(@PathParam("email") String email) {
        try {
            LOGGER.info("UsuarioFacadeREST: Buscando usuario por email");
            return super.buscarUsuarioPorEmail(email);
        } catch (ReadException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    /**
     * Método que busca un usuario por su email para enviar el mail de
     * recuperación de contraseña.
     *
     * @param usuario el usuario que se buscará.
     */
    @POST
    @Path("enviarMailRecuperacion")
    @Consumes({MediaType.APPLICATION_XML})
    @Override
    public void buscarUsuarioParaEnviarMailRecuperarContrasenia(Usuario usuario) {
        try {
            LOGGER.info("UsuarioFacadeREST: Buscando usuario por email para enviar mail de recuperación de contraseña");
            super.buscarUsuarioParaEnviarMailRecuperarContrasenia(usuario);
        } catch (ReadException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    /**
     * Método que busca un usuario por su email para enviar el mail de cambio de
     * contraseña.
     *
     * @param usuario el usuario que se buscará.
     */
    @POST
    @Path("enviarMailCambio")
    @Consumes({MediaType.APPLICATION_XML})
    @Override
    public void buscarUsuarioParaEnviarMailCambiarContrasenia(Usuario usuario) {
        try {
            LOGGER.info("UsuarioFacadeREST: Buscando usuario por email para enviar mail de cambio de contraseña");
            super.buscarUsuarioParaEnviarMailCambiarContrasenia(usuario);
        } catch (ReadException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    /**
     * Método que busca un usuario por su login y contraseña.
     *
     * @param login el login que se usará para buscar a un usuario.
     * @param password la contraseña que se usará para buscar a un usuario.
     * @return hace una llamada a la superclase UsuarioAbstractFacade.
     */
    @GET
    @Path("loginYPassword/{login}/{password}")
    @Produces({MediaType.APPLICATION_XML})
    @Override
    public Collection<Usuario> buscarUsuarioPorLoginYContrasenia(@PathParam("login") String login, @PathParam("password") String password) {
        try {
            LOGGER.info("UsuarioFacadeREST: Buscando usuario por login y contraseña");
            return super.buscarUsuarioPorLoginYContrasenia(login, password);
        } catch (ReadException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    /**
     * Método que busca todos los usuarios.
     *
     * @return hace una llamada a la superclase UsuarioAbstractFacade.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML})
    @Override
    public Collection<Usuario> buscarTodosLosUsuarios() {
        try {
            LOGGER.info("UsuarioFacadeREST: Buscando todos los usuarios");
            return super.buscarTodosLosUsuarios();
        } catch (ReadException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    /**
     * Método que establece el Entity Manager.
     *
     * @return el Entity Manager.
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
