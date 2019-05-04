package dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import model.Customer;
import model.Location;

public class CustomerDao {
	/*
	 * This class handles all the database operations related to the customer table
	 */

	public CustomerDao(){}

    public Customer getDummyCustomer() {
        Location location = new Location();
        location.setZipCode(11790);
        location.setCity("Stony Brook");
        location.setState("NY");

        Customer customer = new Customer();
        customer.setId("111-11-1111");
        customer.setAddress("123 Success Street");
        customer.setLastName("Lu");
        customer.setFirstName("Shiyong");
        customer.setEmail("shiyong@cs.sunysb.edu");
        customer.setLocation(location);
        customer.setTelephone("5166328959");
        customer.setCreditCard("1234567812345678");
        customer.setRating(1);

        return customer;
    }
    public List<Customer> getDummyCustomerList() {
        /*Sample data begins*/
        List<Customer> customers = new ArrayList<Customer>();

        for (int i = 0; i < 10; i++) {
            customers.add(getDummyCustomer());
        }
		/*Sample data ends*/

        return customers;
    }

    /**
	 * @param String searchKeyword
	 * @return ArrayList<Customer> object
	 */
	public List<Customer> getCustomers(String searchKeyword) {
		/*
		 * This method fetches one or more customers based on the searchKeyword and returns it as an ArrayList
		 */

		List<Customer> customers = new ArrayList();
		System.out.println("getCustomer is called String");
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
			ResultSet resultSet = null;
			if(searchKeyword == null) {
				resultSet = st.executeQuery("select * from customer;");
			}else{
				resultSet = st.executeQuery("select * from customer where FirstName LIKE '%" + searchKeyword + "%' OR LastName LIKE '%" + searchKeyword + "';");
			}
			while(resultSet.next()){
				Customer customer = new Customer();
				String email = resultSet.getString("email");
				String lastName = resultSet.getString("LastName");
				String firstName = resultSet.getString("FirstName");
				String address = resultSet.getString("Address");
				String city = resultSet.getString("city");
				String state = resultSet.getString("state");
				String zipcode = resultSet.getString("ZipCode");
				String telephone = resultSet.getString("Telephone");
				String creditCardNum = resultSet.getString("CreditCardNumber");
				int rating = resultSet.getInt("Rating");
				String customerID = resultSet.getString("Id");

				customer.setEmail(email);
				customer.setFirstName(firstName);
				customer.setLastName(lastName);
				customer.setAddress(address);
				customer.setCreditCard(creditCardNum);
				customer.setTelephone(telephone);
				customer.setId(customerID);
				customer.setRating(rating);
				customers.add(customer);
			}

			System.out.println("I am able to connect in customer and print all");
			System.out.println(customers);
			try {
				myConnection.close();
				return customers;
			} catch (SQLException var25) {
			}
		} catch (SQLException var26) {
			System.out.println("Did not connect or put");
			System.out.println(var26.getMessage());
		} catch (ClassNotFoundException var29) {
			var29.printStackTrace();
		}

