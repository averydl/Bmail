package org.gp7.bmail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.mail.Message;
import javax.mail.MessagingException;

public class ReceiveEmailTest {
	public static void main(String[] args) throws FileNotFoundException {
		User user = new User(new File(System.getProperty("user.dir") + "/config.txt"));
	      try {
			Email[] emails = ReceiveEmail.check(user);
			for(Email email : emails) {
				System.out.println(email);
			}
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
