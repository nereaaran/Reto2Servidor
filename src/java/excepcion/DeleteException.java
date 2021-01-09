/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excepcion;

/**
 * Clase que define la excepción que se usará en los métodos "remove".
 *
 * @author Cristina Milea
 */
public class DeleteException extends Exception {

    /**
     * Constructor vacio.
     */
    public DeleteException() {
    }

    /**
     * Constructor con el mensaje del error
     *
     * @param message el mensaje de error.
     */
    public DeleteException(String message) {
        super(message);
    }
}
