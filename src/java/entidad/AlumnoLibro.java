/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Clase para guardar las relaciones entre alumno y libro y sus atributos.
 *
 * @author Nerea Aranguren
 */

@Entity
@Table(name = "alumno_libro", schema = "bibliotecadb")
@XmlRootElement
public class AlumnoLibro implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private AlumnoLibroId idAlumnoLibro;
    @MapsId("idUsuario")
    @ManyToOne
    private Alumno alumno;
    @MapsId("idLibro")
    @ManyToOne
    private Libro libro;

    @Temporal(TemporalType.DATE)
    private Date fechaAsignado;
    @Temporal(TemporalType.DATE)
    private Date fechaLimite;

    /**
     * Obtiene el idAlumnoLibro de AlumnoLibro.
     *
     * @return El idAlumnoLibro de AlumnoLibro.
     */
    public AlumnoLibroId getIdAlumnoLibro() {
        return idAlumnoLibro;
    }

    /**
     * Establece el idAlumnoLibro de AlumnoLibro.
     *
     * @param idAlumnoLibro El idAlumnoLibro de AlumnoLibro.
     */
    public void setId(AlumnoLibroId idAlumnoLibro) {
        this.idAlumnoLibro = idAlumnoLibro;
    }

    /**
     * Obtiene el alumno.
     *
     * @return El alumno.
     */
    
    @XmlTransient
    public Alumno getAlumno() {
        return alumno;
    }

    /**
     * Establece el alumno.
     *
     * @param alumno El alumno.
     */
    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    /**
     * Obtiene el libro.
     *
     * @return El libro.
     */
    
    @XmlTransient
    public Libro getLibro() {
        return libro;
    }

    /**
     * Establece el libro.
     *
     * @param libro El libro.
     */
    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    /**
     * Obtiene la fecha asignada.
     *
     * @return La fecha asignada.
     */
    public Date getFechaAsignado() {
        return fechaAsignado;
    }

    /**
     * Establece la fecha asignada.
     *
     * @param fechaAsignado La fecha asignada.
     */
    public void setFechaAsignado(Date fechaAsignado) {
        this.fechaAsignado = fechaAsignado;
    }

    /**
     * Obtiene la fecha limite.
     *
     * @return La fecha limite.
     */
    public Date getFechaLimite() {
        return fechaLimite;
    }

    /**
     * Establece la fecha limite.
     *
     * @param fechaLimite La fecha limite.
     */
    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    /**
     * Método que compara el código hash de dos objetos.
     *
     * @return un código hash del objeto.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.idAlumnoLibro);
        hash = 59 * hash + Objects.hashCode(this.alumno);
        hash = 59 * hash + Objects.hashCode(this.libro);
        hash = 59 * hash + Objects.hashCode(this.fechaAsignado);
        hash = 59 * hash + Objects.hashCode(this.fechaLimite);
        return hash;
    }

    /**
     * Método que compara si un objeto es igual al objeto "AlumnoLibro".
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
        final AlumnoLibro other = (AlumnoLibro) obj;
        if (!Objects.equals(this.idAlumnoLibro, other.idAlumnoLibro)) {
            return false;
        }
        if (!Objects.equals(this.alumno, other.alumno)) {
            return false;
        }
        if (!Objects.equals(this.libro, other.libro)) {
            return false;
        }
        if (!Objects.equals(this.fechaAsignado, other.fechaAsignado)) {
            return false;
        }
        if (!Objects.equals(this.fechaLimite, other.fechaLimite)) {
            return false;
        }
        return true;
    }

    /**
     * Método que devuelve un String con los atributos del AlumnoLibro.
     *
     * @return el String con los atributos de la entidad.
     */
    @Override
    public String toString() {
        return "AlumnoLibro{" + "idAlumnoLibro=" + idAlumnoLibro + ", alumno=" + alumno + ", libro=" + libro + ", fechaAsignado=" + fechaAsignado + ", fechaLimite=" + fechaLimite + '}';
    }
}
