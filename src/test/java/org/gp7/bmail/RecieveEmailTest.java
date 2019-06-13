package org.gp7.bmail;

import java.io.IOException;

import javax.mail.MessagingException;

public class RecieveEmailTest {
	public static void main(String[] args) {
		 String host = null;// change accordingly
	      String mailStoreType = null;
	      String username = null;// change accordingly
	      String password = null;// change accordingly

	      try {
			RecieveEmail.check(host, mailStoreType, username, password);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
