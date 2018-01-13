package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.Profile;
import dto.User;

public class RegistrationDao {
	
	private String url;
	private String username;
	private String password;
	Connection connection;
	
	public RegistrationDao(String url, String username, String password) {
		System.out.println("RegistrationDao Constructor");
		this.url=url;
		this.username=username;
		this.password=password;
	}
	
	public void connect() throws SQLException {
		System.out.println("connect");

			if(connection==null||connection.isClosed()) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				connection=DriverManager.getConnection(url, username, password);
			}
		
	}
	public void disconnect() throws SQLException {
		System.out.println("disconnect");
		if(connection!=null && connection.isClosed()) {
			connection.close();
		}
	}
	public boolean createUser(String usernamePar,String passwordPar) throws SQLException {
		System.out.println("createUser");
		String sql = "INSERT INTO users (username, password) VALUES (?,?)";
		connect();
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1,usernamePar);
		statement.setString(2,passwordPar);
		boolean userCreated = statement.executeUpdate()>0; 
		statement.close();
		disconnect();
		return userCreated;
	}
	public Boolean checkUser(String usernamePar,String passwordPar) throws SQLException {
		System.out.println("checkUser");
		Boolean isValid = false;
		String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
		connect();
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1,usernamePar);
		statement.setString(2,passwordPar);
		ResultSet rs = statement.executeQuery(); 
		if(rs.next()) {
			isValid=true;
		} 
		statement.close();
		disconnect();
		return isValid;
	}
	
	public List<Profile> listAllProfiles() throws SQLException {
		List<Profile> listProfile = new ArrayList<>();
		
		String sql = "SELECT * FROM my_profile ORDER BY product DESC";
		
		connect();
		
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			String firstName = resultSet.getString("firstName");
			String lastName = resultSet.getString("lastName");
			float experience = resultSet.getFloat("experience");
			float salary = resultSet.getFloat("salary");

			Profile profile = new Profile(firstName, lastName, experience, salary);
			listProfile.add(profile);
		}
		
		resultSet.close();
		statement.close();
		
		disconnect();
		
		return listProfile;
	}

	public boolean insertProfile(Profile profile) throws SQLException {
		String sql = "INSERT INTO my_profile (user_id, experience, salary, firstName, lastName) VALUES (?, ?, ?, ?, ?)";
		connect();
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, profile.getUser_id());
		statement.setFloat(2, profile.getExperience());
		statement.setFloat(3, profile.getSalary());
		statement.setString(4, profile.getFirstName());
		statement.setString(5, profile.getLastName());

		
		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowInserted;
	}
	
	public boolean updateProfile(Profile updatedProfile) throws SQLException {
		String sql = "UPDATE my_profile SET experience = ?, salary = ?, firstName = ?, lastName = ?";
		sql += " WHERE user_id = ?";
		connect();
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setFloat(1, updatedProfile.getExperience());
		statement.setFloat(2, updatedProfile.getSalary());
		statement.setString(3, updatedProfile.getFirstName());
		statement.setString(4, updatedProfile.getLastName());
		statement.setInt(5, updatedProfile.getUser_id());
		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowUpdated;			
	}
	
	public int getUserId(String usernamePar,String passwordPar) throws SQLException {
		int user_id = 0;
		String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
		
		connect();
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, usernamePar);
		statement.setString(2, passwordPar);
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			user_id = resultSet.getInt("user_id");
		}
		
		resultSet.close();
		statement.close();
		disconnect();
		return user_id;
	}
	
	
	public Profile getProfile(int user_id) throws SQLException {
		Profile profile = null;
		String sql = "SELECT * FROM my_profile WHERE user_id = ?";
		connect();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, user_id);
		ResultSet resultSet = statement.executeQuery();
		if(resultSet.next()) {
			int profile_id = resultSet.getInt("profile_id");
			int experience = resultSet.getInt("experience");
			int salary = resultSet.getInt("salary");
			String firstName = resultSet.getString("firstName");
			String lastName = resultSet.getString("lastName");
			
			profile = new Profile (profile_id,user_id,experience,salary, firstName, lastName);
		}
		resultSet.close();
		statement.close();
		disconnect();
		return profile;
	
	}


	
}
