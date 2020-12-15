/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful;

import entidad.Libro;
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
 * Clase que ejecuta los metodos con las operaciones CRUD de la entidad libro.
 *
 * @author Nerea Aranguren
 */
@Stateless
@Path("libro")
public class LibroFacadeREST extends LibroAbstractFacade {

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

    /**
     * Constructor que usa el constructor de la superclase
     * (LibroAbstractFacade).
     */
    public LibroFacadeREST() {
        super(Libro.class);
    }

    /**
     * Metodo que crea un libro cuando le llega una peticion de tipo POST por
     * HTTP.
     *
     * @param entity La entidad "Libro".
     */
    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML})
    public void create(Libro entity) {
        try {
            LOGGER.info("LibroFacadeREST: Creando libro");
            super.create(entity);
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e);
        }
    }

    /**
     * Método que modifica un libro cuando le llega un petición de tipo PUT por
     * HTTP.
     *
     * @param entity La entidad "Libro".
     */
    @PUT
    @Override
    @Consumes({MediaType.APPLICATION_XML})
    public void edit(Libro entity) {
        try {
            LOGGER.info("LibroFacadeREST: Editando libro");
            super.edit(entity);
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e);
        }
    }

    /**
     * Método que elimina un libro cuando le llega una petición de tipo DELETE
     * por HTTP.
     *
     * @param id El id que se usa para buscar un libro.
     */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        try {
            LOGGER.info("LibroFacadeREST: Borrando libro");
            super.remove(super.find(id));
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e);
        }
    }

    /**
     * Método que devuelve una variable tipo libro cuando llega una petición de
     * tipo GET por HTTP.
     *
     * @param id El id del libro que se busca.
     * @return El libro correspondiente a la id.
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML})
    public Libro find(@PathParam("id") Integer id) {
        try {
            LOGGER.info("LibroFacadeREST: Buscando libro");
            return super.find(id);
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e);
        }
    }
    
    
   
    
        
    /**
     * Método que busca todos los libros.
     * @return Llamada a la superclase LibroAbstractFacade.
     */
    @GET
    //@Path("")
    @Produces({MediaType.APPLICATION_XML})
    @Override
    public Collection<Libro> buscarTodosLosLibros(){
        try{
            LOGGER.info("LibroFacadeREST: Buscando todos los libros");
            return super.buscarTodosLosLibros();
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e);
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
