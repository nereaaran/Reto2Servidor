/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful;

import entidad.GrupoLibro;
import entidad.GrupoLibroId;
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
 * Clase que ejecuta los de las operaciones GRUD en la entidad GrupoLibro
 * @author Jonathan Viñan
 */
@Stateless
@Path("grupolibro")
public class GrupoLibroFacadeREST extends AbstractFacade<GrupoLibro> {

    /**
     * Atributo estático y constante que guarda los loggers de esta clase.
     */
    private static final Logger LOGGER = Logger.getLogger("restful.LibroFacadeREST");
    /**
     * Variable de Entity Manager que gestiona los datos que llegan a la BBDD.
     *
     * @PersistenceContext Determina el archivo de persistencia que tiene que
     * usar
     */
    @PersistenceContext(unitName = "Reto2ServidorPU")
    private EntityManager em;

    private GrupoLibroId getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;grupoId=grupoIdValue;libroId=libroIdValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        entidad.GrupoLibroId key = new entidad.GrupoLibroId();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> idGrupo = map.get("idGrupo");
        if (idGrupo != null && !idGrupo.isEmpty()) {
            key.setIdGrupo(new java.lang.Integer(idGrupo.get(0)));
        }
        java.util.List<String> idLibro = map.get("idLibro");
        if (idLibro != null && !idLibro.isEmpty()) {
            key.setIdLibro(new java.lang.Integer(idLibro.get(0)));
        }
        return key;
    }

    /**
     *Constructor que usa el constructor de la superclase
     * (AbstractFacade).
     */
    public GrupoLibroFacadeREST() {
        super(GrupoLibro.class);
    }
    /**
     * /**
     * Metodo que crea un GrupoLibro cuando le llega una peticion de tipo POST
     * por HTTP.
     * @param entity La entidad "Libro".
     */
    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML})
    public void create(GrupoLibro entity) {
        try {
            LOGGER.info("GrupoLibroFacadeREST: Creando libro");
            super.create(entity);
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e);
        }
    }
    /**
     * Método que modifica un grupoLibro cuando le llega un petición de tipo 
     * PUT por HTTP.
     * @param entity La entidad "GrupoLibro".
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML})
    @Override
    public void edit(GrupoLibro entity) {
         try {
            LOGGER.info("GRupoLibroFacadeREST: Editando grupolibro");
            super.edit(entity);
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e);
        }
    }
 /**
     * Método que elimina un grupoLibro cuando le llega una petición de tipo DELETE
     * por HTTP.
     * @param id El id que se usa para buscar un grupolibro.
     */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
         try {
            LOGGER.info("GrupoLibroFacadeREST: Borrando grupolibro");
            entidad.GrupoLibroId key = getPrimaryKey(id);
            super.remove(super.find(key));
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e);
        }
    }
    /**
     * Método que devuelve una variable tipo grupolibro cuando llega una petición de
     * tipo GET por HTTP.
     * @param id El id del grupolibro que se busca.
     * @return El grupolibro correspondiente a la id.
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML})
    public GrupoLibro find(@PathParam("id") PathSegment id) {
        entidad.GrupoLibroId key = getPrimaryKey(id);
        return super.find(key);
    }
    /**
     * Método que establece el Entity Manager.
     * @return El Entity MAnager.
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
