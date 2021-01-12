/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seguridad;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Clase que contiene los metodos para cifrado y descifrado asimetrico que se
 * usa para cuando el usuario haga login o sign up y la contraseña viaje segura
 * por la red.
 *
 * Usa el algoritmo RSA, el modo ECB y el padding PKCS1Padding.
 *
 * @author Nerea Aranguren
 */
public class CifradoAsimetrico {

    /**
     * Atributo estático y constante que guarda los loggers de la clase.
     */
    private static final Logger LOGGER = Logger.getLogger("seguridad.CifradoAsimetrico");

    /**
     * Ruta absoluta del proyecto.
     */
    private static final String filePath = new File("").getAbsolutePath();

    /**
     * Metodo que cifra la contraseña del usuario con una clave publica.
     *
     * @param contraseña La contraseña del usuario.
     * @return Un string con la contraseña cifrada en hexadecimal.
     */
    public String cifrarConClavePublica(String contraseña) {
        byte[] encodedMessage = null;
        //byte[] contraseñaBytes = contraseña.getBytes(charset);

        try {
            LOGGER.info("CifradoAsimetrico: Cifrando con clave publica");

            // Carga la clave pública.
            byte fileKey[] = fileReader(filePath + "/src/java/archivos/ComicSansAsimetricPublic.key");

            // Obtiene una instancia de KeyFactory, algoritmo RSA.
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            // Crea un nuevo X509EncodedKeySpec del fileKey.
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(fileKey);
            // Genera la public key con el keyFactory.
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);

            // Obtiene una instancia del Cipher "RSA/ECB/PKCS1Padding".
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            // Inicia el cipher (ENCRYPT_MODE)
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            // El método doFinal cifra el mensaje
            encodedMessage = cipher.doFinal(contraseña.getBytes());
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            LOGGER.severe(e.getMessage());
        }
        return byteToHexadecimal(encodedMessage);
    }

    /**
     * Metodo que mediante una clave privada (generada por la clase
     * "GeneradorClaves") descifra la contraseña del usuario que llega cifrada
     * mediante una clave publlica.
     *
     * @param contraseñaHexadecimal La contraseña cifrada en hexadecimal.
     * @return Un string de la contraseña descifrada.
     */
    public String descifrarConClavePrivada(String contraseñaHexadecimal) {
        byte[] decodedMessage = null;
        try {
            LOGGER.info("CifradoAsimetrico: Descifrando con clave privada");

            // Cargamos la clave privada
            byte fileKey[] = fileReader(filePath + "/src/java/archivos/ComicSansAsimetricPrivate.key");

            // Obtenemos una instancia de KeyFactory, algoritmo RSA
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            // Creamos un nuevo PKCS8EncodedKeySpec del fileKey
            PKCS8EncodedKeySpec pKCS8EncodedKeySpec = new PKCS8EncodedKeySpec(fileKey);
            // Generamos la public key con el keyFactory
            PrivateKey privateKey = keyFactory.generatePrivate(pKCS8EncodedKeySpec);
            // Obtenemos una instancia del Cipher "RSA/ECB/PKCS1Padding"
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            // Iniciamos el cipher (DECRYPT_MODE)
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            // El método doFinal nos descifra el mensaje
            decodedMessage = cipher.doFinal(HexadecimalToByte(contraseñaHexadecimal));
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            LOGGER.severe(e.getMessage());
        }
        return new String(decodedMessage);
    }

    /**
     * Metodo que convierte un array de bytes en un string hexadecimal.
     *
     * @param bytes Array de bytes que llega.
     * @return Un String con valor hexadecimal.
     */
    private String byteToHexadecimal(byte[] bytes) {
        LOGGER.info("CifradoAsimetrico: Convirtiendo bytes a hexadecimal");

        String HEX = "";
        for (int i = 0; i < bytes.length; i++) {
            String h = Integer.toHexString(bytes[i] & 0xFF);
            if (h.length() == 1) {
                HEX += "0";
            }
            HEX += h;
        }
        return HEX.toUpperCase();
    }

    /**
     * Metodo que convierte un string con valor hexadecimal a un array de bytes.
     *
     * @param hexadecimal El string con valor hexadecimal.
     * @return El array de bytes.
     */
    private byte[] HexadecimalToByte(String hexadecimal) {
        LOGGER.info("CifradoAsimetrico: Convirtiendo hexadecimal a bytes");

        int length = hexadecimal.length();
        byte[] data = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexadecimal.charAt(i), 16) << 4)
                    + Character.digit(hexadecimal.charAt(i + 1), 16));
        }
        return data;
    }

    /**
     * Metodo que devuelve el contenido de un fichero.
     *
     * @param path Path del fichero.
     * @return El texto del fichero.
     */
    private byte[] fileReader(String path) {
        byte ret[] = null;
        File file = new File(path);
        try {
            LOGGER.info("CifradoAsimetrico: Leyendo archivo");

            ret = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
        return ret;
    }
}