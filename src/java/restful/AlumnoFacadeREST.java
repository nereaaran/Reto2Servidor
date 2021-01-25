/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful;

import entidad.Alumno;
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
 * Clase que ejecuta las operaciones CRUD en la entidad "Alumno".
 *
 * @author Cristina Milea
 */
@Stateless
@Path("alumno")
public class AlumnoFacadeREST extends AlumnoAbstractFacade {

    /**
     * Atributo estático y constante que guarda los loggers de la clase.
     */
    private static final Logger LOGGER = Logger.getLogger("restful.AlumnoFacadeREST");

    /**
     * El Entity Manager gestiona los datos que llegan a la base de datos.
     */
    @PersistenceContext(unitName = "Reto2ServidorPU")
    private EntityManager em;

    /**
     * Constructor que llama al constructor de la superclase (AlumnoAbstractFacade).
     */
    public AlumnoFacadeREST() {
        super(Alumno.class);
    }

    /**
     * Método que crea un alumno cuando le llegue una petición de tipo POST por
     * HTTP.
     *
     * @param entity la entidad "Alumno".
     */
    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML})
    public void create(Alumno entity) {
        try {
            LOGGER.info("AlumnoFacadeREST: Creando alumno");
            super.create(entity);
        } catch (CreateException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    /**
     * Método que edita un alumno cuando le llegue una petición de tipo PUT por
     * HTTP.
     *
     * @param entity la entidad "Alumno".
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML})
    @Override
    public void edit(Alumno entity) {
        try {
            LOGGER.info("AlumnoFacadeREST: Editando alumno");
            super.edit(entity);
        } catch (UpdateException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    /**
     * Método que borra un alumno cuando le llegue una petición de tipo DELETE
     * por HTTP.
     *
     * @param login el login que se usará para buscar a un alumno.
     */
    @DELETE
    @Path("{login}")
    public void remove(@PathParam("login") String login) {
        try {
            LOGGER.info("AlumnoFacadeREST: Borrando alumno");
            super.remove(super.find(login));
        } catch (ReadException | DeleteException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    /**
     * Método que busca un alumno cuando le llegue una petición de tipo GET por
     * HTTP.
     *
     * @param id el id que se usará para buscar a un alumno.
     * @return el id que encuentre.
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML})
    public Alumno find(@PathParam("id") Integer id) {
        try {
            LOGGER.info("AlumnoFacadeREST: Buscando alumno");
            return super.find(id);
        } catch (ReadException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    /**
     * Método que busca un alumno por nombre.
     *
     * @param fullName el nombre completo que se usará para buscar a un alumno.
     * @return hace una llamada a la superclase AlumnoAbstractFacade.
     */
    @GET
    @Path("fullName/{fullName}")
    @Produces({MediaType.APPLICATION_XML})
    @Override
    public Collection<Alumno> buscarAlumnoPorNombre(@PathParam("fullName") String fullName) {
        try {
            LOGGER.info("AlumnoFacadeREST: Buscando alumno por nombre");
            return super.buscarAlumnoPorNombre(fullName);
        } catch (ReadException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    /**
     * Método que busca todos los alumnos.
     *
     * @return hace una llamada a la superclase AlumnoAbstractFacade.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML})
    @Override
    public Collection<Alumno> buscarTodosLosAlumnos() {
        try {
            LOGGER.info("AlumnoFacadeREST: Buscando todos los alumnos");
            return super.buscarTodosLosAlumnos();
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
