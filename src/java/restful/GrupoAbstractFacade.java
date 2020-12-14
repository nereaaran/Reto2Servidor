/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful;

import entidad.Grupo;
import java.util.Collection;

/**
 * Clse que opera las queries de la entidad grupo
 * @author Jonathan
 */
public abstract class GrupoAbstractFacade extends AbstractFacade<Grupo>{
    
    public GrupoAbstractFacade(Class<Grupo> entityClass) {
        super(entityClass);
    }
    
    public Collection<Grupo> listarGrupos(){
        return getEntityManager().createNamedQuery("").getResultList();
    }
    
    public Collection<Grupo> listaGrupoProfeosor(String nombre){
        return getEntityManager().createNamedQuery("").setParameter("nombre", nombre).getResultList();
    }
    
    
   
}
