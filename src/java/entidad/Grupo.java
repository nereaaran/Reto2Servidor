/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jonathan
 * 
 */

//Queries para realizar opreciones en la base de datos
@NamedQueries({
    //Busca todos los libros y sus atributos ordenados en descendente
    @NamedQuery(
        name = "listarGrupos", query = "SELECT g FROM Grupo g ORDER BY g.nombre DESC"
    ),
    //Busca libros y sus atributos a partir del titulo
    @NamedQuery(
        name = "listarGrupoPorNombre", query = "SELECT g FROM Grupo g WHERE g.nombre LIKE :nombre"
    )
})

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

    @OneToMany(mappedBy = "grupo", fetch=FetchType.EAGER, cascade = (CascadeType.ALL))
    private Collection<GrupoLibro> grupoLibros;

    @ManyToMany(mappedBy = "grupos", fetch=FetchType.EAGER)
    private Collection<Alumno> alumnos;

    //@XmlTransient ///////////////////////////////////////////////////
    public Collection<GrupoLibro> getGrupoLibros() {
        return grupoLibros;
    }

    public void setGrupoLibros(Collection<GrupoLibro> grupoLibros) {
        this.grupoLibros = grupoLibros;
    }

    @XmlTransient
    public Collection<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(Collection<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    
}
