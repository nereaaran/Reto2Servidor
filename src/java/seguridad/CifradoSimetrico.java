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
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
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
 * Clase que contiene los metodos de cifrado con clave privada que se usan para
 * las credenciales de la cuenta de mail.
 *
 * Usa el algoritmo AES, el modo CBC y el padding PKCS5Padding (128).
 *
 * @author Nerea Aranguren
 */
public class CifradoSimetrico {

    private static byte[] salt = "esta es la salt!".getBytes();

    /**
     * Metodo que cifra mediante una clave y un salt y guarda el resultado en un
     * fichero.
     *
     * @param clave La clave para cifrar el texto.
     * @param texto El mensaje que se quiere cifrar.
     * @return El texto cifrado.
     */
    public String cifrarTextoConClavePrivada(String clave, String texto) {
        String textoCifrado = null;
        KeySpec keySpec = null;
        SecretKeyFactory secretKeyFactory = null;
        try {

            // Crea un SecretKey usando la clave + salt
            keySpec = new PBEKeySpec(clave.toCharArray(), salt, 65536, 128); // AES-128
            secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] key = secretKeyFactory.generateSecret(keySpec).getEncoded();
            SecretKey privateKey = new SecretKeySpec(key, 0, key.length, "AES");

            // Crea un Cipher con el algoritmo que se va a usar y lo inicia
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            // Mensaje cifrado
            byte[] encodedMessage = cipher.doFinal(texto.getBytes());
            // Vector de inicializacion por modo CBC
            byte[] iv = cipher.getIV();

            // Guarda el mensaje codificado: IV (16 bytes) + Mensaje
            byte[] combined = concatenarArrays(iv, encodedMessage);

            fileWriter("c:\\trastero\\EjemploAES.dat", combined);/////////////////////////////////////

            textoCifrado = new String(encodedMessage);

        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        return textoCifrado;
    }

    /**
     * Metodo que descifra el texto mediante una clave privada.
     * @param clave La clave privada.
     * @return El texto descifrado.
     */
    private String descifrarTextoConClavePrivada(String clave) {
        String textoDescifrado = null;

        // Fichero leído
        byte[] fileContent = fileReader("c:\\trastero\\EjemploAES.dat");////////////////////////
        KeySpec keySpec = null;
        SecretKeyFactory secretKeyFactory = null;
        try {
            // Crea un SecretKey usando la clave + salt
            keySpec = new PBEKeySpec(clave.toCharArray(), salt, 65536, 128); // AES-128
            secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] key = secretKeyFactory.generateSecret(keySpec).getEncoded();
            SecretKey privateKey = new SecretKeySpec(key, 0, key.length, "AES");

            // Crea un Cipher con el algoritmos que se va a usar
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivParam = new IvParameterSpec(Arrays.copyOfRange(fileContent, 0, 16)); // La IV est� aqu�
            cipher.init(Cipher.DECRYPT_MODE, privateKey, ivParam);
            byte[] decodedMessage = cipher.doFinal(Arrays.copyOfRange(fileContent, 16, fileContent.length));
            textoDescifrado = new String(decodedMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return textoDescifrado;
    }

    /**
     * Metodo qie concatena dos arrays.
     *
     * @param array1 Primer array.
     * @param array2 Segundo array.
     * @return La concatenacion de los dos arrays
     */
    private byte[] concatenarArrays(byte[] array1, byte[] array2) {
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
            fos.write(text);
        } catch (IOException e) {
            e.printStackTrace();
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
            contenido = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contenido;
    }
}
