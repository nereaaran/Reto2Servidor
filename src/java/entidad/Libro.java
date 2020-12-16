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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Clase entidad de Libro que contiene sus atributos.
 *
 * @author Nerea Aranguren
 */

//Coleccion de queries para realizar operaciones en la base de datos.
@NamedQueries({
    //Busca todos los libros y sus atributos ordenados en descendente
    @NamedQuery(
        name = "buscarTodosLosLibros", query = "SELECT l FROM Libro l ORDER BY l.titulo ASC"
    ),
    //Busca libros y sus atributos a partir del titulo
    @NamedQuery(
        name = "buscarLibrosPorTitulo", query = "SELECT l FROM Libro l WHERE l.titulo LIKE CONCAT('%',:titulo,'%')"
    ),
    //Busca libros y algunos de sus atributos a partir del autor
    @NamedQuery(
        name = "buscarLibrosPorAutor", query = "SELECT l FROM Libro l WHERE l.autor LIKE CONCAT('%',:autor,'%')"
    )
})

@Entity
@Table(name = "libro", schema = "bibliotecadb")
@XmlRootElement
public class Libro implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Id para el libro.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idLibro;
    /**
     * Titulo del libro.
     */
    @NotNull
    private String titulo;
    /**
     * Autora del libro.
     */
    @NotNull
    private String autor;
    /**
     * Editorial del libro.
     */
    @NotNull
    private String editorial;
    /**
     * ISBN del libro.
     */
    @NotNull
    private Long isbn;
    /**
     * Genero literario del libro.
     */
    @NotNull
    private String genero;
    /**
     * Cantidad total que hay del libro.
     */
    @NotNull
    private Integer cantidadTotal;
    /**
     * Cantidad disponible que hay del libro.
     */
    @NotNull
    private Integer cantidadDisponible;
    /**
     * El libro se puede descargar.
     */
    @NotNull
    private boolean descargable;
    /**
     * Enlace de descarga del libro.
     */
    private String linkDescarga;

    /**
     * Relacion 1:N de la entidad Libro con alumnoLibro.
     */
    @OneToMany(mappedBy = "libro", fetch = FetchType.EAGER, cascade = (CascadeType.ALL))
    private Collection<AlumnoLibro> alumnoLibros;

    /**
     * Relacion 1:N de la entidad Libro con LibroGrupo.
     */
    @OneToMany(mappedBy = "libro", fetch = FetchType.EAGER, cascade = (CascadeType.ALL))
    private Collection<GrupoLibro> grupoLibro;

    /**
     * Relacion 1:N de la entidad Libro con Bibliotecario.
     */
    @ManyToOne
    private Bibliotecario bibliotecario;

    /**
     * Obtiene el id del libro.
     *
     * @return El valor del id del Libro.
     */
    public Integer getIdLibro() {
        return idLibro;
    }

    /**
     * Establece el id para el Libro.
     *
     * @param idLibro El valor del id del libro.
     */
    public void setId(Integer idLibro) {
        this.idLibro = idLibro;
    }

    /**
     * Obtiene el titulo del libro.
     *
     * @return El titulo del libro.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Establece el titulo del libro.
     *
     * @param titulo El titulo del libro.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtiene el autor del libro.
     *
     * @return El autor del libro.
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Establece el autor del libro.
     *
     * @param autor El autor del libro.
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * Obtiene la editorial del libro.
     *
     * @return La editorial del libro.
     */
    public String getEditorial() {
        return editorial;
    }

    /**
     * Establece la editoriasl del libro.
     *
     * @param editorial La editorial del libro.
     */
    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    /**
     * Obtiene el isbn del libro.
     *
     * @return El isbn del libro.
     */
    public Long getIsbn() {
        return isbn;
    }

    /**
     * Establece el isbn del libro.
     *
     * @param isbn El isbn del libro.
     */
    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    /**
     * Obtiene el genero literario del libro.
     *
     * @return El genero literario del libro.
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Establece el genero literario del libro.
     *
     * @param genero El genero literario del libro.
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * Obtiene la cantidad total del libro.
     *
     * @return La cantidad total del libro.
     */
    public Integer getCantidadTotal() {
        return cantidadTotal;
    }

    /**
     * Establece la cantidad total del libro.
     *
     * @param cantidadTotal La cantidad total del libro.
     */
    public void setCantidadTotal(Integer cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    /**
     * Obtiene la cantidad disponible del libro.
     *
     * @return La cantidad disponible del libro.
     */
    public Integer getCantidadDisponible() {
        return cantidadDisponible;
    }

    /**
     * Establece la cantidad disponible del libro.
     *
     * @param cantidadDisponible La cantidad disponible del libro.
     */
    public void setCantidadDisponible(Integer cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    /**
     * Obtiene si el libro es descargable.
     *
     * @return True si el libro se puede descargar.
     */
    public boolean isDescargable() {
        return descargable;
    }

    /**
     * Establece si el libro es descargable.
     *
     * @param descargable Si el libro es descargable.
     */
    public void setDescargable(boolean descargable) {
        this.descargable = descargable;
    }

    /**
     * Obtiene enlace de descarga del libro.
     *
     * @return Enlace de descarga del libro.
     */
    public String getLinkDescarga() {
        return linkDescarga;
    }

    /**
     * Establece enlace de descarga del libro.
     *
     * @param linkDescarga Enlace de descarga del libro.
     */
    public void setLinkDescarga(String linkDescarga) {
        this.linkDescarga = linkDescarga;
    }

    /**
     * Obtiene una coleccion de alumnoLibro.
     *
     * @return Coleccion de alumnoLibro.
     */
    @XmlTransient
    public Collection<AlumnoLibro> getAlumnoLibros() {
        return alumnoLibros;
    }

    /**
     * Establece una coleccion de alumnoLibro.
     *
     * @param alumnoLibros una coleccion de alumnoLibro.
     */
    public void setAlumnoLibros(Collection<AlumnoLibro> alumnoLibros) {
        this.alumnoLibros = alumnoLibros;
    }

    /**
     * Obtiene una coleccion de grupoLibro.
     *
     * @return Coleccion de grupoLibro.
     */
    @XmlTransient
    public Collection<GrupoLibro> getGrupoLibro() {
        return grupoLibro;
    }

    /**
     * Establece una coleccion de grupoLibro.
     *
     * @param grupoLibro una coleccion de grupoLibro.
     */
    public void setGrupoLibro(Collection<GrupoLibro> grupoLibro) {
        this.grupoLibro = grupoLibro;
    }

    /**
     * Obtiene el bibliotecario.
     *
     * @return El bibliotecario.
     */
    public Bibliotecario getBibliotecario() {
        return bibliotecario;
    }

    /**
     * Establece el bibliotecario.
     *
     * @param bibliotecario El bibliotecario.
     */
    public void setBibliotecario(Bibliotecario bibliotecario) {
        this.bibliotecario = bibliotecario;
    }

    /**
     * Implementacion del metodo hashCode para la entidad.
     *
     * @return Valor integer del hashcode del objeto.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLibro != null ? idLibro.hashCode() : 0);
        return hash;
    }

    /**
     * Metodo que comprueba el idLibro de dos entidades de libro por igualdad.
     *
     * @param obj El objeto a comparar.
     * @return True si los objetos son iguales.
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
        final Libro other = (Libro) obj;
        if (this.descargable != other.descargable) {
            return false;
        }
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        if (!Objects.equals(this.autor, other.autor)) {
            return false;
        }
        if (!Objects.equals(this.editorial, other.editorial)) {
            return false;
        }
        if (!Objects.equals(this.genero, other.genero)) {
            return false;
        }
        if (!Objects.equals(this.linkDescarga, other.linkDescarga)) {
            return false;
        }
        if (!Objects.equals(this.idLibro, other.idLibro)) {
            return false;
        }
        if (!Objects.equals(this.isbn, other.isbn)) {
            return false;
        }
        if (!Objects.equals(this.cantidadTotal, other.cantidadTotal)) {
            return false;
        }
        if (!Objects.equals(this.cantidadDisponible, other.cantidadDisponible)) {
            return false;
        }
        if (!Objects.equals(this.alumnoLibros, other.alumnoLibros)) {
            return false;
        }
        if (!Objects.equals(this.grupoLibro, other.grupoLibro)) {
            return false;
        }
        if (!Objects.equals(this.bibliotecario, other.bibliotecario)) {
            return false;
        }
        return true;
    }

    /**
     * Metodo que devuelve un string con los datos del libro.
     *
     * @return Un string del objeto libro.
     */
    @Override
    public String toString() {
        return "Libro{" + "idLibro=" + idLibro + ", titulo=" + titulo + ", autor=" + autor + ", editorial=" + editorial + ", isbn=" + isbn + ", genero=" + genero + ", cantidadTotal=" + cantidadTotal + ", cantidadDisponible=" + cantidadDisponible + ", descargable=" + descargable + ", linkDescarga=" + linkDescarga + ", alumnoLibro=" + alumnoLibros + ", grupoLibro=" + grupoLibro + ", bibliotecario=" + bibliotecario + '}';
    }

}
