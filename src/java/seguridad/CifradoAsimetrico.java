/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seguridad;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

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

    /////////////////ESTE METODO VA EN EL LADO CLIENTE
    /**
     * Metodo que cifra la contraseña del usuario con la clave publica.
     *
     * @param texto La contraseña del usuario.
     * @return La contraseña cifrada.
     */
    public byte[] cifrarConClavePublica(String texto) {
        byte[] encodedMessage = null;
        try {
            // Cargamos la clave pública
            byte fileKey[] = fileReader("D:\\DAM\\PSP\\CifradoEjercicios\\src\\archivos\\EjemploRSA_Public.key");////////////////////////
            System.out.println("Tamaño -> " + fileKey.length + " bytes");

            // Obtenemos una instancia de KeyFactory, algoritmo RSA
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            // Creamos un nuevo X509EncodedKeySpec del fileKey
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(fileKey);
            // Generamos la public key con el keyFactory
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);

            // Obtenemos una instancia del Cipher "RSA/ECB/PKCS1Padding"
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            // Iniciamos el cipher (ENCRYPT_MODE)
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            // El método doFinal nos cifra el mensaje
            encodedMessage = cipher.doFinal(texto.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encodedMessage;
    }

    /**
     * Metodo que mediante una clave privada (generada por la clase
     * "GeneradorClaves") descifra un texto que ha sido cifrado mediante una
     * clave publlica.
     *
     * @param mensaje El texto cifrado.
     * @return El mensaje descifrado.
     */
    public byte[] descifrarConClavePrivada(byte[] mensaje) {
        byte[] decodedMessage = null;
        try {
            // Cargamos la clave privada
            byte fileKey[] = fileReader("D:\\DAM\\PSP\\CifradoEjercicios\\src\\archivos\\EjemploRSA_Private.key");/////////////////////////
            System.out.println("Tamaño -> " + fileKey.length + " bytes");

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
            decodedMessage = cipher.doFinal(mensaje);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decodedMessage;
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
            ret = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
