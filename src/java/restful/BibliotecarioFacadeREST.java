/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful;

import entidad.Bibliotecario;
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
import seguridad.CifradoAsimetrico;
import seguridad.CifradoHash;

/**
 * Clase que ejecuta las operaciones CRUD en la entidad "Bibliotecario".
 *
 * @author Cristina Milea
 */
@Stateless
@Path("bibliotecario")
public class BibliotecarioFacadeREST extends BibliotecarioAbstractFacade {

    /**
     * Atributo estático y constante que guarda los loggers de la clase.
     */
    private static final Logger LOGGER = Logger.getLogger("restful.BibliotecarioFacadeREST");

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
        entity.setPassword(descifrarContrasena(entity.getPassword()));
        entity.setPassword(cifrarContrasena(entity.getPassword()));
        
        try {
            LOGGER.info("BibliotecarioFacadeREST: Creando bibliotecario");
            super.create(entity);
        } catch (CreateException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
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
        entity.setPassword(descifrarContrasena(entity.getPassword()));
        entity.setPassword(cifrarContrasena(entity.getPassword()));
        
        try {
            LOGGER.info("BibliotecarioFacadeREST: Editando bibliotecario");
            super.edit(entity);
        } catch (UpdateException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
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
        try {
            LOGGER.info("BibliotecarioFacadeREST: Borrando bibliotecario");
            super.remove(super.find(id));
        } catch (ReadException | DeleteException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
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
        try {
            LOGGER.info("BibliotecarioFacadeREST: Buscando bibliotecario");
            return super.find(id);
        } catch (ReadException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    /**
     * Método que busca todos los bibliotecarios.
     *
     * @return hace una llamada a la superclase BibliotecarioAbstractFacade.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML})
    @Override
    public Collection<Bibliotecario> buscarTodosLosBibliotecarios() {
        try {
            LOGGER.info("BibliotecarioFacadeREST: Buscando todos los profesores");
            return super.buscarTodosLosBibliotecarios();
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

    /**
     * Cifra la contraseña para guardarla en la base de datos.
     *
     * @param contrasena La contraseña del usuario.
     * @return La contraseña cifrada.
     */
    private String cifrarContrasena(String contrasena) {
        LOGGER.info("BibliotecarioFacadeREST: Cifrando contraseña");
        CifradoHash cifrarHash = new CifradoHash();
        return cifrarHash.cifrarTextoEnHash(contrasena);
    }

    /**
     * Descifra la contraseña que le ha llegado del cliente.
     *
     * @param contrasena La contraseña cifrada del usuario.
     * @return La contraseña descifrada.
     */
    private String descifrarContrasena(String contrasena) {
        LOGGER.info("BibliotecarioFacadeREST: Descifrando contraseña");
        CifradoAsimetrico descifrarAsimetrico = new CifradoAsimetrico();
        return descifrarAsimetrico.descifrarConClavePrivada(contrasena);
    }

}
