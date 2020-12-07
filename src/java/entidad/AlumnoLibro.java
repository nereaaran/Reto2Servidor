/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

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
    private AlumnoLibro idAlumnoLibro;
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
    public AlumnoLibro getIdAlumnoLibro() {
        return idAlumnoLibro;
    }

    /**
     * Establece el idAlumnoLibro de AlumnoLibro.
     *
     * @param idAlumnoLibro El idAlumnoLibro de AlumnoLibro.
     */
    public void setId(AlumnoLibro idAlumnoLibro) {
        this.idAlumnoLibro = idAlumnoLibro;
    }

    /**
     * Obtiene el alumno.
     *
     * @return El alumno.
     */
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

}
