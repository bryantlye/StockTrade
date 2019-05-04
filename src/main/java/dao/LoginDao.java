package dao;

import model.Login;

import java.sql.*;

public class LoginDao {
	/*
	 * This class handles all the database operations related to login functionality
	 */
	
	
	public Login login(String username, String password, String role) {
		/*
		 * Return a Login object with role as "manager", "customerRepresentative" or "customer" if successful login
		 * Else, return null
		 * The role depends on the type of the user, which has to be handled in the database
		 * username, which is the email address of the user, is given as method parameter
		 * password, which is the password of the user, is given as method parameter
		 * Query to verify the username and password and fetch the role of the user, must be implemented
		 */
		/*Sample data begins*/
		Login login = new Login();
		login.setRole(role);
		return login;
		/*
		Connection myConnection = null;
		try {
			String mysJDBCDriver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/new_schema?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			String userID = "root";
			String password1 = "12345678";

			Class.forName("com.mysql.jdbc.Driver");
			myConnection = DriverManager.getConnection(url, userID, password1);
			System.out.println("I am able to connect");
			Statement st = myConnection.createStatement();


			//TODO: add password verification once password is added to customer
			ResultSet rs = st.executeQuery("select * from account where Id='" + username + "' and Password='"+password+"'");
			while (rs.next()) {
				Login login = new Login();
				login.setRole(role);
				try {
					myConnection.close();
				} catch (SQLException var13) {
				}

				return login;
			}


		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("Login not successfull");
		return null;
		*/
		
	}
	
	public String addUser(Login login) {
		/*
		 * Query to insert a new record for user login must be implemented
		 * login, which is the "Login" Class object containing username and password for the new user, is given as method parameter
		 * The username and password from login can get accessed using getter methods in the "Login" model
		 * e.g. getUsername() method will return the username encapsulated in login object
		 * Return "success" on successful insertion of a new user
		 * Return "failure" for an unsuccessful database operation
		 */

		Connection myConnection = null;
		try {
			String mysJDBCDriver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/new_schema?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			String userID = "root";
			String password1 = "12345678";
			Class.forName("com.mysql.jdbc.Driver");
			myConnection = DriverManager.getConnection(url, userID, password1);
			System.out.println("I am able to connect");
			Statement st = myConnection.createStatement();

			if(login.getRole() == "customer"){
				String pw = login.getPassword();
				String name = login.getUsername();
				//TODO: uncomment after adding password field to customer
				//st.executeUpdate("update customer set password='"+pw+"' where Email='"+name+"'");
				return "success";
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("Login not successfull");
		return "failure";

		/*Sample data begins*/
		//return "success";
		/*Sample data ends*/
	}

}
