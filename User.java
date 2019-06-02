import com.google.gson.Gson;

import java.io.PrintWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class User {
    private static final String PROTOCOL = "imap";
    private static boolean CONFIG_FILE_EXISTS = false;

    private String username;
    private String password;
    private String email;
    private String emailPassword;
    private String emailServer;

    public User(String username, String password, String email, String emailPassword, String emailServer) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.emailPassword = emailPassword;
        this.emailServer = emailServer;

        // attempt to store user information in config file
        try {
            Gson gson = new Gson();
            String config = gson.toJson(this);
            FileWriter file = new FileWriter(new File("config.txt"));
            PrintWriter writer = new PrintWriter(file);
            writer.write(config);
            writer.close();
            CONFIG_FILE_EXISTS = true;
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public User(File file) throws FileNotFoundException {
        // read file contents to string
        Scanner input = new Scanner(file);
        StringBuilder strBuilder = new StringBuilder();
        while (input.hasNext()) {
            strBuilder.append(input.next());
        }

        // convert file contents (JSON) to new User object using GSON
        Gson gson = new Gson();
        User u = gson.fromJson(strBuilder.toString(), User.class);

        // set fields of new User instance to match fields from config file
        this.username = u.getUsername();
        this.password = u.getPassword();
        this.email = u.getEmail();
        this.emailPassword = u.getEmailPassword();
        this.emailServer = u.getEmailServer();
    }

    public static String getPROTOCOL() {
        return PROTOCOL;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailPassword() {
        return emailPassword;
    }

    public void setEmailPassword(String emailPassword) {
        this.emailPassword = emailPassword;
    }

    public String getEmailServer() {
        return emailServer;
    }

    public void setEmailServer(String emailServer) {
        this.emailServer = emailServer;
    }
}
