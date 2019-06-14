package org.gp7.bmail;

public class EmailTest {
	public static void main(String[] args) {
		User user = new User("group7", "password", "group.seven.test@gmail.com", "group7isthebest", "pop.gmail.com", "smtp.gmail.com");
		SendEmail send = new SendEmail(user,"averyderekl@gmail.com","test", "test content");
	}

}
