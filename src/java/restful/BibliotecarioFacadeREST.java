/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful;

import entidad.Bibliotecario;
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
 * Clase que ejecuta las operaciones CRUD en la entidad "Bibliotecario".
 *
 * @author Cristina Milea
 */
@Stateless
@Path("entidad.bibliotecario")
public class BibliotecarioFacadeREST extends AbstractFacade<Bibliotecario> {

    /**
     * El Entity Manager gestiona los datos que llegan a la base de datos.
     */
    @PersistenceContext(unitName = "Reto2ServidorPU")
    private EntityManager em;

    /**
     * Constructor que llama al constructor de la superclase (AbstractFacade).
     */
    public BibliotecarioFacadeREST() {
        super(Bibliotecario.class);
    }

    /**
     * Método que crea un bibliotecario cuando le llegue una petición de tipo
     * POST por HTTP.
     *
     * @param entity la entidad "Biliotecario".
     */
    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML})
    public void create(Bibliotecario entity) {
        super.create(entity);
    }

    /**
     * Método que edita un bibliotecario cuando le llegue una petición de tipo
     * PUT por HTTP.
     *
     * @param entity la entidad "Biliotecario".
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML})
    @Override
    public void edit(Bibliotecario entity) {
        super.edit(entity);
    }

    /**
     * Método que borra un bibliotecario cuando le llegue una petición de tipo
     * DELETE por HTTP.
     *
     * @param id el id que se usará para buscar a un bibliotecario.
     */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    /**
     * Método que busca un bibliotecario cuando le llegue una petición de tipo
     * GET por HTTP.
     *
     * @param id el id que se usará para buscar a un bibliotecario.
     * @return el id que encuentre.
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML})
    public Bibliotecario find(@PathParam("id") Integer id) {
        return super.find(id);
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
