/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful;

import entidad.Grupo;
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
 * Clase que ejecuta las operaciones CRUD en la entidad "Grupo".
 *
 * @author Jonathan Viñan
 */
@Stateless
@Path("grupo")
public class GrupoFacadeREST extends GrupoAbstractFacade {

    /**
     * Atributo estático y constante de los loggers de esta clase.
     */
    private static final Logger LOGGER = Logger.getLogger("restful.GrupoFacadeREST");

    /**
     * Variable de Entity Manager que gestiona los datos que llegan a la BBDD.
     *
     * @PersistenceContext Determina el archivo de persistencia que tiene que
     * usar
     */
    @PersistenceContext(unitName = "Reto2ServidorPU")
    private EntityManager em;

    /**
     * Constructor que usa el constructor de la superclase
     * (GrupoAbstractFacade).
     */
    public GrupoFacadeREST() {
        super(Grupo.class);
    }

    /**
     * Metodo que crea un grupo cuando mediante la peticion de tipo POST por
     * HTTP.
     *
     * @param entity La entidad "Grupo".
     */
    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML})
    public void create(Grupo entity) {
        try {
            LOGGER.info("GrupoFacadeREST: Creando grupo");
            super.create(entity);
        } catch (CreateException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    /**
     * Método que modifica un grupo cuando mediante la petición de tipo PUT por
     * HTTP.
     *
     * @param entity La entidad "Grupo".
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML})
    @Override
    public void edit(Grupo entity) {
        try {
            LOGGER.info("GrupoFacadeREST: editando grupo");
            super.edit(entity);
        } catch (UpdateException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    /**
     * Método que elimina un grupo mediante la petición de tipo DELETE por HTTP.
     *
     * @param id El id que se usa para buscar un grupo.
     */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        try {
            LOGGER.info("GrupoFacadeREST: Eliminando grupo");
            super.remove(super.find(id));
        } catch (ReadException | DeleteException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }

    }

    /**
     * Método que devuelve una variable tipo libro por una petición de tipo GET
     * por HTTP.
     *
     * @param id El id del grupo que se busca.
     * @return El grupo correspondiente a la id.
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML})
    public Grupo find(@PathParam("id") Integer id) {
        try {
            LOGGER.info("GrupoFacadeREST: Buscando grupo por id");
            return super.find(id);
        } catch (ReadException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }

    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * Método que devuelve una variable tipo Grupo cuando llega una petición de
     * tipo GET por HTTP
     *
     * @param nombre
     * @return la lista de grupos
     */
    @GET
    @Path("grupo/{nombre}")
    @Produces({MediaType.APPLICATION_XML})
    public Collection<Grupo> find(@PathParam("nombre") String nombre) {
        try {
            LOGGER.info("listarGrupoPorNombre: Listando los grupos por el nombre");
            return super.listarGrupoPorNombre(nombre);
        } catch (ReadException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    /**
     * Método que devuelve los Grupo cuando llega una petición de tipo GET por
     * HTTP
     *
     * @return
     */
    @GET
    @Path("grupos")
    @Produces({MediaType.APPLICATION_XML})
    public Collection<Grupo> find() {
        try {
            LOGGER.info("listarTodosLosGrupo: Listando los grupos");
            return super.listarGrupos();
        } catch (ReadException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

}
