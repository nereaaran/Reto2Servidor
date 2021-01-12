/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seguridad;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

/**
 * Clase que contiene el metodo de cifrado hash con el algoritmo SHA-512 que se
 * usa para cifrar la contraseña que se guarda en la base de datos.
 *
 * @author Nerea Aranguren
 */
public class CifradoHash {

    /**
     * Atributo estático y constante que guarda los loggers de la clase.
     */
    private static final Logger LOGGER = Logger.getLogger("seguridad.CifradoHash");

    /**
     * Metodo que mediante el MessageDigest cifra la contraseña del usuario.
     *
     * @param contraseña La contraseña del usuario.
     * @return La contraseña hasheada en hexadecimal.
     */
    public String cifrarTextoEnHash(String contraseña) {
        MessageDigest messageDigest;
        try {
            LOGGER.info("CifradoHash: Cifrando clave");

            messageDigest = MessageDigest.getInstance("SHA-512");
            byte dataBytes[] = contraseña.getBytes();
            messageDigest.update(dataBytes);
            byte resumen[] = messageDigest.digest();

            contraseña = new String(resumen);

        } catch (NoSuchAlgorithmException e) {
            LOGGER.severe(e.getMessage());
        }
        return contraseña;
    }
}
