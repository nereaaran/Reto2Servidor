/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seguridad;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Clase que contiene el metodo de cifrado hash con el algoritmo SHA-512 que se
 * usa para cifrar la contraseña que se guarda en la base de datos.
 *
 * @author Nerea Aranguren
 */
public class CifradoHash {

    /**
     * Metodo que mediante el MessageDigest cifra la contraseña del usuario.
     *
     * @param texto La contraseña del usuario.
     * @return La contraseña hasheada en hexadecimal.
     */
    public String cifrarTextoEnHash(String contraseña) {
        MessageDigest messageDigest;
        String contraseñaHasheada = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-512");
            byte dataBytes[] = contraseña.getBytes();
            messageDigest.update(dataBytes);
            byte resumen[] = messageDigest.digest();

            contraseñaHasheada = Hexadecimal(resumen);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return contraseñaHasheada;
    }

    /**
     * Metodo que convierte el hash digerido en ua cadena hexadecimal.
     *
     * @param resumen La contraseña digerida.
     * @return La contraseña hexadecimal.
     */
    static String Hexadecimal(byte[] resumen) {
        String HEX = "";
        for (int i = 0; i < resumen.length; i++) {
            String h = Integer.toHexString(resumen[i] & 0xFF);
            if (h.length() == 1) {
                HEX += "0";
            }
            HEX += h;
        }
        return HEX.toUpperCase();
    }
}
