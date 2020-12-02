/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Clase que define los atributos y los métodos de la entidad "Usuario".
 *
 * @author Cristina Milea
 */
@Entity
@Table(name = "usuario", schema = "bibliotecadb")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Atributo que guarda el id del usuario. Es la clave primaria de la tabla
     * "usuario".
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idUsuario;
    /**
     * Login del usuario.
     */
    private String login;
    /**
     * Email del usuario.
     */
    private String email;
    /**
     * Nombre completo del usuario.
     */
    private String fullName;
    /**
     * Estado del usuario, que puede ser ENABLED o DISABLED.
     */
    @Enumerated(EnumType.STRING) //ORDINAL crea una columna de tipo int y STRING crea una columna de tipo varchar.
    private UserStatus status;
    /**
     * Privilegio del usuario, que puede ser ADMIN o USER.
     */
    @Enumerated(EnumType.STRING)
    private UserPrivilege privilege;
    /**
     * Contraseña del usuario.
     */
    private String password;
    /**
     * Fecha del último acceso del usuario.
     */
    private Timestamp lastAccess;
    /**
     * Fecha de la última vez en la que se ha modificado la contraseña del
     * usuario.
     */
    private Timestamp lastPasswordChange;

    /**
     * Constructor de la clase "Usuario".
     *
     * @param idUsuario el id del usuario.
     * @param login el login del usuario.
     * @param email el email del usuario.
     * @param fullName el nombre completo del usuario.
     * @param status el estado del usuario.
     * @param privilege el privilegio del usuario.
     * @param password la contraseña del usuario.
     * @param lastAccess el último acceso del usuario.
     * @param lastPasswordChange el último cambio de contraseña del usuario.
     */
    public Usuario(Integer idUsuario, String login, String email, String fullName, UserStatus status, UserPrivilege privilege, String password, Timestamp lastAccess, Timestamp lastPasswordChange) {
        this.idUsuario = idUsuario;
        this.login = login;
        this.email = email;
        this.fullName = fullName;
        this.status = status;
        this.privilege = privilege;
        this.password = password;
        this.lastAccess = lastAccess;
        this.lastPasswordChange = lastPasswordChange;
    }

    /**
     * Método que muestra el id del usuario.
     *
     * @return el id que se va a mostrar.
     */
    public Integer getIdUsuario() {
        return idUsuario;
    }

    /**
     * Método que guarda el id del usuario.
     *
     * @param idUsuario el id que se va a guardar.
     */
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Método que muestra el login del usuario.
     *
     * @return el login que se va a mostrar.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Método que guarda el login del usuario.
     *
     * @param login el login que se va a guardar.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Método que muestra el email del usuario.
     *
     * @return el email que se va a mostrar.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Método que guarda el email del usuario.
     *
     * @param email el email que se va a guardar.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Método que muestra el nombre completo del usuario.
     *
     * @return el nombre completo que se va a mostrar.
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Método que guarda el nombre completo del usuario.
     *
     * @param fullName el nombre completo que se va a guardar.
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Método que muestra el estado del usuario.
     *
     * @return el estado que se va a mostrar.
     */
    public UserStatus getStatus() {
        return status;
    }

    /**
     * Método que guarda el estado del usuario.
     *
     * @param status el estado que se va a guardar.
     */
    public void setStatus(UserStatus status) {
        this.status = status;
    }

    /**
     * Método que muestra el privilegio del usuario.
     *
     * @return el privilegio que se va a mostrar.
     */
    public UserPrivilege getPrivilege() {
        return privilege;
    }

    /**
     * Método que guarda el privilegio del usuario.
     *
     * @param privilege el privilegio que se va a guardar.
     */
    public void setPrivilege(UserPrivilege privilege) {
        this.privilege = privilege;
    }

    /**
     * Método que muestra la contraseña del usuario.
     *
     * @return la contraseña que se va a mostrar.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Método que guarda la contraseña del usuario.
     *
     * @param password la contraseña que se va a guardar.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Método que muestra el último acceso del usuario.
     *
     * @return el último acceso que se va a mostrar.
     */
    public Timestamp getLastAccess() {
        return lastAccess;
    }

    /**
     * Método que guarda el último acceso del usuario.
     *
     * @param lastAccess el último acceso que se va a guardar.
     */
    public void setLastAccess(Timestamp lastAccess) {
        this.lastAccess = lastAccess;
    }

    /**
     * Método que muestra el último cambio de contraseña del usuario.
     *
     * @return la fecha del último cambio de contraseña que se va a mostrar.
     */
    public Timestamp getLastPasswordChange() {
        return lastPasswordChange;
    }

    /**
     * Método que guarda el último cambio de contraseña del usuario.
     *
     * @param lastPasswordChange la fecha del último cambio de contraseña que se
     * va a guardar.
     */
    public void setLastPasswordChange(Timestamp lastPasswordChange) {
        this.lastPasswordChange = lastPasswordChange;
    }

    /**
     * Método que compara el código hash de dos objetos.
     *
     * @return el código hash del objeto.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    /**
     * Método que compara si un objeto es igual al objeto "Usuario".
     *
     * @param object cualquier tipo de objeto.
     * @return un "false" si los objetos noson iguales y un "true" si lo son.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    /**
     * Método que devuelve un String con el id del usuario.
     *
     * @return el String con el id del usuario.
     */
    @Override
    public String toString() {
        return "entidad.Usuario[ idUsuario=" + idUsuario + " ]";
    }

}
