/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 * Clase de PK de GrupoLibro
 * @author Jonathan Viñan
 */
@Embeddable
class GrupoLibroId implements Serializable{
    
    private Integer grupoId;
    private Integer libroId;

    public Integer getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(Integer grupoId) {
        this.grupoId = grupoId;
    }

    public Integer getLibroId() {
        return libroId;
    }

    public void setLibroId(Integer libroId) {
        this.libroId = libroId;
    }
    
}
