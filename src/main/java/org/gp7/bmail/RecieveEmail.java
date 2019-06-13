package org.gp7.bmail;

import java.io.IOException;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class RecieveEmail {

	public static void check(String host, String user, String password) throws MessagingException, IOException {
		//Going to need a constructor which uses a strategy to determine which connection is made
		//Based on address (@gmail.com, etc.)
		Properties props = new Properties();

		//second param is host(pop.gmail.com)
		props.put("mail.pop3.host", host);
		props.put("mail.pop3.port", "995");
	    props.put("mail.pop3.starttls.enable", "true");


		Session sess = Session.getDefaultInstance(props);
		Store store = sess.getStore("pop3s");

		store.connect(host, user, password);

		//Unsure how to determine string param here.
		Folder[] folders = store.getDefaultFolder().list();

		for(Folder folder : folders) {
			System.out.println(folder);
			System.out.println("---------------------------------");
			System.out.println("---------------------------------");
			folder.open(Folder.READ_ONLY);

			//Delivered to us in the form of an array of messages, very convenient!
			Message[] messages = folder.getMessages();
			//for each message, get subject, sender, and content
			for (int i = 0; i < messages.length; i++) {
				Message message = messages[i];
				System.out.println("---------------------------------");
				System.out.println("Email Number " + (i + 1));
				System.out.println("Subject: " + message.getSubject());
				System.out.println("From: " + message.getFrom()[0]);
				System.out.println("Text: " + getText(message));
			}

			//Passing false here allows us to keep the emails stored locally until expunged.
			folder.close(false);
			store.close();
		}


	}

	//For some reason there's no way to guarantee that the information Message.getContent() returns
	//is actually useful and readable. this private method goes through a lot of effort to return
	//readable text.
	private static String getText(Message message) throws MessagingException, IOException {
	    String result = "";
	    if (message.isMimeType("text/plain")) {
	        result = message.getContent().toString();
	    } else if (message.isMimeType("multipart/*")) {
	        MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
	        result = getTextFromMimeMultipart(mimeMultipart);
	    }
	    return result;
	}

	private static String getTextFromMimeMultipart(
	        MimeMultipart mimeMultipart)  throws MessagingException, IOException{
	    String result = "";
	    int count = mimeMultipart.getCount();
	    for (int i = 0; i < count; i++) {
	        BodyPart bodyPart = mimeMultipart.getBodyPart(i);
	        if (bodyPart.isMimeType("text/plain")) {
	            result = result + "\n" + bodyPart.getContent();
	            break; // without break same text appears twice in my tests
	        } else if (bodyPart.isMimeType("text/html")) {
	            String html = (String) bodyPart.getContent();
	            result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
	        } else if (bodyPart.getContent() instanceof MimeMultipart){
	            result = result + getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent());
	        }
	    }
	    return result;
	}
}
