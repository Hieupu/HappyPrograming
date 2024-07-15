package util;

import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class EmailService {

    private static final Logger logger = Logger.getLogger(EmailService.class.getName());

    private final String username = "hieudnmhe171083@fpt.edu.vn";
    private final String password = "fmax pfao tlms zlet";
    private final String host = "smtp.gmail.com";
    private final int port = 587;

    /**
     *
     * @param to
     * @param subject
     * @param text
     */
    public void sendEmail(String to, String subject, String text) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);
            logger.log(Level.INFO, "Email sent successfully to {0}", to);
        } catch (MessagingException e) {
            logger.log(Level.SEVERE, "Failed to send email to " + to, e);
            throw new RuntimeException("Failed to send email", e);
        }
    }
}
