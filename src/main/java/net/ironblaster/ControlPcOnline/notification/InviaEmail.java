package net.ironblaster.ControlPcOnline.notification;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import net.ironblaster.ControlPcOnline.persistence.Persistence;
import net.ironblaster.ControlPcOnline.persistence.Persistence.EMAILSETTING;

public class InviaEmail {
		static Properties properties;
	 static {
	    	properties = new Properties();
	        properties.put("mail.smtp.host", Persistence.getEmailSetting().get(EMAILSETTING.SERVERSMTP));
	        properties.put("mail.smtp.port", Persistence.getEmailSetting().get(EMAILSETTING.PORT));
	        properties.put("mail.smtp.auth", Persistence.getEmailSetting().get(EMAILSETTING.AUTH));
	        properties.put("mail.smtp.starttls.enable", Persistence.getEmailSetting().get(EMAILSETTING.SSLENABLE));
	    }
	 public static void mail(String oggetto, String messaggio) {
		    
	        try {
	            Authenticator auth = new Authenticator() {
	                public PasswordAuthentication getPasswordAuthentication() {
	                    return new PasswordAuthentication(Persistence.getEmailSetting().get(EMAILSETTING.EMAIL), Persistence.getEmailSetting().get(EMAILSETTING.PASSWORD));
	                }
	            };
	            
	            
	            
	            Session session = Session.getInstance(properties, auth);
	            Message message = new MimeMessage(session);
	            
	            
	            
	            message.setFrom(new InternetAddress(Persistence.getEmailSetting().get(EMAILSETTING.EMAIL)));     
	           
	            
	            message.setRecipient(Message.RecipientType.TO, new InternetAddress(Persistence.getEmailSetting().get(EMAILSETTING.RECIVEREMAIL)));
	            message.setSentDate(new Date());
	            message.setSubject(oggetto);
	            message.setText(messaggio);

	            Transport.send(message);
	            
	     
	        } catch (Exception e) {
	       
	        	e.printStackTrace();
	        }
	    }
}
