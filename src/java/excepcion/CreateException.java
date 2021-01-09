/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excepcion;

/**
 * Clase que define la excepción que se usará en los métodos "create".
 *
 * @author Cristina Milea
 */
public class CreateException extends Exception {

    /**
     * Constructor vacio.
     */
    public CreateException() {
    }

    /**
     * Constructor con el mensaje del error
     *
     * @param message el mensaje de error.
     */
    public CreateException(String message) {
        super(message);
    }
}
