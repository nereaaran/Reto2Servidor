/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Clase de la entidad "Bibliotecario".
 *
 * @author Cristina Milea
 */
@Entity
@Table(name = "alumno", schema = "bibliotecadb")
@DiscriminatorValue("ALUMNO")
public class Bibliotecario extends Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Relación 1:N de la entidad "Bibliotecario" con "Libro".
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLibro")
    private Collection<Libro> libro;

    /**
     * Método que muestra la colección de libros.
     *
     * @return los libros de la colección.
     */
    public Collection<Libro> getLibro() {
        return libro;
    }

    /**
     * Método que guarda la colección de libros.
     *
     * @param libro los libros que se van a guardar.
     */
    public void setLibro(Collection<Libro> libro) {
        this.libro = libro;
    }
}
