/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seguridad;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Clase que contiene los metodos de cifrado y descifrado con clave privada que
 * se usan para las credenciales de la cuenta de mail.
 *
 * Usa el algoritmo AES, el modo CBC y el padding PKCS5Padding (128).
 *
 * @author Nerea Aranguren
 */
public class CifradoSimetrico {

    /**
     * Atributo estático y constante que guarda los loggers de la clase.
     */
    private static final Logger LOGGER = Logger.getLogger("seguridad.CifradoSimetrico");

    /**
     * Ruta absoluta del proyecto.
     */
    private static final String filePath = new File("").getAbsolutePath();

    /**
     * Variable que guarda el salt.
     */
    private static byte[] salt = "esta es la salt!".getBytes();

    /**
     * Metodo que cifra las credenciales del correo mediante una clave y un salt
     * y guarda el resultado en un fichero.
     *
     * @param email El email que se quiere cifrar.
     * @param contraseña La contraseña que se quiere cifrar.
     */
    public void cifrarTextoConClavePrivada(String email, String contraseña) {
        KeySpec keySpec = null;
        SecretKeyFactory secretKeyFactory = null;
        String clave = obtenerClavePrivada();
        try {
            LOGGER.info("CifradoSimetrico: Cifrando con clave privada");

            // Crea un SecretKey usando la clave + salt
            keySpec = new PBEKeySpec(clave.toCharArray(), salt, 65536, 128);
            secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] key = secretKeyFactory.generateSecret(keySpec).getEncoded();
            SecretKey privateKey = new SecretKeySpec(key, 0, key.length, "AES");

            // Crea un Cipher con el algoritmo que se va a usar y lo inicia
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            // Mensaje cifrado
            byte[] encodedEmail = cipher.doFinal(email.getBytes());
            byte[] encodedContraseña = cipher.doFinal(contraseña.getBytes());
            // Vector de inicializacion por modo CBC
            byte[] iv = cipher.getIV();

            // Guarda el mensaje codificado: IV (16 bytes) + Mensaje
            byte[] combinedEmail = concatenarArrays(iv, encodedEmail);
            byte[] combinedContraseña = concatenarArrays(iv, encodedContraseña);

            // Escribe los textos cifrados en distintos archivos.
            fileWriter(filePath + "/src/java/archivos/EmailCifrado.dat", combinedEmail);
            fileWriter(filePath + "/src/java/archivos/ContrasenaCifrada.dat", combinedContraseña);

        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    /**
     * Metodo que obtiene y descifra el email mediante una clave privada.
     *
     * @return El texto descifrado.
     */
    public String descifrarEmailConClavePrivada() {
        String emailDescifrado = null;
        String clave = obtenerClavePrivada();

        // Fichero leído
        byte[] fileContent = fileReader(filePath + "/src/java/archivos/EmailCifrado.dat");
        KeySpec keySpec = null;
        SecretKeyFactory secretKeyFactory = null;
        try {
            LOGGER.info("CifradoSimetrico: Descifrando email con clave privada");

            // Crea un SecretKey usando la clave + salt
            keySpec = new PBEKeySpec(clave.toCharArray(), salt, 65536, 128);
            secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] key = secretKeyFactory.generateSecret(keySpec).getEncoded();
            SecretKey privateKey = new SecretKeySpec(key, 0, key.length, "AES");

            // Crea un Cipher con el algoritmos que se va a usar
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivParam = new IvParameterSpec(Arrays.copyOfRange(fileContent, 0, 16));
            cipher.init(Cipher.DECRYPT_MODE, privateKey, ivParam);
            byte[] decodedMessage = cipher.doFinal(Arrays.copyOfRange(fileContent, 16, fileContent.length));
            emailDescifrado = new String(decodedMessage);
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            LOGGER.severe(e.getMessage());
        }
        return emailDescifrado;
    }

    /**
     * Metodo que obtiene y descifra la contraseña mediante una clave privada.
     *
     * @return El texto descifrado.
     */
    public String descifrarContraseñaConClavePrivada() {
        String contraseñaDescifrada = null;
        String clave = obtenerClavePrivada();

        // Fichero leído
        byte[] fileContent = fileReader(filePath + "/src/java/archivos/ContrasenaCifrada.dat");
        KeySpec keySpec = null;
        SecretKeyFactory secretKeyFactory = null;
        try {
            LOGGER.info("CifradoSimetrico: Descifrando contraseña con clave privada");

            // Crea un SecretKey usando la clave + salt
            keySpec = new PBEKeySpec(clave.toCharArray(), salt, 65536, 128);
            secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] key = secretKeyFactory.generateSecret(keySpec).getEncoded();
            SecretKey privateKey = new SecretKeySpec(key, 0, key.length, "AES");

            // Crea un Cipher con el algoritmos que se va a usar
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivParam = new IvParameterSpec(Arrays.copyOfRange(fileContent, 0, 16));
            cipher.init(Cipher.DECRYPT_MODE, privateKey, ivParam);
            byte[] decodedMessage = cipher.doFinal(Arrays.copyOfRange(fileContent, 16, fileContent.length));
            contraseñaDescifrada = new String(decodedMessage);
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            LOGGER.severe(e.getMessage());
        }
        return contraseñaDescifrada;
    }

    /**
     * Metodo que obtiene la clave privada de un archivo de propiedades.
     *
     * @return La clave.
     */
    private String obtenerClavePrivada() {
        LOGGER.info("CifradoSimetrico: obteniendo clave privada");

        ResourceBundle RB = ResourceBundle.getBundle("archivos.Private");
        String clave = RB.getString("CLAVE");
        return clave;
    }

    /**
     * Metodo qie concatena dos arrays.
     *
     * @param array1 Primer array.
     * @param array2 Segundo array.
     * @return La concatenacion de los dos arrays
     */
    private byte[] concatenarArrays(byte[] array1, byte[] array2) {
        LOGGER.info("CifradoSimetrico: Concatenando dos arrays");

        byte[] concatenacion = new byte[array1.length + array2.length];
        System.arraycopy(array1, 0, concatenacion, 0, array1.length);
        System.arraycopy(array2, 0, concatenacion, array1.length, array2.length);
        return concatenacion;
    }

    /**
     * Metodo que escribe en un fichero.
     *
     * @param path Path del fichero.
     * @param text Texto a escibir.
     */
    private void fileWriter(String path, byte[] text) {
        try (FileOutputStream fos = new FileOutputStream(path)) {
            LOGGER.info("CifradoSimetrico: Escribiendo archivo");

            fos.write(text);
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    /**
     * Metodo que devuelve el contenido de un fichero.
     *
     * @param path Path del fichero.
     * @return El texto del fichero.
     */
    private byte[] fileReader(String path) {
        byte contenido[] = null;
        File file = new File(path);
        try {
            LOGGER.info("CifradoSimetrico: Leyendo archivo");

            contenido = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
        return contenido;
    }
}
