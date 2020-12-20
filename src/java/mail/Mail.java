/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;

import java.util.Properties;
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
import restful.UsuarioFacadeREST;

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
        this.MAIL = RB.getString("MAIL");
        this.PASS = RB.getString("PASS");
        this.SMTP_HOST = RB.getString("SMTP_HOST");
        this.SMTP_PORT = Integer.parseInt(RB.getString("SMTP_PORT"));
    }

    /**
     * Método que se encarga de enviar el mail.
     *
     * @param mailReceptor el mail del receptor.
     * @throws MessagingException una excepción si ocurre algún error con el
     * envío del mensaje.
     */
    public void enviarMail(String mailReceptor) throws MessagingException {
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
        //Establece el asunto del mail.
        message.setSubject(RB.getString("ASUNTO"));

        //El mail puede tener varias partes.
        Multipart multipart = new MimeMultipart();

        //La parte principal del mail.
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(RB.getString("TEXTO"), "text/html");
        multipart.addBodyPart(mimeBodyPart);

        //Junta todas las partes.
        message.setContent(multipart);

        //Envia el mail.
        Transport.send(message);
    }

    /**
     * Método main que crea un servicio de mail nuevo.
     *
     * @param args los argumentos que va a recibir.
     */
    public static void main(String[] args) {
        try {            
            LOGGER.info("Mail: Enviando mail");
            Mail mail = new Mail();
            mail.enviarMail("kristina.s.milea@gmail.com");
        } catch (MessagingException e) {
            LOGGER.severe(e.getMessage());
        }
    }
}