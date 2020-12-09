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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Clase que define los atributos y los métodos de la entidad "Alumno".
 *
 * @author Cristina Milea
 */
@Entity(name = "alumno")
@DiscriminatorValue("ALUMNO") //Valor que diferenciará a los alumnos en la tabla de usuarios.
@XmlRootElement
public class Alumno extends Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * DNI del alumno.
     */
    private String dni;
    /**
     * Fecha de nacimiento del alumno.
     */
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
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "alumno_grupo", schema = "bibliotecadb", joinColumns = @JoinColumn(name = "idAlumno", referencedColumnName = "idUsuario"),
            inverseJoinColumns = @JoinColumn(name = "idGrupo", referencedColumnName = "idGrupo")) //Como la relación no tiene atributos, se pone @JoinTable.
    private Collection<Grupo> grupos;

    /**
     * Método que establece el DNI del alumno.
     *
     * @return el DNI que va a mostrar.
     */
    public String getDni() {
        return dni;
    }

    /**
     * Método que obtiene el DNI del alumno.
     *
     * @param dni el DNI que se va a guardar.
     */
    public void setDni(String dni) {
        this.dni = dni;
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
     * Método que establece la colección de libros por alumno.
     *
     * @return los alumnos y sus libros de la colección.
     */
    public Collection<AlumnoLibro> getAlumnoLibro() {
        return AlumnoLibro;
    }

    /**
     * Método que obtiene la colección de libros por alumno.
     *
     * @param AlumnoLibro los alumnos y sus libros que se van a guardar.
     */
    public void setAlumnoLibro(Collection<AlumnoLibro> AlumnoLibro) {
        this.AlumnoLibro = AlumnoLibro;
    }

    /**
     * Método que establece la colección de grupos.
     *
     * @return los grupos de la colección.
     */
    @XmlTransient
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
