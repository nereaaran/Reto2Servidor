/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seguridad;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ResourceBundle;
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
     * Atributo que guarda la ruta de la clave publica del archivo de
     * propiedades.
     */
    private final static String PUBLIC_KEY_PATH = ResourceBundle.getBundle("/archivos.Paths").getString("ASIMETRIC_KEY_PUBLIC");
    /**
     * Atributo que guarda la ruta de la clave privada del archivo de
     * propiedades.
     */
    private final static String PRIVATE_KEY_PATH = ResourceBundle.getBundle("/archivos.Paths").getString("ASIMETRIC_KEY_PRIVATE");

    /**
     * Metodo que cifra la contraseña del usuario con una clave publica.
     *
     *
     * @param contraseña La contraseña del usuario.
     * @return Un string con la contraseña cifrada en hexadecimal.
     */
    public String cifrarConClavePublica(String contraseña) {
        byte[] encodedMessage = null;

        try {
            LOGGER.info("CifradoAsimetrico: Cifrando con clave publica");

            //byte fileKey[] = fileReader(filePath + RB.getString("ASIMETRIC_KEY_PUBLIC"));/////////////////////////////////
            byte fileKey[] = getFileKey(PUBLIC_KEY_PATH);

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(fileKey);
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
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

            byte fileKey[] = getFileKey(PRIVATE_KEY_PATH);

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec pKCS8EncodedKeySpec = new PKCS8EncodedKeySpec(fileKey);
            PrivateKey privateKey = keyFactory.generatePrivate(pKCS8EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
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
     *//*
    private byte[] fileReader(String path) {
        byte ret[] = null;
        File file = new File(path);
        try {
            LOGGER.info("CifradoAsimetrico: Leyendo archivo");

            System.out.println(path);////////////////////////////////////////////////////

            ret = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
        return ret;
    }*/
 /*
    public byte[] getFileKey(String path) {
        byte[] publicKeyBytes = null;
        try {
            LOGGER.info("CifradoAsimetrico: Leyendo archivo");

            InputStream inputStream = CifradoAsimetrico.class.getClassLoader().getResourceAsStream(path);
            publicKeyBytes = new byte[inputStream.available()];
            inputStream.read(publicKeyBytes);
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
        return publicKeyBytes;
    }*/

    /**
     * Se encarga de leer el fichero con la clave y devolverla como un byte de
     * arrays.
     *
     * @param keyFilePath El path de el archivo con la clave.
     * @return La clave en array de bytes.
     */
    public byte[] getFileKey(String keyFilePath) {
        byte[] keyBytes = null;
        try {
            LOGGER.info("CifradoAsimetrico: Leyendo archivo");

            InputStream inputStream = CifradoAsimetrico.class.getResourceAsStream(keyFilePath);///////////FUNCIONA con el path en un archivo de propiedades y coger el properties file
            //InputStream inputStream = CifradoAsimetrico.class.getResourceAsStream("/archivos/ComicSansAsimetricPrivate.key");/////Funciona con fichero en carpeta archivos
            //InputStream inputStream = CifradoAsimetrico.class.getResourceAsStream("ComicSansAsimetricPrivate.key");///////////////// FUNCIONA con el fichero en la carpeta seguridad
            keyBytes = new byte[inputStream.available()];
            inputStream.read(keyBytes);
        } catch (IOException ex) {
            LOGGER.severe(ex.getMessage());
        }
        return keyBytes;
    }
}
