package dao;

import model.Login;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginDao {

	public Login login(String username, String password, String role) {
		Login login = null;
		java.sql.Connection myConnection = null;
		try {
			String mysJDBCDriver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/new_schema";
			String userID = "root";
			String password1 = "root";
			Class.forName(mysJDBCDriver).newInstance();
			java.util.Properties mysys = System.getProperties();
			mysys.put("user", userID);
			mysys.put("password", password1);
			myConnection = DriverManager.getConnection(url, mysys);
			Statement myStatement = myConnection.createStatement();
			ResultSet resultSet = myStatement.executeQuery("select * from Login where UserName='"+ username+"' and Password='"+password+"' and Role='"+role+"'");
			if(resultSet.next()) {
				login = new Login();
				login.setUsername(resultSet.getString("UserName"));
				login.setPassword(resultSet.getString("Password"));
				login.setRole(resultSet.getString("Role"));
				try {
					myConnection.close();
				} catch (Exception e) {
					System.out.print(e);
				}
				return login;
			}
		}
		catch (Exception e) {
			System.out.print(e);
		}
		return login;
	}
	
	public String addUser(Login login) {
		java.sql.Connection myConnection = null;
		try {
			String mysJDBCDriver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/new_schema";
			String userID = "root";
			String password1 = "root";
			Class.forName(mysJDBCDriver).newInstance();
			java.util.Properties mysys = System.getProperties();
			mysys.put("user", userID);
			mysys.put("password", password1);
			myConnection = DriverManager.getConnection(url, mysys);
			String query = " insert into Login values (?, ?, ?)";
			PreparedStatement preparedStmt = myConnection.prepareStatement(query);
			preparedStmt.setString(1, login.getUsername());
			preparedStmt.setString (2, login.getPassword());
			preparedStmt.setString (3, login.getRole());
			preparedStmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.print(e);
		}
		return "success";
	}

}
