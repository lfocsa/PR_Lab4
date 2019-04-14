package SMTP_Sending_messages;

import POP3_Retrieve_messages.RetrieveEmails;
import com.sun.mail.smtp.SMTPTransport;


import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Calendar;
import java.util.Properties;

public class SendEmail {
    private static Multipart multipart = new MimeMultipart();

    public static void main(String[] args) {
        Session session = Session.getInstance(GetProperties(), null);

        try {
            Message msg = MessageInitialize(session);
            SMTPTransport transport = (SMTPTransport)session.getTransport("smtps");
            transport.connect("smtp.gmail.com", RetrieveEmails.USERNAME, RetrieveEmails.PASSWORD);
            transport.sendMessage(msg, msg.getAllRecipients());

            System.out.println("Response: " + transport.getLastServerResponse());
            transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private static Message MessageInitialize(Session session) throws MessagingException{
        Message sender = new MimeMessage(session);
        sender.setFrom(new InternetAddress("test@example.com"));
        sender.setRecipients(Message.RecipientType.TO, InternetAddress.parse(RetrieveEmails.USERNAME, false));
        sender.setSubject("SMTP Message Subject ");
        sender.setSentDate(Calendar.getInstance().getTime());


        BodyParts("<em>This is a message body.</em><br>" +
                "<h2>Image is here</h2><img src=\"cid:image\"></a>", "text/html; charset=utf-8");
        Attachments("utm.png", "<image>");
        Attachments("PR-File.txt", null);

        sender.setContent(multipart);

        return sender;
    }

    private static void Attachments(String path, String contentId) throws MessagingException {
        BodyPart messageBodyPart = new MimeBodyPart();
        DataSource fds = new FileDataSource(path);
        messageBodyPart.setDataHandler(new DataHandler(fds));
        messageBodyPart.setHeader("Content-ID", contentId);
        messageBodyPart.setFileName(path);
        multipart.addBodyPart(messageBodyPart);
    }

    private static void BodyParts(String contentText, String contentType) throws MessagingException {
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(contentText, contentType);
        multipart.addBodyPart(messageBodyPart);
    }

    private static Properties GetProperties(){
        Properties props = System.getProperties();
        props.put("mail.smtps.host","smtp.gmail.com");
        props.put("mail.smtps.auth","true");
        props.put("mail.smtp.EnableSSL.enable","true");
        return props;
    }
}
