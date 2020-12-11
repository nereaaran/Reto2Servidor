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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Clase de la entidad "Bibliotecario".
 *
 * @author Cristina Milea
 */
@Entity(name = "bibliotecario")
@DiscriminatorValue("BIBLIOTECARIO") //Valor que diferenciará al bibliotecario en la tabla de usuarios.
@XmlRootElement
public class Bibliotecario extends Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Relación 1:N de la entidad "Bibliotecario" con "Libro".
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bibliotecario")
    private Collection<Libro> libros;

    /**
     * Método que establece la colección de libros.
     *
     * @return los libros de la colección.
     */
    @XmlTransient
    public Collection<Libro> getLibros() {
        return libros;
    }

    /**
     * Método que obtiene la colección de libros.
     *
     * @param libros los libros que se van a guardar.
     */
    public void setLibro(Collection<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return "Bibliotecario{" + "libros=" + libros + '}';
    }
}
