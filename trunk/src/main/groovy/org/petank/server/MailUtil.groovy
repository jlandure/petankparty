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
	
	private static String FROM = "jujujuz@gmail.com"
	private static String HEAD = "[PetankParty] "

	static MailUtil getInstance() {
		return instance
	}
	
	def sendMail(to, subject, body) {
		this.sendMail(MailUtil.FROM, to, subject, body)
	}
	
	def sendMail(to, subject, body, byte[] attachmentData, attachmentName, attachmentType) {
		MimeBodyPart attachment = new MimeBodyPart()
		attachment.setFileName(attachmentName)
		attachment.setContent(attachmentData, attachmentType)
		this.sendMail(MailUtil.FROM, to, subject, body, attachment)
	}
	
	def sendMail(from, to, subject, body, MimeBodyPart attachment=null) {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		Message msg = new MimeMessage(session);
		
		Multipart mp = new MimeMultipart();
		
		MimeBodyPart htmlPart = new MimeBodyPart();
		htmlPart.setContent(body, "text/html");
		mp.addBodyPart(htmlPart);
		
		if(attachment != null) {
			mp.addBodyPart(attachment);
		}
		
		
		msg.setFrom(new InternetAddress(from));
		to.each{
			msg.addRecipient(Message.RecipientType.TO,
					new InternetAddress(it));
		}
		msg.setSubject(HEAD + subject);
		msg.setContent(mp);
		
		Transport.send(msg);
		
	}
	
}
