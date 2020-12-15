/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful;

import entidad.Grupo;
import java.util.Collection;
import java.util.logging.Logger;
import javax.ws.rs.InternalServerErrorException;

/**
 *
 * @author Jonathan viñan
 */
public abstract class GrupoAbstractFacade extends AbstractFacade<Grupo>{
    
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
     * @return Una coleccion de grupod.
     */
    public Collection<Grupo> listarGrupos(){
        try {
            LOGGER.info("listarGrupo: Listando todos los grupos");
            return getEntityManager()
                    .createNamedQuery("listarGrupos")
                    .getResultList();
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e);
        }
    }
    /**
     * Método que ejecuta la query "listarGrupoPorNombre".
     * @param nombre
     * @return 
     */
    public Collection<Grupo> listarGrupoPorNombre(String nombre){
        try {
             LOGGER.info("listaGrupoPorNombre: Listando todos los grupos por el nombre");
             return getEntityManager()
                .createNamedQuery("listaGrupoPorNombre")
                .setParameter("nombre", nombre)
                .getResultList();
        } catch (Exception e) {
             LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e); 
        }
    }
    
}
