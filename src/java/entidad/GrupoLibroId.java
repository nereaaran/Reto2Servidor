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
public class GrupoLibroId implements Serializable{
    
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
    
}
