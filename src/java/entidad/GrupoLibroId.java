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
 * Clase de PK de GrupoLibro
 *
 * @author Jonathan Viñan
 */
@Embeddable
public class GrupoLibroId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idGrupo;
    private Integer idLibro;

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Integer getIdLibro() {
        return idLibro;
    }

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
        return hash;
    }

    /**
     * Método que compara si un objeto es igual al objeto "GrupoLibroId".
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
        final GrupoLibroId other = (GrupoLibroId) obj;
        if (!Objects.equals(this.idGrupo, other.idGrupo)) {
            return false;
        }
        if (!Objects.equals(this.idLibro, other.idLibro)) {
            return false;
        }
        return true;
    }

    /**
     * Método que devuelve un String con los atributos del GrupoLibroId.
     *
     * @return un String con los atributos de la entidad.
     */
    @Override
    public String toString() {
        return "GrupoLibroId{" + "idGrupo=" + idGrupo + ", idLibro=" + idLibro + '}';
    }
}
