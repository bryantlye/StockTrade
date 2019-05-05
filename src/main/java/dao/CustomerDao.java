package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.LASTORE;
import model.Customer;
import model.Location;

public class CustomerDao {

	public List<Customer> getCustomers(String searchKeyword) {
		List<Customer> customers = new ArrayList<Customer>();
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
			if(searchKeyword==null) {
				ResultSet resultSet = myStatement.executeQuery("select * from Customer;");
				while (resultSet.next()) {
					Customer myCustomer = new Customer();
					myCustomer.setLastName(resultSet.getString("LastName"));
					myCustomer.setFirstName(resultSet.getString("FirstName"));
					myCustomer.setAddress(resultSet.getString("Address"));
					Location location = new Location();
					location.setCity(resultSet.getString("City"));
					location.setState(resultSet.getString("State"));
					location.setZipCode(Integer.parseInt(resultSet.getString("ZipCode")));
					myCustomer.setLocation(location);
					myCustomer.setTelephone(resultSet.getString("Telephone"));
					myCustomer.setEmail(resultSet.getString("Email"));
					myCustomer.setRating(resultSet.getInt("Rating"));
					myCustomer.setCreditCard(resultSet.getString("CreditCardNumber"));
					myCustomer.setId(Integer.toString(resultSet.getInt("Id")));
					customers.add(myCustomer);
				}
				try {
					myConnection.close();
				} catch (SQLException e) {
					System.out.print(e);
				}
			}
		}
		catch(Exception e){
			System.out.print(e);
		}
		return customers;
	}


	public Customer getHighestRevenueCustomer() {
		/*
		 * This method fetches the customer who generated the highest total revenue and returns it
		 * The students code to fetch data from the database will be written here
		 * The customer record is required to be encapsulated as a "Customer" class object
		 */

		return null;
	}

	public Customer getCustomer(String customerID) {
		Customer customer = new Customer();
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
			int customer1 = Integer.parseInt(customerID);
			ResultSet resultSet = myStatement.executeQuery("select * from Customer where Id='"+customer1+"';");
			while(resultSet.next()) {
				Location location = new Location();
				location.setZipCode(Integer.parseInt(resultSet.getString("ZipCode")));
				location.setState(resultSet.getString("State"));
				location.setCity(resultSet.getString("City"));
				customer.setLocation(location);
				customer.setLastName(resultSet.getString("LastName"));
				customer.setFirstName(resultSet.getString("FirstName"));
				customer.setAddress(resultSet.getString("Address").replace(" ","_"));
				customer.setTelephone(resultSet.getString("Telephone"));
				customer.setEmail(resultSet.getString("Email"));
				customer.setRating(resultSet.getInt("Rating"));
				customer.setCreditCard(resultSet.getString("CreditCardNumber"));
				customer.setId(Integer.toString(resultSet.getInt("Id")));
			}
			try {
				myConnection.close();
			} catch (Exception e) {
				System.out.print(e);
			}
		} catch (Exception e) {
			System.out.print(e);
		}
		return customer;
	}
	
	public String deleteCustomer(String customerID) {
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
			int customerid = Integer.parseInt(customerID);
			ResultSet resultSet = myStatement.executeQuery("select * from Customer where Id='"+customerID+"';");

			if(resultSet.next()){
				String email = resultSet.getString("Email");
				String queryy = " delete from Login WHERE UserName = ?";
				PreparedStatement preparedStmtt = myConnection.prepareStatement(queryy);
				preparedStmtt.setString(1, email);
				preparedStmtt.execute();
				String query = " delete from Customer WHERE Id = ?";
				PreparedStatement preparedStmt = myConnection.prepareStatement(query);
				preparedStmt.setInt(1, customerid);
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


	public String getCustomerID(String email) {
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
			ResultSet resultSet = myStatement.executeQuery("select * from Customer where Email='"+email+"';");
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
		System.out.print(id);
		return id+"";
	}


	public String addCustomer(Customer customer) {
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
			String query = " insert into Customer values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = myConnection.prepareStatement(query);
			preparedStmt.setString(1, customer.getLastName());
			preparedStmt.setString (2, customer.getFirstName());
			preparedStmt.setString (3, customer.getAddress());
			preparedStmt.setString (4, customer.getLocation().getCity());
			preparedStmt.setString (5, customer.getLocation().getState());
			preparedStmt.setString (6, Integer.toString(customer.getLocation().getZipCode()));
			preparedStmt.setString (7, customer.getTelephone());
			preparedStmt.setString (8, customer.getEmail());
			preparedStmt.setInt (9, customer.getRating());
			preparedStmt.setString (10, customer.getCreditCard());
			preparedStmt.setInt (11, Integer.parseInt(customer.getId()));
			preparedStmt.executeUpdate();
		}
		catch(Exception e){
			System.out.print(e);
		}
		return "success";
	}

	public String editCustomer(Customer customer) {
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
			String query = " UPDATE Customer SET LastName = ?, FirstName = ?, Address = ?,  City = ?, State = ?, ZipCode = ?, Telephone = ?, Email = ?, Rating =?, CreditCardNumber=? WHERE Id = ?";
			PreparedStatement preparedStmt = myConnection.prepareStatement(query);
			preparedStmt.setString (1, customer.getLastName());
			preparedStmt.setString (2, customer.getFirstName());
			preparedStmt.setString (3, customer.getAddress());
			preparedStmt.setString (4, customer.getLocation().getCity());
			preparedStmt.setString (5, customer.getLocation().getState());
			preparedStmt.setString (6, Integer.toString(customer.getLocation().getZipCode()));
			preparedStmt.setString (7, customer.getTelephone());
			preparedStmt.setString (8, customer.getEmail());
			preparedStmt.setInt(9, customer.getRating());
			preparedStmt.setString(10, customer.getCreditCard());
			preparedStmt.setInt(11, Integer.parseInt(customer.getId()));
			preparedStmt.executeUpdate();
			try {
				myConnection.close();
			}
			catch (SQLException e) {
				System.out.print(e);
			}
		}
		catch(Exception e) {
			System.out.print(e);
		}
		return "success";
	}

    public List<Customer> getCustomerMailingList() {
        return getCustomers(null);
    }

    public List<Customer> getAllCustomers() {
		return getCustomers(null);
	}
}
