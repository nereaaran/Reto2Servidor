/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful;

import entidad.Usuario;
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
    @Override
    @Consumes({MediaType.APPLICATION_XML})
    public void create(Usuario entity) {
        try {
            LOGGER.info("UsuarioFacadeREST: Creando usuario");
            super.create(entity);
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e);
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
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e);
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
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e);
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
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e);
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
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e);
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
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e);
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
    public Collection<Usuario> buscarLoginYContrasenia(@PathParam("login") String login, @PathParam("password") String password) {
        Collection<Usuario> ret = null;
        try {
            LOGGER.info("UsuarioFacadeREST: Buscando usuario por login y contraseña");
            ret = super.buscarLoginYContrasenia(login, password);
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e);
        }

        return ret;
    }

    /**
     * Método que busca todos los alumnos.
     *
     * @return hace una llamada a la superclase UsuarioAbstractFacade.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML})
    @Override
    public Collection<Usuario> consultarTodosAlumnos() {
        try {
            LOGGER.info("UsuarioFacadeREST: Buscando todos los alumnos");
            return super.consultarTodosAlumnos();
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e);
        }
    }

    /**
     * Método que busca un alumno por nombre.
     *
     * @param fullName el nombre completo que se usará para buscar a un alumno.
     * @return hace una llamada a la superclase UsuarioAbstractFacade.
     */
    @GET
    @Path("fullName/{fullName}")
    @Produces({MediaType.APPLICATION_XML})
    @Override
    public Collection<Usuario> consultarAlumnoPorNombre(@PathParam("fullName") String fullName) {
        try {
            LOGGER.info("UsuarioFacadeREST: Buscando alumno por nombre");
            return super.consultarAlumnoPorNombre(fullName);
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e);
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
