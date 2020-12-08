/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import javax.annotation.Generated;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.MERGE;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jonathan
 */
@Entity
@Table(name = "grupo", schema = "bibliotecadb")
@XmlRootElement
public class Grupo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idGrupo;
    @NotNull
    private String nombre;
    @NotNull
    private String descripcion;
    @NotNull
    private Integer numAlumno;

    /**
     *
     * @return idGrupo
     */
    public Integer getIdGrupo() {
        return idGrupo;
    }

    /**
     * Se muestra el nombre del grupo
     *
     * @param idGrupo
     */
    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    /**
     * Se devuelve eñ nombre del grupo
     *
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Se muestra el nombre del grupo
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Se devuelve la dscripcion
     *
     * @return description
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Se muestra el valor la descripcion
     *
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * De vuelve la cantidad
     *
     * @return numAlumno
     */
    public Integer getNumAlumno() {
        return numAlumno;
    }

    /**
     * Se muestra numAlumno del grupo
     *
     * @param numAlumno
     */
    public void setNumAlumno(Integer numAlumno) {
        this.numAlumno = numAlumno;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.idGrupo;
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.descripcion);
        hash = 37 * hash + this.numAlumno;
        return hash;
    }

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
        final Grupo other = (Grupo) obj;
        if (!Objects.equals(this.idGrupo, other.idGrupo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Grupo{" + "idGrupo=" + idGrupo + ", nombre=" + nombre + ", descripcion=" + descripcion + ", numAlumno=" + numAlumno + '}';
    }

    @ManyToOne
    private Profesor profesor;

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    @OneToMany(mappedBy = "grupo", cascade = ALL)
    private Collection<GrupoLibro> grupoLibro;

    @ManyToMany(mappedBy = "grupos")
    private Collection<Alumno> alumnos;

    public Collection<GrupoLibro> getGrupoLibro() {
        return grupoLibro;
    }

    public void setGrupoLibro(Collection<GrupoLibro> grupoLibro) {
        this.grupoLibro = grupoLibro;
    }

    public Collection<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(Collection<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

}
