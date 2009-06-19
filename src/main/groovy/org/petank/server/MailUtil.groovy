/**
 * 
 */
package org.petank.server

import groovy.lang.Singletonimport java.util.Properties

import java.util.Properties;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMultipart
import javax.mail.internet.MimeBodyPart
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.activation.DataHandler;

/**
 * @author jlandure
 *
 */
@Singleton
public class MailUtil {
	
	private static String FROM = "petankparty@no-reply.org"

	static MailUtil getInstance() {
		return instance
	}
	
	def sendMail(to, subject, body) {
		this.sendMail(MailUtil.FROM, to, subject, body)
	}
	
	def sendMail(from, to, subject, body) {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		
		try {
	
	        Multipart mp = new MimeMultipart();
	
	        MimeBodyPart htmlPart = new MimeBodyPart();
	        htmlPart.setContent(body, "text/html");
	        mp.addBodyPart(htmlPart);
	
	        //MimeBodyPart attachment = new MimeBodyPart();
	        //attachment.setFileName("manual.pdf");
	        //attachment.setContent(attachmentData, "application/pdf");
	        //mp.addBodyPart(attachment);
	
	        Message msg = new MimeMessage(session);
	        
	        msg.setContent(mp);
	        msg.setFrom(new InternetAddress(from));
	        to.each{
	        	msg.addRecipient(Message.RecipientType.TO,
                        new InternetAddress(it));
	        }
            msg.setSubject(subject);
        
            Transport.send(msg);
    
        } catch (AddressException e) {
           println "AddressException>"+e
        } catch (MessagingException e) {
        	println "MessagingException>"+e
        }
	}
	
}
