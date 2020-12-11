/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "alumno")
    private Collection<AlumnoLibro> alumnoLibros;

    /**
     * Relación N:M de la entidad "Alumno" con "Grupo".
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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
    
    //Si pongo el xmltransient aqui saca el alumno y no da error, pero no saca la info de los libros
    //@XmlTransient
    public Collection<AlumnoLibro> getAlumnoLibros() {
        return alumnoLibros;
    }

    /**
     * Método que obtiene la colección de libros por alumno.
     *
     * @param alumnoLibros los alumnos y sus libros que se van a guardar.
     */
    public void setAlumnoLibros(Collection<AlumnoLibro> alumnoLibros) {
        this.alumnoLibros = alumnoLibros;
    }

    /**
     * Método que establece la colección de grupos.
     *
     * @return los grupos de la colección.
     */
    //@XmlTransient
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

    /**
     * Método que compara el código hash de dos objetos.
     *
     * @return el código hash del objeto.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.dni);
        hash = 97 * hash + Objects.hashCode(this.fechaNacimiento);
        hash = 97 * hash + Objects.hashCode(this.alumnoLibros);
        hash = 97 * hash + Objects.hashCode(this.grupos);
        return hash;
    }

    /**
     * Método que compara si un objeto es igual al objeto "Alumno".
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
        final Alumno other = (Alumno) obj;
        if (!Objects.equals(this.dni, other.dni)) {
            return false;
        }
        if (!Objects.equals(this.fechaNacimiento, other.fechaNacimiento)) {
            return false;
        }
        if (!Objects.equals(this.alumnoLibros, other.alumnoLibros)) {
            return false;
        }
        if (!Objects.equals(this.grupos, other.grupos)) {
            return false;
        }
        return true;
    }

    /**
     * Método que devuelve un String con los atributos del alumno.
     *
     * @return un String con los atributos de la entidad.
     */
    @Override
    public String toString() {
        return "Alumno{" + "dni=" + dni + ", fechaNacimiento=" + fechaNacimiento + ", AlumnoLibro=" + alumnoLibros + ", grupos=" + grupos + '}';
    }
}
