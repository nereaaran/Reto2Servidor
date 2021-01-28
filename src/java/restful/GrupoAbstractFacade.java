/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful;

import entidad.Grupo;
import excepcion.ReadException;
import java.util.Collection;
import java.util.logging.Logger;

/**
 * Clase que maneja las queries de la entidad "Grupo".
 *
 * @author Jonathan viñan
 */
public abstract class GrupoAbstractFacade extends AbstractFacade<Grupo> {

    /**
     * Atributo estático y constante de los loggers de esta clase.
     */
    private static final Logger LOGGER = Logger.getLogger("restful.GrupoAbstractFacade");

    /**
     * Constructor que llama al constructor de la superclase (AbstractFacade).
     *
     * @param entityClass Objeto de tipo Grupo.
     */
    public GrupoAbstractFacade(Class<Grupo> entityClass) {
        super(entityClass);
    }

    /**
     * Método que ejecuta la query "listarGrupos".
     *
     * @return Una coleccion de grupod.
     * @throws excepcion.ReadException excepción al buscar un usuario.
     */
    public Collection<Grupo> listarGrupos() throws ReadException {

        try {
            LOGGER.info("listarGrupo: Listando todos los grupos");
            return getEntityManager()
                    .createNamedQuery("listarGrupos")
                    .getResultList();
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new ReadException(e.getMessage());
        }
    }

    /**
     * Método que ejecuta la query "listarGrupoPorNombre".
     *
     * @param nombre el nombre del grupo.
     * @return una coleccion de grupos.
     * @throws excepcion.ReadException excepción al buscar un grupo.
     */
    public Collection<Grupo> listarGrupoPorNombre(String nombre) throws ReadException {

        try {
            LOGGER.info("listaGrupoPorNombre: Listando todos los grupos por el nombre");
            return getEntityManager()
                    .createNamedQuery("listarGrupoPorNombre")
                    .setParameter("nombre", nombre)
                    .getResultList();
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new ReadException(e.getMessage());
        }
    }
}
