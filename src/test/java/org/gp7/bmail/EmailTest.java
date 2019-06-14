package org.gp7.bmail;
import java.io.File;
import java.io.FileNotFoundException;

public class EmailTest {
	public static void main(String[] args) throws FileNotFoundException {
		User user = new User(new File(System.getProperty("user.dir") + "/config.txt"));
		SendEmail send = new SendEmail(user,"averyderekl@gmail.com","test", "test content");
	}

}
