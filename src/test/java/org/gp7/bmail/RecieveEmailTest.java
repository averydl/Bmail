package org.gp7.bmail;

import java.io.IOException;

import javax.mail.MessagingException;

public class RecieveEmailTest {
	public static void main(String[] args) {
		  String host = "pop.gmail.com"; // change accordingly
	      String username = "group.seven.test@gmail.com";// change accordingly
	      String password = "group7isthebest"; // change accordingly

	      try {
			RecieveEmail.check(host, username, password);
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
