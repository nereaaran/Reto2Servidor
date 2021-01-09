/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excepcion;

/**
 * Clase que define la excepción que se usará en los métodos "edit".
 *
 * @author Cristina Milea
 */
public class UpdateException extends Exception {

    /**
     * Constructor vacio.
     */
    public UpdateException() {
    }

    /**
     * Constructor con el mensaje del error
     *
     * @param message el mensaje de error.
     */
    public UpdateException(String message) {
        super(message);
    }
}
