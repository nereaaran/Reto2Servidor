/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful;

import entidad.AlumnoLibro;
import entidad.AlumnoLibroId;
import java.util.List;
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
import javax.ws.rs.core.PathSegment;

/**
 *
 * @author 2dam
 */
@Stateless
@Path("entidad.alumnolibro")
public class AlumnoLibroFacadeREST extends AbstractFacade<AlumnoLibro> {

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
        java.util.List<String> alumnoId = map.get("alumnoId");
        if (alumnoId != null && !alumnoId.isEmpty()) {
            key.setAlumnoId(new java.lang.Integer(alumnoId.get(0)));
        }
        java.util.List<String> libroId = map.get("libroId");
        if (libroId != null && !libroId.isEmpty()) {
            key.setLibroId(new java.lang.Integer(libroId.get(0)));
        }
        return key;
    }

    public AlumnoLibroFacadeREST() {
        super(AlumnoLibro.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(AlumnoLibro entity) {
        super.create(entity);
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML})
    @Override
    public void edit(AlumnoLibro entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        entidad.AlumnoLibroId key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML})
    public AlumnoLibro find(@PathParam("id") PathSegment id) {
        entidad.AlumnoLibroId key = getPrimaryKey(id);
        return super.find(key);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
