package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.DVD;

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
			
			//create new DVD object
			DVD dvd = new DVD(id, title, genre, year);
			
			allDvds.add(dvd);

		}
		return allDvds;
	}

	public void insertDVD(DVD dvd) throws SQLException {

		Connection connection = getConnection();
		Statement statement = connection.createStatement();

		String sql = "INSERT INTO collection (ID, Title, Genre, Year )" + " VALUES ("+dvd.getId()+", '"+dvd.getTitle()+"', '"+dvd.getGenre()+"', "+dvd.getYear()+");";
		System.out.println(sql);
		
		statement.executeUpdate(sql);
		System.out.println("done");

	}
	
	public void updateDVD(DVD updateDvd) throws SQLException {

		Connection connection = getConnection();
		Statement statement = connection.createStatement();

		String sql = "UPDATE collection " + 
				"SET Title = '"+updateDvd.getTitle()+"', Genre = '"+updateDvd.getGenre()+"', Year = "+updateDvd.getYear()+"" + 
				" WHERE ID = "+updateDvd.getId()+";";
		System.out.println(sql);
		
		statement.executeUpdate(sql);
		System.out.println("done");

	}
	
	public void deleteDVD(DVD deleteDvd) throws SQLException {

		Connection connection = getConnection();
		Statement statement = connection.createStatement();

		String sql = "DELETE FROM collection " + 
				" WHERE ID = "+deleteDvd.getId()+";";

		System.out.println(sql);
		
		statement.executeUpdate(sql);
		System.out.println("done");

	}
}
