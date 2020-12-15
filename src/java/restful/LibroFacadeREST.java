/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful;

import entidad.Libro;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Nerea Aranguren
 */
@Stateless
@Path("entidad.libro")
public class LibroFacadeREST extends LibroAbstractFacade {

    @PersistenceContext(unitName = "Reto2ServidorPU")
    private EntityManager em;

    public LibroFacadeREST() {
        super(Libro.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML})
    public void create(Libro entity) {
        super.create(entity);
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML})
    public void edit(Libro entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML})
    public Libro find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}