/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful;

import entidad.AlumnoLibro;
import entidad.AlumnoLibroId;
import excepcion.*;
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
import javax.ws.rs.core.PathSegment;

/**
 * Clase que contiene y ejecuta las operaciones CRUD de la entidad
 * "AlumnoLibro".
 *
 * @author Nerea Aranguren
 */
@Stateless
@Path("alumnolibro")
public class AlumnoLibroFacadeREST extends AbstractFacade<AlumnoLibro> {

    /**
     * Atributo estático y constante que guarda los loggers de la clase.
     */
    private static final Logger LOGGER = Logger.getLogger("restful.AlumnoLibroFacadeREST");

    /**
     * Variable de Entity Manager que gestiona los datos que llegan a la BBDD.
     */
    @PersistenceContext(unitName = "Reto2ServidorPU")
    private EntityManager em;

    private AlumnoLibroId getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;alumnoId=alumnoIdValue;libroId=libroIdValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        entidad.AlumnoLibroId key = new entidad.AlumnoLibroId();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> idUsuario = map.get("idUsuario");
        if (idUsuario != null && !idUsuario.isEmpty()) {
            key.setIdUsuario(new java.lang.Integer(idUsuario.get(0)));
        }
        java.util.List<String> idLibro = map.get("idLibro");
        if (idLibro != null && !idLibro.isEmpty()) {
            key.setIdLibro(new java.lang.Integer(idLibro.get(0)));
        }
        return key;
    }

    /**
     * Constructor que usa el constructor de la superclase.
     */
    public AlumnoLibroFacadeREST() {
        super(AlumnoLibro.class);
    }

    /**
     * Método que crea un AlumnoLibro cuando le llega una peticion de tipo POST
     * por HTTP.
     *
     * @param entity La entidad "AlumnoLibro".
     */
    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML})
    public void create(AlumnoLibro entity) {
        try {
            LOGGER.info("AlumnoLibroFacadeREST: Creando alumnolibro");
            super.create(entity);
        } catch (CreateException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    /**
     * Método que modifica un AlumnoLibro cuando le llega un petición de tipo
     * PUT por HTTP.
     *
     * @param entity La entidad "AlumnoLibro".
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML})
    @Override
    public void edit(AlumnoLibro entity) {
        try {
            LOGGER.info("AlumnoLibroFacadeREST: Editando alumnolibro");
            super.edit(entity);
        } catch (UpdateException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    /**
     * Método que elimina un AlumnoLibro cuando le llega un petición de tipo
     * DELETE por HTTP.
     *
     * @param id El id que se usa para buscar un "AlumnoLibro".
     */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        try {
            LOGGER.info("AlumnoLibroFacadeREST: Borrando alumnolibro");
            entidad.AlumnoLibroId key = getPrimaryKey(id);
            super.remove(super.find(key));
        } catch (ReadException | DeleteException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    /**
     * Método que devuelve una variable tipo AlumnoLibro cuando llega una
     * petición de tipo GET por HTTP.
     *
     * @param id El id del AlumnoLibro que se busca.
     * @return El AlumnoLibro correspondiente a la id.
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML})
    public AlumnoLibro find(@PathParam("id") PathSegment id) {
        try {
            LOGGER.info("AlumnoLibroFacadeREST: Buscando alumnolibro");
            entidad.AlumnoLibroId key = getPrimaryKey(id);
            return super.find(key);
        } catch (ReadException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    /**
     * Método que establece el Entity Manager.
     *
     * @return El Entity MAnager.
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
