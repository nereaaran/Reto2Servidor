/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful;

import entidad.Profesor;
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
 *
 * @author Cristina Milea
 */
@Stateless
@Path("profesor")
public class ProfesorFacadeREST extends AbstractFacade<Profesor> {
    
    /**
     * Atributo estático y constante que guarda los loggers de esta clase.
     */
    private static final Logger LOGGER = Logger.getLogger("restful.LibroFacadeREST");


    @PersistenceContext(unitName = "Reto2ServidorPU")
    private EntityManager em;

    /**
     * Constructor que usa el constructor de la superclase
     * (AbstractFacade).
     */
    public ProfesorFacadeREST() {
        super(Profesor.class);
    }
    /**
     * Metodo que crea un profesor cuando le llega una peticion de tipo POST por
     * HTTP.
     * @param entity La entidad "Profesor".
     */
    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML})
    public void create(Profesor entity) {
        try {
            LOGGER.info("ProfesorFacadeREST: Creando profesor");
            super.create(entity);
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e);
        }
    }
    /**
     * Método que modifica un profesor cuando le llega un petición de tipo PUT por
     * HTTP.
     * @param entity La entidad "Profesor".
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML})
    @Override
    public void edit(Profesor entity) {
        try {
            LOGGER.info("ProfesorFacadeREST: Editando profesor");
            super.edit(entity);
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e);
        }
    }
    /**
     * Método que elimina un profesor cuando le llega una petición de tipo DELETE
     * por HTTP
     * @param id El id que se usa para buscar un profesor.
     */   
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    /**
     * Método que devuelve una variable tipo profesor cuando llega una petición de
     * tipo GET por HTTP.
     * @param id El id del profesor que se busca.
     * @return El profesor correspondiente a la id.
     */
    @GET    
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML})
    public Profesor find(@PathParam("id") Integer id) {
          try {
            LOGGER.info("ProfesorFacadeREST: Buscando libro");
            return super.find(id);
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e);
        }
    }
    /**
     * Método que establece el Entity Manager.s
     * @return El Entity MAnager.
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}