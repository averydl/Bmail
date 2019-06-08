package org.gp7.email.clientTest;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

public class RecieveEmail {

	public static void check(String host, String type, String user, String password) throws MessagingException, IOException {
		Properties props;
		Session sess;
		
		//Going to need a constructor which uses a strategy to determine which connection is made
		//Based on address (@gmail.com, etc.)
		props = new Properties();
		
		//second param is host(pop.gmail.com)
		props.put("mail.pop3.host", "pop.gmail.com");
		props.put("mail.pop3.port", "995");
	    props.put("mail.pop3.starttls.enable", "true");
		
		//This section is dependent on javax.activation.
		//@Todo: look into a way around this since it's deprecated as of Java9
		javax.mail.Authenticator auth = new javax.mail.Authenticator() {
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication("group.seven.test@gmail.com", "group7isthebest");
			}
		};
		
		sess = Session.getDefaultInstance(props,auth);
		Store store = sess.getStore("pop3s");
		
		store.connect("pop.gmail.com","group.seven.test@gmail.com","group7isthebest");
		
		//Unsure how to determine string param here.
		Folder folder = store.getFolder("INBOX");
		folder.open(Folder.READ_ONLY);
		
		//Delivered to us in the form of an array of messages, very convenient!
		Message[] messages = folder.getMessages();
		
		//for each message, get subject, sender, and content
		for(int i = 0; i < messages.length;i++) {
			Message message = messages[i];
			System.out.println("---------------------------------");
	        System.out.println("Email Number " + (i + 1));
	        System.out.println("Subject: " + message.getSubject());
	        System.out.println("From: " + message.getFrom()[0]);
	        System.out.println("Text: " + message.getContent().toString());
		}
		
		//Passing false here allows us to keep the emails stored locally until expunged.
		folder.close(false);
		store.close();
		
		
	}
}
