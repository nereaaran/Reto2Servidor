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
 * Clase para guardar las relaciones entre el grupo y el libro
 * @author Joanthan Viñan
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
    /**
     * Obtiene el idGrupoLibro de GrupoLibro.
     * @return el idGrupoLibro de GrupoLibro.
     */
    public GrupoLibroId getIdGrupoLibro() {
        return idGrupoLibro;
    }
    /**
     * Establece el idGrupoLibro.
     * @param idGrupoLibro el idGrupoLibro.
     */
    public void setIdGrupoLibro(GrupoLibroId idGrupoLibro) {
        this.idGrupoLibro = idGrupoLibro;
    }  
    /**
     * Obtiene el grupo
     * @return el grupo
     */
    @XmlTransient
    public Grupo getGrupo() {
        return grupo;
    }
    /**
     * Establece el grupo
     * @param grupo
     */
    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
    /**
     * Estable el Libro
     * @return el libro
     */
    public Libro getLibro() {
        return libro;
    }

    /**
     * Obtine el libro
     * @param libro
     */
    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    /**
     * Establece la fecha de inicio
     * @return la fecha inicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Obtiene la fecha inicio
     * @param fechaInicio
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    /**
     * Establece la fecha final
     * @return la fecha final
     */
    public Date getFechaFin() {
        return fechaFin;
    }
    /**
     * Obtiene la fecha final
     * @param fechaFin
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    /**
     * Metodo que compara el codigo hash de dos objetos
     * @return un codigo hash del objeto
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.idGrupoLibro);
        hash = 47 * hash + Objects.hashCode(this.grupo);
        hash = 47 * hash + Objects.hashCode(this.libro);
        hash = 47 * hash + Objects.hashCode(this.fechaInicio);
        hash = 47 * hash + Objects.hashCode(this.fechaFin);
        return hash;
    }
    /**
     * Metodo que comprara si un objeto es igual al objeto GrupoLibro
     * @param obj cualquier tipo de objeto
     * @return un 'false' si los objetos no son iguales y un 'true' si lo sons
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
        final GrupoLibro other = (GrupoLibro) obj;
        if (!Objects.equals(this.idGrupoLibro, other.idGrupoLibro)) {
            return false;
        }
        if (!Objects.equals(this.grupo, other.grupo)) {
            return false;
        }
        if (!Objects.equals(this.libro, other.libro)) {
            return false;
        }
        if (!Objects.equals(this.fechaInicio, other.fechaInicio)) {
            return false;
        }
        if (!Objects.equals(this.fechaFin, other.fechaFin)) {
            return false;
        }
        return true;
    }
    
    /**
     * Método que devuelve un String con los atributos del grupo.
     * @return un String con los atributos de la entidad.
     */
    @Override
    public String toString() {
        return "GrupoLibro{" + "idGrupoLibro=" + idGrupoLibro + ", grupo=" + grupo + ", libro=" + libro + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + '}';
    }
}
