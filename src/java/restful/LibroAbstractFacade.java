/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful;

import entidad.Libro;
import java.util.Collection;

/**
 * Clase que maneja las queries de la entidad Libro
 * @author Nerea Aranguren
 */
public abstract class LibroAbstractFacade extends AbstractFacade<Libro>{

    public LibroAbstractFacade(Class<Libro> entityClass) {
        super(entityClass);
    }
    
    /**
     * 
     * @return 
     */
    public Collection<Libro> buscarTodosLibrosBibliotecario(){//estos metodos se tienen   ue llamar como las queries?
        return getEntityManager().createNamedQuery("").getResultList();
    }
    
    public Collection<Libro> buscarTodosLibrosAlumnoProfesor(){
        return getEntityManager().createNamedQuery("").getResultList();
    }
    
    public Collection<Libro> buscarLibrosPorTituloBibliotecario(String titulo){
        return getEntityManager().createNamedQuery("").setParameter("titulo", titulo ).getResultList();
    }
    
    public Collection<Libro> buscarLibrosPorTituloAlumnoProfesor(String titulo){
        return getEntityManager().createNamedQuery("").setParameter("titulo", titulo ).getResultList();
    }
    
    public Collection<Libro> buscarLibrosPorAutorAlumnoProfesor(String autor){
        return getEntityManager().createNamedQuery("").setParameter("autor", autor ).getResultList();
    }
    
}
