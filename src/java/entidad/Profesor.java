/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Clase Profesor 
 * @author Jonathan
 */

@Entity
@Table (name="profesor", schema="bibliotecadb")
@DiscriminatorValue("PROFESOR") //Valor que diferenciará al profesor en la tabla de usuarios.
@XmlRootElement
public class Profesor extends Usuario implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Numero de telefono del profesor 
     */
    @NotNull
    private Integer telefono;


    /**
     * Muestroa el telefono
     * @return telefono
     */
    public Integer getTelefono() {
        return telefono;
    }
    
    /**
     * Guarda el dato telefono
     * @param telefono que almacenamos
     */
    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    //Relacion
    @OneToMany(cascade = ALL, mappedBy = "grupo")
    private Collection<Grupo> grupos;

    @XmlTransient
    public Collection<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(Collection<Grupo> grupos) {
        this.grupos = grupos;
    }

    
    
    
    
    
    

    
    
    

    
    
    
}
