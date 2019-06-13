package org.gp7.bmail;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class SendEmail {

	public SendEmail(String to,String subject, String body) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.SocketFactory.port", "587");
		props.put("mail.smtp.SocketFactory.class", "javax.net.SSL.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", "587");

		//Maybe put this in another class for full implementation ex: EmailAut(usn,pass)
		javax.mail.Authenticator auth = new javax.mail.Authenticator() {
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication("group.seven.test@gmail.com", "group7isthebest");
			}
		};
		//Using the properties we defined in props, and the authentication details, we define
		//a session sess.
		//Once provided with these details, the session will persist as long as the client is logged in.
		Session sess = Session.getDefaultInstance(props,auth);

		try {
			Message message = new MimeMessage(sess);
			//SetFrom should always be from the currently logged in user.
			message.setFrom(new InternetAddress("group.seven.test@gmail.com"));

			//message.setHeader(arg0, arg1); I am not sure what this does

			message.setSubject("Hello!");
			message.setText("Hello! If you are reading this, this actually works :D");

			//The first parameter is the type of 'sent' -- TO or CC.
			//The second parameter is the address in the form "username@host.com"
			//This is defined by the user at runtime.
			//new InternetAddress(String) or InternetAddress.parse(String)?
			message.setRecipient(Message.RecipientType.TO, new InternetAddress("jjstanderfer@gmail.com"));
			//message.setRecipients(Message.RecipientType.CC, [[array of InternetAddresses]]); <-For multiple recipients

			Transport.send(message);
			JOptionPane.showMessageDialog(null, "The message has been sent");
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
}
