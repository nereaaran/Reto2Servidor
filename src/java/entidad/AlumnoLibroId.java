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

}
