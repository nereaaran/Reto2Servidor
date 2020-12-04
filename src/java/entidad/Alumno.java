/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@DiscriminatorValue("ALUMNO") //Valor que diferenciará a los alumnos en la tabla de usuarios.
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
     * Relación 1:N de la entidad "Alumno" con "AlumnoLibro".
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "alumno")
    private Collection<AlumnoLibro> AlumnoLibro;

    /**
     * Relación N:M de la entidad "Alumno" con "Grupo".
     */
    @ManyToMany(CascadeType.ALL)
    @JoinTable(name = "alumno_grupo", schema = "bibliotecadb", joinColumns = @JoinColumn(name = "idAlumno", referencedColumnName = "idUsuario"),
            inverseJoinColumns = @JoinColumn(name = "idGrupo", referencedColumnName = "idGrupo")) //Como la relación no tiene atributos, se pone @JoinTable.
    private Collection<Grupo> grupos;

    /**
     * Método que establece el teléfono del alumno.
     *
     * @return el teléfono que va a mostrar.
     */
    public Integer getTelefono() {
        return telefono;
    }

    /**
     * Método que obtiene el teléfono del alumno.
     *
     * @param telefono el teléfono que se va a guardar.
     */
    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    /**
     * Método que establece la fecha de nacimiento del alumno.
     *
     * @return la fecha de nacimiento que va a mostrar.
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Método que obtiene la fecha de nacimiento del alumno.
     *
     * @param fechaNacimiento la fecha de nacimineto que se va a guardar.
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Método que establece la colección de libros.
     *
     * @return los libros de la colección.
     */
    public Collection<AlumnoLibro> getLibros() {
        return libros;
    }

    /**
     * Método que obtiene la colección de libros.
     *
     * @param libros los libros que se van a guardar.
     */
    public void setLibro(Collection<AlumnoLibro> libros) {
        this.libros = libros;
    }

    /**
     * Método que establece la colección de grupos.
     *
     * @return los grupos de la colección.
     */
    public Collection<Grupo> getGrupos() {
        return grupos;
    }

    /**
     * Método que obtiene la colección de grupos.
     *
     * @param grupos los grupos que se van a guardar.
     */
    public void setGrupos(Collection<Grupo> grupos) {
        this.grupos = grupos;
    }
}
