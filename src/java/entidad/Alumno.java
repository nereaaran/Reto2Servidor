/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;

/**
 * Clase que define los atributos y los métodos de la entidad "Alumno".
 *
 * @author Cristina Milea
 */
@Entity
@Table(name = "alumno", schema = "bibliotecadb")
@DiscriminatorValue("ALUMNO")
public class Alumno extends Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Teléfono del alumno.
     */
    private Integer telefono;
    /**
     * Fecha de nacimiento del alumno.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Past
    private Timestamp fechaNacimiento;
    
    
    
    
    /*@ManyToMany (fetch = FetchType.EAGER)
    @JoinTable(name = "libro_grupo", schema = "bibliotecadb", joinColumns = @JoinColumn(name = "idLibro", referencedColumnName= "idLibro"), 
            inverseJoinColumns = @JoinColumn(name = "idGrupo", referencedColumnName= "idGrupo"))
    private Collection<Grupo> grupos;*/
    
    
    
    /**
     * Método que muestra el teléfono del alumno.
     *
     * @return el teléfono que va a mostrar.
     */
    public Integer getTelefono() {
        return telefono;
    }

    /**
     * Método que guarda el teléfono del alumno.
     *
     * @param telefono el teléfono que se va a guardar.
     */
    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    /**
     * Método que muestra la fecha de nacimiento del alumno.
     *
     * @return la fecha de nacimiento que va a mostrar.
     */
    public Timestamp getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Método que guarda la fecha de nacimiento del alumno.
     *
     * @param fechaNacimiento la fecha de nacimineto que se va a guardar.
     */
    public void setFechaNacimiento(Timestamp fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

}
