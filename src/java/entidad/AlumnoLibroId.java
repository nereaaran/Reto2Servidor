/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;

/**
 * Clase para asignar id a la entidad AlumnoLibro.
 *
 * @author Nerea Aranguren
 */
@Embeddable
public class AlumnoLibroId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idUsuario;
    private Integer idLibro;

    /**
     * Obtiene el idUsuario.
     *
     * @return El idUsuario.
     */
    public Integer getIdUsuario() {
        return idUsuario;
    }

    /**
     * Establece el idUsuario.
     *
     * @param idUsuario El idUsuario.
     */
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Obtiene el idLibro.
     *
     * @return El idLibro.
     */
    public Integer getIdLibro() {
        return idLibro;
    }

    /**
     * Establece el idLibro.
     *
     * @param idLibro El idLibro.
     */
    public void setIdLibro(Integer idLibro) {
        this.idLibro = idLibro;
    }

    /**
     * Método que compara el código hash de dos objetos.
     *
     * @return el código hash del objeto.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.idUsuario);
        hash = 71 * hash + Objects.hashCode(this.idLibro);
        return hash;
    }

    /**
     * Método que compara si un objeto es igual al objeto "AlumnoLibroId".
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
        final AlumnoLibroId other = (AlumnoLibroId) obj;
        if (!Objects.equals(this.idUsuario, other.idUsuario)) {
            return false;
        }
        if (!Objects.equals(this.idLibro, other.idLibro)) {
            return false;
        }
        return true;
    }

    /**
     * Método que devuelve un String con los atributos del AlumnoLibroId.
     *
     * @return un String con los atributos de la entidad.
     */
    @Override
    public String toString() {
        return "AlumnoLibroId{" + "idUsuario=" + idUsuario + ", idLibro=" + idLibro + '}';
    }
}
