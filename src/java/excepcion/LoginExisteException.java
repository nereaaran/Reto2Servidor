/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excepcion;

/**
 * Clase que define la excepción que se usará cuando el login exista.
 *
 * @author Cristina Milea.
 */
public class LoginExisteException extends Exception {

    /**
     * Constructor vacio.
     */
    public LoginExisteException() {
    }

    /**
     * Constructor con el mensaje del error
     *
     * @param message el mensaje de error.
     */
    public LoginExisteException(String message) {
        super(message);
    }
}
