package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.DVD;
import models.User;

public class MyDAO {

	private Connection getConnection() {

		Connection conn = null;

		try {
			// load driver
			Class.forName("org.sqlite.JDBC");
			// url of the database location
			String url = "jdbc:sqlite:/Users/adnanhussain/Documents/DVDWebApp/dvd.sqlite";
			// initialise connection object
			conn = DriverManager.getConnection(url);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		// returns conn object
		return conn;
	}

	public ArrayList<DVD> getDVDs() throws SQLException {

		ArrayList<DVD> allDvds = new ArrayList<>();

		Connection connection = getConnection();
		Statement statement = connection.createStatement();

		String sql = "SELECT * FROM collection;";

		System.out.println(sql);

		ResultSet rs = statement.executeQuery(sql);

		while (rs.next()) {
			int id = rs.getInt("ID");
			String title = rs.getString("Title");
			String genre = rs.getString("Genre");
			int year = rs.getInt("Year");

			System.out.println(id + " " + title + " " + genre + " " + year + " ");

			// create new DVD object
			DVD dvd = new DVD(id, title, genre, year);

			allDvds.add(dvd);

		}
		connection.close();
		return allDvds;
	}

	public DVD getSingleDvd(int dvdId) throws SQLException {

		Connection connection = getConnection();
		Statement statement = connection.createStatement();
		DVD dvd = null;

		String sql = "SELECT * FROM collection WHERE id = " + dvdId + ";";

		System.out.println(sql);

		ResultSet rs = statement.executeQuery(sql);

		while (rs.next()) {
			int id = rs.getInt("ID");
			String title = rs.getString("Title");
			String genre = rs.getString("Genre");
			int year = rs.getInt("Year");

			System.out.println(id + " " + title + " " + genre + " " + year + " ");

			// create new DVD object
			dvd = new DVD(id, title, genre, year);

			// connection.close();

		}
		connection.close();
		return dvd;
	}

	public void insertDVD(DVD dvd) throws SQLException {

		Connection connection = getConnection();
		Statement statement = connection.createStatement();

		String sql = "INSERT INTO collection ( Title, Genre, Year )" + " VALUES ('" + dvd.getTitle() + "', '"
				+ dvd.getGenre() + "', " + dvd.getYear() + ");";
		System.out.println(sql);

		statement.executeUpdate(sql);
		System.out.println("done");

		connection.close();

	}

	public void updateDVD(DVD updateDvd) throws SQLException {

		Connection connection = getConnection();
		Statement statement = connection.createStatement();

		String sql = "UPDATE collection " + "SET Title = '" + updateDvd.getTitle() + "', Genre = '"
				+ updateDvd.getGenre() + "', Year = " + updateDvd.getYear() + "" + " WHERE ID = " + updateDvd.getId()
				+ ";";
		System.out.println(sql);

		statement.executeUpdate(sql);
		System.out.println("done");

		connection.close();

	}

	public void deleteDVD(int id) throws SQLException {

		Connection connection = getConnection();
		Statement statement = connection.createStatement();

		String sql = "DELETE FROM collection " + " WHERE ID = " + id + ";";

		System.out.println(sql);

		statement.executeUpdate(sql);
		System.out.println("done");

		connection.close();

	}

	public User validateUserInfo(String un, String pw) throws SQLException {

		// makes connection to the database
		Connection connection = getConnection();
		//adding the question marks to protect
		String sql = "SELECT username, password, apikey,  COUNT (*) as valid FROM Users WHERE username = ? AND password = ?";

		PreparedStatement statement = connection.prepareStatement(sql);
		
		// add variables to the prepared statement
		statement.setString(1, un);
		statement.setString(2, pw);

		System.out.println(sql);
		ResultSet rs = statement.executeQuery(sql);

		// checks the result set
		while (rs.next()) {
			// look in the result set and check if it is valid
			int valid = rs.getInt("valid");

			if (valid > 0) {
				System.out.println("valid user");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String api = rs.getString("apikey");

				connection.close();
				return new User(username, password, api);
			}

		}
		connection.close();
		return null;

	}

	public void insertUser(User user) throws SQLException {

		Connection connection = getConnection();
		Statement statement = connection.createStatement();

		String sql = "INSERT INTO users (username, password, apikey)" + " VALUES ('" + user.getUsername() + "', '"
				+ user.getPassword() + "', '" + user.getApi() + "');";
		System.out.println(sql);

		statement.executeUpdate(sql);
		System.out.println("done");

		connection.close();

	}

	public boolean checkKey(String apikey) throws SQLException {

		// makes connection to the database
		Connection connection = getConnection();
		Statement statement = connection.createStatement();

		String sql = "SELECT COUNT (*) as valid FROM Users WHERE apikey = '" + apikey + "';";

		System.out.println(sql);
		ResultSet rs = statement.executeQuery(sql);

		// checks the result set
		while (rs.next()) {
			// look in the result set and check if it is valid
			int valid = rs.getInt("valid");

			if (valid == 0) {
				// all good
				connection.close();
				return true;
			}

		}
		connection.close();
		return false;

	}

}
