/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 * Clase para asignar id a la entidad AlumnoLibro.
 *
 * @author Nerea Aranguren
 */

@Embeddable
public class AlumnoLibroId implements Serializable {

    private Integer alumnoId;
    private Integer libroId;

    /**
     * Obtiene el alumnoId.
     *
     * @return El alumnoId.
     */
    public Integer getAlumnoId() {
        return alumnoId;
    }

    /**
     * Establece el alumnoId.
     *
     * @param alumnoId El alumnoId.
     */
    public void setAlumnoId(Integer alumnoId) {
        this.alumnoId = alumnoId;
    }

    /**
     * Obtiene el libroId.
     *
     * @return El libroId.
     */
    public Integer getLibroId() {
        return libroId;
    }

    /**
     * Establece el libroId.
     *
     * @param libroId El libroId.
     */
    public void setLibroId(Integer libroId) {
        this.libroId = libroId;
    }

}
