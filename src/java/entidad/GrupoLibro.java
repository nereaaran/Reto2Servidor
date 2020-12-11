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
 * Clase grupo libro
 * @author Joanthan
 */
@Entity
@Table (name = "grupo_libro", schema="bibliotecadb")
@XmlRootElement
public class GrupoLibro implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private GrupoLibroId idGrupoLibro;
    @MapsId("idGrupo")
    @ManyToOne
    private Grupo grupo;
    @MapsId("idLibro")
    @ManyToOne
    private Libro libro;
    
    @Temporal (TemporalType.DATE)
    private Date fechaInicio;
    @Temporal (TemporalType.DATE)
    private Date fechaFin;

    public GrupoLibroId getIdGrupoLibro() {
        return idGrupoLibro;
    }

    public void setIdGrupoLibro(GrupoLibroId idGrupoLibro) {
        this.idGrupoLibro = idGrupoLibro;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * Método que devuelve un String con los atributos del grupo.
     *
     * @return un String con los atributos de la entidad.
     */
    @Override
    public String toString() {
        return "GrupoLibro{" + "idGrupoLibro=" + idGrupoLibro + ", grupo=" + grupo + ", libro=" + libro + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + '}';
    }
}
