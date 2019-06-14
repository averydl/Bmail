import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnection {
	private Connection connection;
	private String url = "jdbc:mysql://localhost:3306/email_database?serverTimezone=UTC&useSSL=TRUE";
	
	public DatabaseConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			System.out.println("Could not load the driver");
		}
		
		try {
			connection = DriverManager.getConnection(url, "student", "password");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addUsername(String username, String password) {
		String query = "INSERT INTO users (username, password)";
		query += "VALUES (?, ?)";
		try {
			PreparedStatement p = connection.prepareStatement(query);
			p.setString(1, username);
			p.setString(2, password);
			p.execute();
			p.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void addEmail(String user, String sender, String subject, String body) {
		String query = "INSERT INTO emails (ownerId, senderId, subject, body)";
		query += "VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement p = connection.prepareStatement(query);
			p.setString(1, user);
			p.setString(2, sender);
			p.setString(3, subject);
			p.setString(4, body);
			p.execute();
			p.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void getAccount(String username) {
		String query = "SELECT * FROM users ";
		query += "WHERE username = \"" + username + "\";";
		System.out.println(query);
		PreparedStatement p;
		try {
			p = connection.prepareStatement(query);
//			p.setString(1, username);
			ResultSet r = p.executeQuery();
			while (r.next()) {
				System.out.println(r.getString(1) + " " + r.getString(2));
//				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void getEmails(String username) {
		String query = "SELECT * FROM emails ";
		query += "WHERE ownerId = \"" + username + "\";";
		System.out.println(query);
		PreparedStatement p;
		try {
			p = connection.prepareStatement(query);
//			p.setString(1, username);
			ResultSet r = p.executeQuery();
			while (r.next()) {
				System.out.println(r.getString(1) + " " + r.getString(2) + " " + r.getString(3) + " " + r.getString(4));
//				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		DatabaseConnection db = new DatabaseConnection();
		db.addUsername("Anthony", "password");
		String body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus ac neque tempor, posuere nunc nec, aliquet sapien. Sed vitae ipsum sed mi posuere pellentesque eu eu lectus. Proin convallis, neque sed vehicula lacinia, lectus nisl scelerisque magna, ut aliquam lorem lacus ut ipsum. Donec scelerisque euismod semper. Morbi enim tortor, sodales eget arcu in, blandit molestie sapien. Praesent id tortor venenatis, aliquet leo nec, viverra elit. Aenean eros libero, tincidunt ut mauris vel, aliquam aliquet velit. Sed placerat et enim ut vehicula. Nullam non lectus in nibh convallis suscipit. Aliquam placerat, orci ut faucibus venenatis, sem massa ornare ante, venenatis consequat quam sem sed leo.";
		db.addEmail("Anthony", "Joe", "yo", body);
		db.getAccount("Anthony");
		db.getEmails("Anthony");
	}
}
