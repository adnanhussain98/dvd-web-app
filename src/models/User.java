package models;

public class User {
	
	String username;
	String password;
	String api;
	
	public User(String username, String password, String api) {
		super();
		this.username = username;
		this.password = password;
		this.api = api;
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

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}
}
