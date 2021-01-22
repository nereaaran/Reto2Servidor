/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Clase de la entidad "Bibliotecario".
 *
 * @author Cristina Milea
 */
@NamedQueries({
    @NamedQuery(
            name = "consultarTodosBibliotecarios",
            query = "SELECT b FROM bibliotecario b"
    )
})

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
     * Método que obtiene la colección de libros.
     *
     * @return los libros de la colección.
     */
    @XmlTransient
    public Collection<Libro> getLibros() {
        return libros;
    }

    /**
     * Método que establece la colección de libros.
     *
     * @param libros los libros que se van a guardar.
     */
    public void setLibro(Collection<Libro> libros) {
        this.libros = libros;
    }

    /**
     * Método que compara el código hash de dos objetos.
     *
     * @return el código hash del objeto.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.libros);
        return hash;
    }

    /**
     * Método que compara si un objeto es igual al objeto "Bibliotecario".
     *
     * @param obj cualquier tipo de objeto.
     * @return un "false" si los objetos noson iguales y un "true" si lo son.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Bibliotecario other = (Bibliotecario) obj;
        if (!Objects.equals(this.libros, other.libros)) {
            return false;
        }
        return true;
    }

    /**
     * Método que devuelve un String con los atributos del bibliotecario.
     *
     * @return un String con los atributos de la entidad.
     */
    @Override
    public String toString() {
        return "Bibliotecario{" + "libros=" + libros + '}';
    }
}
