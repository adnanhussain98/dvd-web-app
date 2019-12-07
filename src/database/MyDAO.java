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

/**
 * This class is the DA0 (Data Acessor Object). This class establishes a
 * connection to the database and it contains methods that will communicate with
 * the database
 **/

public class MyDAO {

	private Connection getConnection() {

		// creating connection object
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

	// instead of returning void we want to return all dvds as objects - because
	// java will understand it and we can work with these objects
	// Returning an array list
	public ArrayList<DVD> getDVDs() throws SQLException {

		// initialising array list
		ArrayList<DVD> allDvds = new ArrayList<>();

		// creating connection object inside the method
		Connection connection = getConnection();
		// statement object created which will hold the sql string that will be
		// executed.
		Statement statement = connection.createStatement();
		// adding the question marks to protect from SQL injection. question marks
		// represent parameters that are passed in to the sql
		// gets all dvd's from the dvd table (collection) and stores it in sql string
		String sql = "SELECT * FROM collection;";
		// prints the sql statement that has been executed
		System.out.println(sql);
		// result set catches the sql query result and then executes it.
		ResultSet rs = statement.executeQuery(sql);
		// while function iterates over each row until there's no more rows
		while (rs.next()) {
			// uses getters from DVD class and stores them in rs object.
			int id = rs.getInt("ID");
			String title = rs.getString("Title");
			String genre = rs.getString("Genre");
			int year = rs.getInt("Year");
			// prints each row it has iterated over. Empty strings in sysout hold's the DVD
			// information
			System.out.println(id + " " + title + " " + genre + " " + year + " ");

			// create new DVD object
			DVD dvd = new DVD(id, title, genre, year);
			// adds the arguments from the dvd object to the allDvds array list
			allDvds.add(dvd);

		}
		// Closing the connection as if you do not close it it will consume your
		// resource
		// continuously and will have overall impact on your application and it
		// may also not be added or returned to the pool.
		connection.close();
		// Returning the all the dvds from the arraylist that holds the dvd objects
		return allDvds;
	}

	public DVD getSingleDvd(int dvdId) throws SQLException {
		// creating connection object inside the method
		Connection connection = getConnection();
		// declaring object of DVD class. It has not been instantiated therefore it is
		// null.
		// must have a value before its value is used
		DVD dvd = null;
		// adding the question marks to protect from SQL injection. question marks
		// represent parameters that are passed in to the sql
		// gets single dvd's from the dvd table (collection) where the ID = "" and
		// stores it in sql string
		String sql = "SELECT * FROM collection WHERE id = ?";
		// PreparedStatement prevents SQL injection and executes faster
		// statement object created and sql string is passed as an argument.
		PreparedStatement statement = connection.prepareStatement(sql);

		// the numbers represent the parameters in the query.
		// 1 represents the first parameter in the query.
		statement.setInt(1, dvdId);
		// prints the sql statement that has been executed.
		System.out.println(sql);
		// result set catches the sql query result and then executes it.
		// sql does not need to be passed as an argument as the prepared statement holds
		// it.
		ResultSet rs = statement.executeQuery();
		// while function iterates over each row until there's no more rows
		while (rs.next()) {
			// uses getters from DVD class and stores them in rs object.
			int id = rs.getInt("ID");
			String title = rs.getString("Title");
			String genre = rs.getString("Genre");
			int year = rs.getInt("Year");
			// prints each row it has iterated over. Empty strings in sysout hold's the DVD
			// information
			System.out.println(id + " " + title + " " + genre + " " + year + " ");

			// create new DVD object and stores the variables
			dvd = new DVD(id, title, genre, year);

		}
		// Closing the connection as if you do not close it it will consume your
		// resource
		// continuously and will have overall impact on your application and it
		// may also not be added or returned to the pool.
		connection.close();
		return dvd;
	}

	public void insertDVD(DVD dvd) throws SQLException {
		// creating connection object inside the method
		Connection connection = getConnection();
		// adding the question marks to protect from SQL injection. question marks
		// represent parameters that are passed in to the sql
		// inserts values into title, genre, year field and stores it in sql string
		String sql = "INSERT INTO collection (Title, Genre, Year)" + " VALUES (?, ?, ?)";
		// PreparedStatement prevents SQL injection and executes faster
		// statement object created and sql string is passed as an argument.
		PreparedStatement statement = connection.prepareStatement(sql);

		// the numbers represent the parameters in the query.
		// 1 represents the first parameter in the query.
		statement.setString(1, dvd.getTitle());
		statement.setString(2, dvd.getGenre());
		statement.setInt(3, dvd.getYear());
		// prints sql statement to console.
		System.out.println(sql);
		// executes the sql string that is passed in to the statement
		// sql does not need to be passed as an argument as the prepared statement holds
		// it.
		statement.executeUpdate();
		// prints "done" to console.
		System.out.println("done");
		// Closing the connection as if you do not close it it will consume your
		// resource
		// continuously and will have overall impact on your application and it
		// may also not be added or returned to the pool.
		connection.close();

	}

	public void updateDVD(DVD updateDvd) throws SQLException {
		// creating connection object inside the method
		Connection connection = getConnection();
		// adding the question marks to protect from SQL injection. question marks
		// represent parameters that are passed in to the sql
		// inserts values into title, genre, year field to the ID chosen by the user and
		// stores it in sql string
		String sql = "UPDATE collection " + "SET Title = ?, Genre = ?, Year = ?" + " WHERE ID = ?";
		// PreparedStatement prevents SQL injection and executes faster
		// statement object created and sql string is passed as an argument.
		PreparedStatement statement = connection.prepareStatement(sql);

		// the numbers represent the parameters in the query.
		// 1 represents the first parameter in the query.
		statement.setString(1, updateDvd.getTitle());
		statement.setString(2, updateDvd.getGenre());
		statement.setInt(3, updateDvd.getYear());
		statement.setInt(4, updateDvd.getId());
		// prints sql statement to console.
		System.out.println(sql);
		// executes the sql string that is passed in to the statement
		// sql does not need to be passed as an argument as the prepared statement holds
		// it.
		statement.executeUpdate();
		// prints "done" to console.
		System.out.println("done");
		// Closing the connection as if you do not close it it will consume your
		// resource
		// continuously and will have overall impact on your application and it
		// may also not be added or returned to the pool.
		connection.close();

	}

	public void deleteDVD(int id) throws SQLException {
		// creating connection object inside the method
		Connection connection = getConnection();
		// adding the question marks to protect from SQL injection. question marks
		// represent parameters that are passed in to the sql
		// deletes title from dvd table (collection) by ID chosen by the user and stores
		// it in sql string
		String sql = "DELETE FROM collection " + " WHERE ID = ?";
		// PreparedStatement prevents SQL injection and executes faster
		// statement object created and sql string is passed as an argument.
		PreparedStatement statement = connection.prepareStatement(sql);

		// the numbers represent the parameters in the query.
		// 1 represents the first parameter in the query.
		statement.setInt(1, id);
		// prints sql statement to console.
		System.out.println(sql);
		// executes the sql string that is passed in to the statement
		// sql does not need to be passed as an argument as the prepared statement holds
		// it.
		statement.executeUpdate();
		// prints "done" to console.
		System.out.println("done");
		// Closing the connection as if you do not close it it will consume your
		// resource
		// continuously and will have overall impact on your application and it
		// may also not be added or returned to the pool.
		connection.close();

	}

	public User validateUserInfo(String un, String pw) throws SQLException {

		// makes connection to the database
		Connection connection = getConnection();
		// adding the question marks to protect from SQL injection. question marks
		// represent parameters that are passed in to the sql
		// selects the username, password, apikey and counts each row to check if the
		// username and password matches
		String sql = "SELECT username, password, apikey, COUNT (*) as valid FROM Users WHERE username = ? AND password = ?";
		// PreparedStatement prevents SQL injection and executes faster
		// statement object created and sql string is passed as an argument.
		PreparedStatement statement = connection.prepareStatement(sql);

		// the numbers represent the parameters in the query.
		// 1 represents the first parameter in the query.
		statement.setString(1, un);
		statement.setString(2, pw);

		// prints sql statement to console.
		System.out.println(sql);
		// executes the sql string that is passed in to the statement
		// sql does not need to be passed as an argument as the prepared statement holds
		// it.
		ResultSet rs = statement.executeQuery();

		// while function iterates over each row until there's no more rows
		while (rs.next()) {
			// look in the result set and check if it is valid.
			int valid = rs.getInt("valid");

			if (valid > 0) {
				// prints valid to console if user is valid
				System.out.println("valid user");
				// uses getters from Users class and stores them in rs object.
				String username = rs.getString("username");
				String password = rs.getString("password");
				String api = rs.getString("apikey");
				// closes connection if the user is valid
				connection.close();
				return new User(username, password, api);
			}

		}
		// closes connection if user is not valid.
		connection.close();
		// does not return anything if the user is not valid.
		return null;

	}

	public void insertUser(User user) throws SQLException {

		// makes connection to the database
		Connection connection = getConnection();

		// adding the question marks to protect from SQL injection. question marks
		// represent parameters that are passed in to the sql
		// inserts values into username, password, apikey field and stores it in sql
		// string
		String sql = "INSERT INTO users (username, password, apikey)" + " VALUES (?, ?, ?)";
		// PreparedStatement prevents SQL injection and executes faster
		// statement object created and sql string is passed as an argument.
		PreparedStatement statement = connection.prepareStatement(sql);

		// the numbers represent the parameters in the query.
		// 1 represents the first parameter in the query.
		// points to user object and uses the getters to retrieve methods
		statement.setString(1, user.getUsername());
		statement.setString(2, user.getPassword());
		statement.setString(3, user.getApi());
		// prints sql statement to console.
		System.out.println(sql);
		// executes the sql string that is passed in to the statement
		// sql does not need to be passed as an argument as the prepared statement holds
		// it.
		statement.executeUpdate();
		// prints "done" to console.
		System.out.println("done");
		// Closing the connection as if you do not close it it will consume your
		// resource
		// continuously and will have overall impact on your application and it
		// may also not be added or returned to the pool.
		connection.close();

	}

	public boolean checkKey(String apikey) throws SQLException {

		// makes connection to the database
		Connection connection = getConnection();
		// counts each row from Users table to check if the apikey matches.
		String sql = "SELECT COUNT (*) as valid FROM Users WHERE apikey = ?";
		// PreparedStatement prevents SQL injection and executes faster
		// statement object created and sql string is passed as an argument.
		PreparedStatement statement = connection.prepareStatement(sql);

		// the numbers represent the parameters in the query.
		// 1 represents the first parameter in the query.
		statement.setString(1, apikey);
		// prints sql statement to console.
		System.out.println(sql);
		// executes the sql string that is passed in to the statement
		// sql does not need to be passed as an argument as the prepared statement holds
		// it.
		ResultSet rs = statement.executeQuery();

		// checks the result set
		while (rs.next()) {
			// look in the result set and check if it is valid
			int valid = rs.getInt("valid");
			

			if (valid == 1) {
				// closes connection if the user is valid
				connection.close();
				return true;
			}

		}
		// closes connection if user is not valid.
		connection.close();
		// returns false if the user is not valid.
		return false;

	}

}
