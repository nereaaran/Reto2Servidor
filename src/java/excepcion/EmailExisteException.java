/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excepcion;

/**
 * Clase que define la excepción que se usará cuando el email exista.
 *
 * @author Cristina Milea.
 */
public class EmailExisteException extends Exception {

    /**
     * Constructor vacio.
     */
    public EmailExisteException() {
    }

    /**
     * Constructor con el mensaje del error
     *
     * @param message el mensaje de error.
     */
    public EmailExisteException(String message) {
        super(message);
    }
}
