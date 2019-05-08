package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import model.Employee;
import model.Location;
import sun.misc.FloatingDecimal;

public class EmployeeDao {

	public String addEmployee(Employee employee) {
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
			String query = " insert into Employee values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = myConnection.prepareStatement(query);
			preparedStmt.setString(1, employee.getLastName());
			preparedStmt.setString (2, employee.getFirstName());
			preparedStmt.setString (3, employee.getEmail());
			preparedStmt.setString (4, employee.getAddress());
			preparedStmt.setString (5, employee.getLocation().getCity());
			preparedStmt.setString (6, employee.getLocation().getState());
			preparedStmt.setString (7, Integer.toString(employee.getLocation().getZipCode()));
			preparedStmt.setString (8, employee.getTelephone());
			preparedStmt.setInt (9, Integer.parseInt(employee.getSsn()));
			preparedStmt.setInt (10, Integer.parseInt(employee.getSsn()));
			preparedStmt.setDate (11, Date.valueOf(employee.getStartDate()));
			preparedStmt.setDouble(12,employee.getHourlyRate());
			preparedStmt.executeUpdate();
		}
		catch(Exception e){
			System.out.print(e);
		}
		return "success";
	}

	public String editEmployee(Employee employee) {
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

			String query = " UPDATE Employee SET LastName = ?, FirstName = ?, Email = ?, Address = ?,  City = ?, State = ?, ZipCode = ?, Telephone = ?, StartDate = ?, HourlyRate = ? WHERE ID = ?";
			PreparedStatement preparedStmt = myConnection.prepareStatement(query);
			preparedStmt.setString (1, employee.getLastName());
			preparedStmt.setString (2, employee.getFirstName());
			preparedStmt.setString (3, employee.getAddress());
			preparedStmt.setString (4, employee.getEmail());
			preparedStmt.setString (5, employee.getLocation().getCity());
			preparedStmt.setString (6, employee.getLocation().getState());
			preparedStmt.setString (7, Integer.toString(employee.getLocation().getZipCode()));
			preparedStmt.setString (8, employee.getTelephone());
			preparedStmt.setDate(9, Date.valueOf(employee.getStartDate()));
			preparedStmt.setDouble(10, employee.getHourlyRate());
			preparedStmt.setDouble(11, Integer.parseInt(employee.getId()));
			preparedStmt.executeUpdate();

			try {
				myConnection.close();
			}
			catch (Exception e) {
				System.out.print(e);
			}
		}
		catch(Exception e) {
			System.out.print(e);
		}
		return "success";

	}

	public String deleteEmployee(String employeeID) {
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
			ResultSet resultSet = myStatement.executeQuery("select * from Employee where ID='"+employeeID+"';");

			if(resultSet.next()){
				String email = resultSet.getString("Email");
				String queryy = " delete from Login WHERE UserName = ?";
				PreparedStatement preparedStmtt = myConnection.prepareStatement(queryy);
				preparedStmtt.setString(1, email);
				preparedStmtt.execute();
				String query = " delete from Employee WHERE ID = ?";
				PreparedStatement preparedStmt = myConnection.prepareStatement(query);
				preparedStmt.setInt(1, Integer.parseInt(employeeID));
				preparedStmt.execute();
			}
			try {
				myConnection.close();
			} catch (Exception e) {
				System.out.print(e);
			}
		}
		catch (Exception e){
			System.out.print(e);
		}
		return "success";
	}

	public List<Employee> getEmployees() {
		List<Employee> employees = new ArrayList<Employee>();
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
			ResultSet resultSet = myStatement.executeQuery("select * from Employee;");
			while (resultSet.next()) {
				Employee employee = new Employee();
				employee.setLastName(resultSet.getString("LastName"));
				employee.setFirstName(resultSet.getString("FirstName"));
				employee.setAddress(resultSet.getString("Address"));
				Location location = new Location();
				location.setCity(resultSet.getString("City"));
				location.setState(resultSet.getString("State"));
				location.setZipCode(Integer.parseInt(resultSet.getString("ZipCode")));
				employee.setLocation(location);
				employee.setTelephone(resultSet.getString("Telephone"));
				employee.setEmail(resultSet.getString("Email"));
				employee.setId(Integer.toString(resultSet.getInt("ID")));
				employee.setSsn(Integer.toString(resultSet.getInt("ID")));
				employee.setHourlyRate(Float.parseFloat(resultSet.getString("HourlyRate")));
				employee.setStartDate(resultSet.getString("StartDate"));
				employees.add(employee);
			}
			try {
				myConnection.close();
			} catch (SQLException e) {
				System.out.print(e);
			}
		}
		catch(Exception e){
			System.out.print(e);
		}
		return employees;
	}

	public Employee getEmployee(String employeeID) {
		Employee employee = new Employee();
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
			Statement myStatement= myConnection.createStatement();
			ResultSet resultSet = myStatement.executeQuery("select * from Employee where ID='"+employeeID+"';");
			while(resultSet.next()) {
				Location location = new Location();
				location.setZipCode(Integer.parseInt(resultSet.getString("ZipCode")));
				location.setState(resultSet.getString("State"));
				location.setCity(resultSet.getString("City"));
				employee.setLocation(location);
				employee.setLastName(resultSet.getString("LastName"));
				employee.setFirstName(resultSet.getString("FirstName"));
				employee.setAddress(resultSet.getString("Address").replace(" ","_"));
				employee.setTelephone(resultSet.getString("Telephone"));
				employee.setEmail(resultSet.getString("Email"));
				employee.setStartDate(resultSet.getString("StartDate"));
				employee.setHourlyRate(Float.parseFloat(resultSet.getString("HourlyRate")));
				employee.setId(Integer.toString(resultSet.getInt("Id")));
				employee.setSsn(Integer.toString(resultSet.getInt("Id")));
			}
			try {
				myConnection.close();
			} catch (Exception e) {
				System.out.print(e);
			}
		} catch (Exception e) {
			System.out.print(e);
		}
		return employee;
	}
	
	public Employee getHighestRevenueEmployee() {
		Employee employee = new Employee();
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
			Statement myStatement= myConnection.createStatement();
			ResultSet resultSet = myStatement.executeQuery("SELECT E.*, SUM(O.NumShares * O.PricePerShare) AS Revenue FROM Trade T, Employee E, Orderr O WHERE (T.BrokerId IS NOT null AND E.Id = T.BrokerId AND T.OrderId = O.Id) GROUP BY E.Id ORDER BY Revenue DESC LIMIT 1;");
			while(resultSet.next()) {
				Location location = new Location();
				location.setZipCode(Integer.parseInt(resultSet.getString("ZipCode")));
				location.setState(resultSet.getString("State"));
				location.setCity(resultSet.getString("City"));
				employee.setLocation(location);
				employee.setLastName(resultSet.getString("LastName"));
				employee.setFirstName(resultSet.getString("FirstName"));
				employee.setAddress(resultSet.getString("Address").replace(" ","_"));
				employee.setTelephone(resultSet.getString("Telephone"));
				employee.setEmail(resultSet.getString("Email"));
				employee.setStartDate(resultSet.getString("StartDate"));
				employee.setHourlyRate(Float.parseFloat(resultSet.getString("HourlyRate")));
				employee.setId(Integer.toString(resultSet.getInt("Id")));
				employee.setSsn(Integer.toString(resultSet.getInt("Id")));
			}
			try {
				myConnection.close();
			} catch (Exception e) {
				System.out.print(e);
			}
		} catch (Exception e) {
			System.out.print(e);
		}
		return employee;
	}

	public String getEmployeeID(String username) {
		String id=null;
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
			Statement myStatement= myConnection.createStatement();
			ResultSet resultSet = myStatement.executeQuery("select * from Employee where Email='"+username+"';");
			while(resultSet.next()) {
				id = resultSet.getInt("Id")+"";
			}
			try {
				myConnection.close();
			} catch (Exception e) {
				System.out.print(e);
			}
		} catch (Exception e) {
			System.out.print(e);
		}
		return id+"";
	}

}