		return customers;
	}


	public Customer getHighestRevenueCustomer() {
		/*
		 * This method fetches the customer who generated the highest total revenue and returns it
		 * The students code to fetch data from the database will be written here
		 * The customer record is required to be encapsulated as a "Customer" class object
		 */


		return getDummyCustomer();
	}

	public Customer getCustomer(String customerID) {

		/*
		 * This method fetches the customer details and returns it
		 * customerID, which is the Customer's ID who's details have to be fetched, is given as method parameter
		 * The students code to fetch data from the database will be written here
		 * The customer record is required to be encapsulated as a "Customer" class object
		 */

		Connection myConnection = null;
		Customer customer = new Customer();
		try {
			String mysJDBCDriver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/new_schema?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			String userID = "root";
			String password1 = "12345678";

			Class.forName("com.mysql.jdbc.Driver");
			myConnection = DriverManager.getConnection(url, userID, password1);
			System.out.println("I am able to connect");
			Statement st = myConnection.createStatement();

			ResultSet resultSet = st.executeQuery(" select * from customer WHERE Id = '"+customerID+"'");
			while(resultSet.next()){
				String email = resultSet.getString("email");
				String lastName = resultSet.getString("LastName");
				String firstName = resultSet.getString("FirstName");
				String address = resultSet.getString("Address");
				String city = resultSet.getString("city");
				String state = resultSet.getString("state");
				String zipcode = resultSet.getString("ZipCode");
				String telephone = resultSet.getString("Telephone");
				String creditCardNum = resultSet.getString("CreditCardNumber");
				int rating = resultSet.getInt("Rating");

				customer.setEmail(email);
				customer.setFirstName(firstName);
				customer.setLastName(lastName);
				customer.setAddress(address);
				customer.setCreditCard(creditCardNum);
				customer.setTelephone(telephone);
				customer.setId(customerID);
				customer.setRating(rating);
			}

		}catch(Exception e){e.printStackTrace();}


		return customer;
	}
	
	public String deleteCustomer(String customerID) {

		/*
		 * This method deletes a customer returns "success" string on success, else returns "failure"
		 * The students code to delete the data from the database will be written here
		 * customerID, which is the Customer's ID who's details have to be deleted, is given as method parameter
		 */

		/*Sample data begins*/
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

			String query = " delete from customer WHERE Id = ?";
			PreparedStatement preparedStmt = myConnection.prepareStatement(query);
			preparedStmt.setString(1, customerID);
			preparedStmt.execute();
			System.out.println("Delete success");
			try {
				myConnection.close();
				return "success";
			} catch (SQLException var12) {
				return "success";
			}

		}catch(Exception e){e.printStackTrace();}


		return "failure";
		/*Sample data ends*/
		
	}


	public String getCustomerID(String email) {
		/*
		 * This method returns the Customer's ID based on the provided email address
		 * The students code to fetch data from the database will be written here
		 * username, which is the email address of the customer, who's ID has to be returned, is given as method parameter
		 * The Customer's ID is required to be returned as a String
		 */
		Connection myConnection = null;
		String id = "111-11-1111";
		try {
			String mysJDBCDriver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/new_schema?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			String userID = "root";
			String password1 = "12345678";

			Class.forName("com.mysql.jdbc.Driver");
			myConnection = DriverManager.getConnection(url, userID, password1);
			System.out.println("I am able to connect");
			Statement st = myConnection.createStatement();

			ResultSet rs = st.executeQuery("select * from customer where Email='"+email+"';");
			id = rs.getString("Id");

			try {
				myConnection.close();

			} catch (SQLException var12) {
			}

		}catch(Exception e){e.printStackTrace();}

		return id;

	}


	public String editCustomer(Customer customer) {

		/*
		 * All the values of the add customer form are encapsulated in the customer object.
		 * These can be accessed by getter methods (see Customer class in model package).
		 * e.g. firstName can be accessed by customer.getFirstName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database insertion of the customer details and return "success" or "failure" based on result of the database insertion.
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

			String query = " update customer set LastName = ?, FirstName = ?, Address = ?, City = ?, State = ?, ZipCode = ?, Telephone = ?, Email = ?, Rating = ?, CreditCardNumber = ? where Id = ?";
			PreparedStatement preparedStmt = myConnection.prepareStatement(query);
			preparedStmt.setString(1, customer.getLastName());
			preparedStmt.setString(2, customer.getFirstName());
			preparedStmt.setString(3, customer.getAddress());
			preparedStmt.setString(4, "");
			preparedStmt.setString(5, "");
			preparedStmt.setString(6, "");
			preparedStmt.setString(7, customer.getTelephone());
			//TODO: fix Fata too long for column 'Email' at row 1 Exception
			preparedStmt.setString(8, customer.getEmail());
			preparedStmt.setInt(9, customer.getRating());
			preparedStmt.setString(10, customer.getCreditCard());
			preparedStmt.setString(11, customer.getId());
			preparedStmt.executeUpdate();
			System.out.println("Customer is edited");
			try {
				myConnection.close();
				return "success";
			} catch (SQLException var16) {var16.printStackTrace(); }
		}catch(Exception e){
			e.printStackTrace();

		}
		return "failure";
		/*Sample data begins*/

		/*Sample data ends*/

	}

	public String addCustomer(Customer customer) {
		/*
		 * All the values of the edit customer form are encapsulated in the customer object.
		 * These can be accessed by getter methods (see Customer class in model package).
		 * e.g. firstName can be accessed by customer.getFirstName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database update and return "success" or "failure" based on result of the database update.
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

			String query = " insert into customer (LastName, FirstName, Address, City, State, ZipCode, Telephone, Email, Rating, CreditCardNumber, Id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = myConnection.prepareStatement(query);
			preparedStmt.setString(1, customer.getLastName());
			preparedStmt.setString(2, customer.getFirstName());
			preparedStmt.setString(3, customer.getAddress());
			preparedStmt.setString(4, "");
			preparedStmt.setString(5, "");
			preparedStmt.setString(6, "");
			preparedStmt.setString(7, customer.getTelephone());
			//TODO: fix Fata too long for column 'Email' at row 1 Exception
			preparedStmt.setString(8, customer.getEmail());
			preparedStmt.setInt(9, customer.getRating());
			preparedStmt.setString(10, customer.getCreditCard());
			preparedStmt.setString(11, customer.getId());
			preparedStmt.executeUpdate();
			System.out.println("Customer is added");
			try {
				myConnection.close();
				return "success";
			} catch (SQLException var16) {var16.printStackTrace(); }
		}catch(Exception e){
			e.printStackTrace();

		}
		return "failure";

	}

    public List<Customer> getCustomerMailingList() {

		/*
		 * This method fetches the all customer mailing details and returns it
		 * The students code to fetch data from the database will be written here
		 */

        return getDummyCustomerList();
    }

    public List<Customer> getAllCustomers() {
        /*
		 * This method fetches returns all customers
		 */
		List<Customer> customers = new ArrayList();
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
			ResultSet resultSet = st.executeQuery("select * from customer;");

			while(resultSet.next()){
				Customer customer = new Customer();
				String email = resultSet.getString("email");
				String lastName = resultSet.getString("LastName");
				String firstName = resultSet.getString("FirstName");
				String address = resultSet.getString("Address");
				String city = resultSet.getString("city");
				String state = resultSet.getString("state");
				String zipcode = resultSet.getString("ZipCode");
				String telephone = resultSet.getString("Telephone");
				String creditCardNum = resultSet.getString("CreditCardNumber");
				int rating = resultSet.getInt("Rating");
				String customerID = resultSet.getString("Id");

				customer.setEmail(email);
				customer.setFirstName(firstName);
				customer.setLastName(lastName);
				customer.setAddress(address);
				customer.setCreditCard(creditCardNum);
				customer.setTelephone(telephone);
				customer.setId(customerID);
				customer.setRating(rating);
				customers.add(customer);
			}

			System.out.println("I am able to connect in customer and print all");

			try {
				myConnection.close();
				return customers;
			} catch (SQLException var26) {
			}
		} catch (SQLException var27) {
			System.out.println("Did not connect or put");
			System.out.println(var27.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return customers;
    }
}
