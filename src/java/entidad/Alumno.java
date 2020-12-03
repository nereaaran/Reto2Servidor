/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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
    @Temporal(TemporalType.DATE)
    @Past
    private Date fechaNacimiento;

    
    
    /**
     * Relación 1:N de la entidad "Alumno" con "reserva_alumno_libro".
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAlumno")
    private Collection<Libro> libros;
    
    
    
    
    /**
     * Relación N:M de la entidad "Alumno" con "Libro".
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "alumno_libro", schema = "bibliotecadb", joinColumns = @JoinColumn(name = "idAlumno", referencedColumnName = "idAlumno"),
            inverseJoinColumns = @JoinColumn(name = "idLibro", referencedColumnName = "idLibro"))
    private Collection<Libro> libros;

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
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Método que guarda la fecha de nacimiento del alumno.
     *
     * @param fechaNacimiento la fecha de nacimineto que se va a guardar.
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
