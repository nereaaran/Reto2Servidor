/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excepcion;

/**
 * Clase que define la excepción que se usará en los métodos "find".
 *
 * @author Cristina Milea
 */
public class ReadException extends Exception {

    /**
     * Constructor vacio.
     */
    public ReadException() {
    }

    /**
     * Constructor con el mensaje del error
     *
     * @param message el mensaje de error.
     */
    public ReadException(String message) {
        super(message);
    }
}
