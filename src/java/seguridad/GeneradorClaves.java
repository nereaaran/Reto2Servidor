/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seguridad;

import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Clase que contiene el metodo para generar las claves publica y privada que se
 * usan en el cifrado asimetrico.
 *
 * Usa el algoritmo RSA. La clase para crear la especificacion de clave privada
 * es PKCS8EncodedKeySpec y para la publica es X509EncodedKeySpec.
 *
 * @author Nerea Aranguren
 */
public class GeneradorClaves {

    /**
     * Metodo que genera las claves publica y privada y las guarda en un
     * fichero.
     */
    public void generarClavePrivada() {

        KeyPairGenerator generator;
        try {
            generator = KeyPairGenerator.getInstance("RSA");
            // Inicializa el tamaño a 1024 bits
            generator.initialize(1024);
            KeyPair keypair = generator.generateKeyPair();
            PublicKey publicKey = keypair.getPublic();
            PrivateKey privateKey = keypair.getPrivate();

            // Clave Publica
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKey.getEncoded());
            FileOutputStream fileOutputStream = new FileOutputStream("D:\\DAM\\PSP\\CifradoEjercicios\\src\\archivos\\EjemploRSA_Public.key");//////////////////
            fileOutputStream.write(x509EncodedKeySpec.getEncoded());
            fileOutputStream.close();

            // Clave Privada
            PKCS8EncodedKeySpec pKCS8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());
            fileOutputStream = new FileOutputStream("D:\\DAM\\PSP\\CifradoEjercicios\\src\\archivos\\EjemploRSA_Private.key");//////////////////////////////////
            fileOutputStream.write(pKCS8EncodedKeySpec.getEncoded());
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
