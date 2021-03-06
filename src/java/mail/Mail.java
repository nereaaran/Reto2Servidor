/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;

import entidad.Usuario;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import seguridad.CifradoSimetrico;

/**
 * Clase que se encarga de enviar un mail de cambio de contraseña.
 *
 * @author Cristina Milea
 */
public class Mail {

    /**
     * * Atributo estático y constante que guarda los loggers de la clase.
     */
    private static final Logger LOGGER = Logger.getLogger("mail.Mail");

    /**
     * Atributo que lee los datos del archivo de propiedades.
     */
    private static final ResourceBundle RB = ResourceBundle.getBundle("mail.Mail");

    /**
     * Correo electronico del emisor.
     */
    private final String MAIL;
    /**
     * Contraseña del emisor.
     */
    private final String PASS;

    /**
     * Host del SMTP.
     */
    private final String SMTP_HOST;
    /**
     * Puerto del SMTP.
     */
    private final int SMTP_PORT;

    /**
     * Constructor de la clase que coge los valores de un archivo de
     * propiedades.
     */
    public Mail() {
        CifradoSimetrico cifradoSimetrico = new CifradoSimetrico();
        this.MAIL = cifradoSimetrico.descifrarEmailConClavePrivada();//"info.ComicSans@gmail.com"; 
        this.PASS = cifradoSimetrico.descifrarContraseñaConClavePrivada();//"abcd*1234";
        this.SMTP_HOST = RB.getString("SMTP_HOST");
        this.SMTP_PORT = Integer.parseInt(RB.getString("SMTP_PORT"));
    }

    /**
     * Método que genera la nueva contraseña para el usuario.
     *
     * @return la nueva contraseña generada.
     */
    public String generarContrasenia() {
        int leftLimit = 48; //Número '0'
        int rightLimit = 122; //Letra 'Z'
        int tamanioMax = 10;
        Random random = new Random();

        String nuevaContrasenia = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)) //(0-9) || (A-Z) && (a-z)
                .limit(tamanioMax)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return nuevaContrasenia;
    }

    /**
     * Método que se encarga de configurar el mail para recuperar la contraseña
     * olvidada.
     *
     * @param mailReceptor el mail del receptor.
     * @return la nueva contraseña.
     * @throws MessagingException una excepción si ocurre algún error con el
     * envío del mensaje.
     */
    public String configurarMailRecuperarContrasenia(String mailReceptor) throws MessagingException {
        //Propiedades mínimas y obligatorias del mail.
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.port", SMTP_PORT);
        properties.put("mail.smtp.ssl.enable", "false");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.trust", SMTP_HOST);
        properties.put("mail.imap.partialfetch", false);

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MAIL, PASS);
            }
        });

        //Se crea un mensaje nuevo.
        Message message = new MimeMessage(session);
        //Establece el emisor.
        message.setFrom(new InternetAddress(MAIL));
        //Establece el receptor.
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailReceptor));

        //El mail puede tener varias partes.
        Multipart multipart = new MimeMultipart();

        //La parte principal del mail.
        String nuevaContrasenia = generarContrasenia();
        MimeBodyPart mimeBodyPart = new MimeBodyPart();

        //Establece el asunto del mail.
        message.setSubject(RB.getString("ASUNTO_RECUPERACION"));
        mimeBodyPart.setContent(RB.getString("TEXTO_RECUPERACION") + nuevaContrasenia + RB.getString("HTML"), "text/html");

        multipart.addBodyPart(mimeBodyPart);

        //Junta todas las partes.
        message.setContent(multipart);

        //Envia el mail.
        Transport.send(message);

        return nuevaContrasenia;
    }

    /**
     * Método que se encarga de configurar el mail para cambiar la contrasenia.
     *
     * @param mailReceptor el mail del receptor.
     * @return la nueva contraseña.
     * @throws MessagingException una excepción si ocurre algún error con el
     * envío del mensaje.
     */
    public String configurarMailCambiarContrasenia(String mailReceptor) throws MessagingException {
        //Propiedades mínimas y obligatorias del mail.
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.port", SMTP_PORT);
        properties.put("mail.smtp.ssl.enable", "false");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.trust", SMTP_HOST);
        properties.put("mail.imap.partialfetch", false);

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MAIL, PASS);
            }
        });

        //Se crea un mensaje nuevo.
        Message message = new MimeMessage(session);
        //Establece el emisor.
        message.setFrom(new InternetAddress(MAIL));
        //Establece el receptor.
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailReceptor));

        //El mail puede tener varias partes.
        Multipart multipart = new MimeMultipart();

        //La parte principal del mail.
        String nuevaContrasenia = generarContrasenia();
        MimeBodyPart mimeBodyPart = new MimeBodyPart();

        //Establece el asunto del mail.
        message.setSubject(RB.getString("ASUNTO_CAMBIO"));
        mimeBodyPart.setContent(RB.getString("TEXTO_CAMBIO"), "text/html");

        multipart.addBodyPart(mimeBodyPart);

        //Junta todas las partes.
        message.setContent(multipart);

        //Envia el mail.
        Transport.send(message);

        return nuevaContrasenia;
    }

    /**
     * Método que se encarga de enviar el mail de recuperación de contraseña.
     *
     * @param usuario A quien se le envia el email.
     * @return la nueva contraseña.
     */
    public static String enviarMailRecuperarContrasenia(Usuario usuario) {
        String nuevaContrasenia = null;

        try {
            LOGGER.info("Mail: Enviando mail de recuperación");
            Mail mail = new Mail();

            nuevaContrasenia = mail.configurarMailRecuperarContrasenia(usuario.getEmail());

        } catch (MessagingException e) {
            LOGGER.severe(e.getMessage());
        }

        return nuevaContrasenia;
    }

    /**
     * Método que se encarga de enviar el mail de cambio de contraseña.
     *
     * @param email el email al que se le enviará el correo.
     */
    public static void enviarMailCambiarContrasenia(String email) {
        try {
            LOGGER.info("Mail: Enviando mail de cambio de contraseña");
            Mail mail = new Mail();

            mail.configurarMailCambiarContrasenia(email);

        } catch (MessagingException e) {
            LOGGER.severe(e.getMessage());
        }
    }
}
