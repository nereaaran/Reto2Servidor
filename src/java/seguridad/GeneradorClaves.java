/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seguridad;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * Clase que contiene el metodo para generar las claves publica y privada que se
 * usan en el cifrado asimetrico y con una prueba para comprobar que funciona.
 *
 * Usa el algoritmo RSA. La clase para crear la especificacion de clave privada
 * es PKCS8EncodedKeySpec y para la publica es X509EncodedKeySpec.
 *
 * @author Nerea Aranguren
 */
public class GeneradorClaves {

    /**
     * Atributo estático y constante que guarda los loggers de la clase.
     */
    private static final Logger LOGGER = Logger.getLogger("seguridad.GeneradorClaves");

    /**
     * Ruta absoluta del proyecto.
     */
    private static final String filePath = new File("").getAbsolutePath();

    /**
     * Atributo que lee las rutas de las claves del archivo de propiedades.
     */
    private static final ResourceBundle RB = ResourceBundle.getBundle("archivos.Paths");

    /**
     * Metodo que genera las claves publica y privada y las guarda en un
     * fichero.
     */
    public void generarClavePrivadaYPublica() {

        KeyPairGenerator generator;
        try {
            LOGGER.info("GeneradorClaves: Generando claves publica y privada");

            generator = KeyPairGenerator.getInstance("RSA");
            // Inicializa el tamaño a 1024 bits
            generator.initialize(1024);
            KeyPair keypair = generator.generateKeyPair();
            PublicKey publicKey = keypair.getPublic();
            PrivateKey privateKey = keypair.getPrivate();

            // Clave Publica
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKey.getEncoded());
            FileOutputStream fileOutputStream = new FileOutputStream(filePath + RB.getString("ASIMETRIC_KEY_PUBLIC"));
            fileOutputStream.write(x509EncodedKeySpec.getEncoded());
            fileOutputStream.close();

            // Clave Privada
            PKCS8EncodedKeySpec pKCS8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());
            fileOutputStream = new FileOutputStream(filePath +  RB.getString("ASIMETRIC_KEY_PRIVATE"));
            fileOutputStream.write(pKCS8EncodedKeySpec.getEncoded());
            fileOutputStream.close();
        } catch (IOException | NoSuchAlgorithmException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    /**
     * Metodo principal que se usa para ejecutar la clase y generar las claves.
     *
     * @param args Array de strings
     */
    public static void main(String[] args) {
//      Genera la clave privada y publica
//      GeneradorClaves generadorClaves = new GeneradorClaves();
//      generadorClaves.generarClavePrivadaYPublica();

        //Prueba de generador de cifrado
        CifradoAsimetrico cifradoAsimetrico = new CifradoAsimetrico();
        String mensajeCifrado = cifradoAsimetrico.cifrarConClavePublica("mensaje super secreto");
        System.out.println("Cifrado! -> " + mensajeCifrado);

        String a = mensajeCifrado;
        // Prueba descifrado
        String mensajeDescifrado = cifradoAsimetrico.descifrarConClavePrivada(a);
        System.out.println("Descifrado! -> " + mensajeDescifrado);
    }
}
