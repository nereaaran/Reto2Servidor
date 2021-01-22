/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful;

import entidad.Profesor;
import entidad.Profesor;
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
 * Clase que ejecuta las operaciones CRUD en la entidad "Profesor".
 *
 * @author Jonathan Viñan
 */
@Stateless
@Path("profesor")
public class ProfesorFacadeREST extends ProfesorAbstractFacade {

    /**
     * Atributo estático y constante que guarda los loggers de esta clase.
     */
    private static final Logger LOGGER = Logger.getLogger("restful.ProfesorFacadeREST");

    @PersistenceContext(unitName = "Reto2ServidorPU")
    private EntityManager em;

    /**
     * Constructor que usa el constructor de la superclase (AbstractFacade).
     */
    public ProfesorFacadeREST() {
        super(Profesor.class);
    }

    /**
     * Metodo que crea un profesor cuando le llega una peticion de tipo POST por
     * HTTP.
     *
     * @param entity La entidad "Profesor".
     */
    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML})
    public void create(Profesor entity) {
        try {
            LOGGER.info("ProfesorFacadeREST: Creando profesor");
            super.create(entity);
        } catch (CreateException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    /**
     * Método que modifica un profesor cuando le llega un petición de tipo PUT
     * por HTTP.
     *
     * @param entity La entidad "Profesor".
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML})
    @Override
    public void edit(Profesor entity) {
        try {
            LOGGER.info("ProfesorFacadeREST: Editando profesor");
            super.edit(entity);
        } catch (UpdateException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    /**
     * Método que elimina un profesor cuando le llega una petición de tipo
     * DELETE por HTTP
     *
     * @param id El id que se usa para buscar un profesor.
     */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        try {
            LOGGER.info("ProfesorFacadeREST: Borrando profesor");
            super.remove(super.find(id));
        } catch (ReadException | DeleteException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    /**
     * Método que devuelve una variable tipo profesor cuando llega una petición
     * de tipo GET por HTTP.
     *
     * @param id El id del profesor que se busca.
     * @return El profesor correspondiente a la id.
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML})
    public Profesor find(@PathParam("id") Integer id) {
        try {
            LOGGER.info("ProfesorFacadeREST: Buscando profesor");
            return super.find(id);
        } catch (ReadException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    //@author Cristina Milea
    /**
     * Método que busca todos los profesores.
     *
     * @return hace una llamada a la superclase ProfesorAbstractFacade.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML})
    @Override
    public Collection<Profesor> consultarTodosProfesores() {
        try {
            LOGGER.info("ProfesorFacadeREST: Buscando todos los profesores");
            return super.consultarTodosProfesores();
        } catch (ReadException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    /**
     * Método que establece el Entity Manager.s
     *
     * @return El Entity MAnager.
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
