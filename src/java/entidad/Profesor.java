/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Clase Profesor con sus respectivos atributos
 * @author Jonathan Viñan
 */
@NamedQueries({ //@author Cristina Milea
    @NamedQuery(
            name = "buscarTodosLosProfesores",
            query = "SELECT p FROM profesor p"
    )
})

@Entity(name = "profesor")
@DiscriminatorValue("PROFESOR") //Valor que diferenciará al profesor en la tabla de usuarios.
@XmlRootElement
public class Profesor extends Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Numero de telefono del profesor.
     */
    private Integer telefono;
    /**
     * Obtiene el telefono.
     * @return telefono.
     */
    public Integer getTelefono() {
        return telefono;
    }
    /**
     * Establece el telefono
     * @param telefono que almacenamos
     */
    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }
    /**
     * Relacion 1:N con la entidad Grupo.
     */
    @OneToMany(cascade = ALL, mappedBy = "profesor")
    private Collection<Grupo> grupos;
    /**
     * Obtiene la coleccion del Grupo.
     * @return coleccion Grupo.
     */
    @XmlTransient
    public Collection<Grupo> getGrupos() {
        return grupos;
    }
    /**
     * Establece la coleccion grupo.
     * @param grupos.
     */
    public void setGrupos(Collection<Grupo> grupos) {
        this.grupos = grupos;
    }
    /**
     * Método que compara el código hash de dos objetos.
     * @return el código hash del objeto.
     */
    @Override    
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.telefono);
        hash = 61 * hash + Objects.hashCode(this.grupos);
        return hash;
    }

    /**
     * Método que compara si un objeto es igual al objeto "Profesor".
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
        final Profesor other = (Profesor) obj;
        if (!Objects.equals(this.telefono, other.telefono)) {
            return false;
        }
        if (!Objects.equals(this.grupos, other.grupos)) {
            return false;
        }
        return true;
    }
    /**
     * Método que devuelve un String con los atributos del profesor.
     *
     * @return un String con los atributos de la entidad.
     */
    @Override
    public String toString() {
        return "Profesor{" + "telefono=" + telefono + ", grupos=" + grupos + '}';
    }
}