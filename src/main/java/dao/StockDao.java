package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.Customer;
import model.Location;
import model.Stock;

import javax.xml.transform.Result;

public class StockDao {

    static int start =0;
    public Stock getDummyStock() {
        Stock stock = new Stock();
        stock.setName("Apple");
        stock.setSymbol("AAPL");
        stock.setPrice(150.0);
        stock.setNumShares(1200);
        stock.setType("Technology");

        return stock;
    }

    public List<Stock> getDummyStocks() {
        List<Stock> stocks = new ArrayList<Stock>();

		/*Sample data begins*/
        for (int i = 0; i < 10; i++) {
            stocks.add(getDummyStock());
        }
		/*Sample data ends*/

        return stocks;
    }

	public List<Stock> getAllStocks() {
        List<Stock> stocks = new ArrayList<Stock>();
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
            ResultSet resultSet = myStatement.executeQuery("select * from Stock;");
            while (resultSet.next()) {
                Stock myStock = new Stock();
                myStock.setName(resultSet.getString("StockName"));
                myStock.setSymbol(resultSet.getString("StockSymbol"));
                myStock.setType(resultSet.getString("StockType"));
                myStock.setPrice(resultSet.getDouble("PricePerShare"));
                myStock.setNumShares(resultSet.getInt("NumberShares"));
                stocks.add(myStock);
            }
            try {
                myConnection.close();
            } catch (Exception e) {
                System.out.print(e);
            }
        }
        catch (Exception e) {
            System.out.print(e);
        }
		return stocks;
	}

    public Stock getStockBySymbol(String stockSymbol) {
        Stock myStock = new Stock();
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
            ResultSet resultSet = myStatement.executeQuery("select * from Stock where StockSymbol='"+stockSymbol+"';");
            while(resultSet.next()) {
                myStock.setName(resultSet.getString("StockName"));
                myStock.setSymbol(resultSet.getString("StockSymbol"));
                myStock.setType(resultSet.getString("StockType"));
                myStock.setPrice(resultSet.getDouble("PricePerShare"));
                myStock.setNumShares(resultSet.getInt("NumberShares"));
            }
            try {
                myConnection.close();
            } catch (Exception e) {
                System.out.print(e);
            }
        }
        catch(Exception e) {
            System.out.print(e);
        }
        return myStock;
    }

    public String setStockPrice(String stockSymbol, double stockPrice) {
        java.sql.Connection myConnection = null;

        //update stock prices
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
            //update stock prices
            String query = "UPDATE Stock SET PricePerShare = ? WHERE StockSymbol = ?";
            PreparedStatement preparedStmt = myConnection.prepareStatement(query);
            preparedStmt.setDouble (1, stockPrice);
            preparedStmt.setString (2, stockSymbol);
            preparedStmt.executeUpdate();

            try {
                myConnection.close();
            } catch (Exception e) {
                System.out.print(e);
            }
        }
        catch (Exception e) {
            System.out.print(e);
        }

        //update price history
        try {
            String mysJDBCDriver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/new_schema";
            String userID = "root";
            String password1 = "root";
            java.util.Properties mysys = System.getProperties();
            mysys.put("user", userID);
            mysys.put("password", password1);
            myConnection = DriverManager.getConnection(url, mysys);
            String query = " insert into PriceHistory values (?, ?, ?)";
            PreparedStatement preparedStmt = myConnection.prepareStatement(query);
            preparedStmt.setString(1, stockSymbol);
            preparedStmt.setDouble (2, stockPrice);
            preparedStmt.setTimestamp (3, new java.sql.Timestamp(new java.util.Date().getTime()));
            preparedStmt.executeUpdate();

            try {
                myConnection.close();
            } catch (Exception e) {
                System.out.print(e);
            }
        }
        catch (Exception e) {
            System.out.print(e);
        }

        return "success";
    }
	
	public List<Stock> getOverallBestsellers() {
		/*
		 * The students code to fetch data from the database will be written here
		 * Get list of bestseller stocks
		 */
        List<Stock> stocks = new ArrayList<Stock>();
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
            ResultSet resultSet = myStatement.executeQuery("SELECT S.*, SUM(O.NumShares * O.PricePerShare) AS Revenue FROM Stock S, Orderr O WHERE (S.PricePerShare = O.PricePerShare) GROUP BY S.StockSymbol ORDER BY Revenue DESC;");
            while (resultSet.next()) {
                Stock myStock = new Stock();
                myStock.setName(resultSet.getString("StockName"));
                myStock.setSymbol(resultSet.getString("StockSymbol"));
                myStock.setType(resultSet.getString("StockType"));
                myStock.setPrice(resultSet.getDouble("PricePerShare"));
                myStock.setNumShares(resultSet.getInt("NumberShares"));
                stocks.add(myStock);
            }
            try {
                myConnection.close();
            } catch (Exception e) {
                System.out.print(e);
            }
        }
        catch (Exception e) {
            System.out.print(e);
        }
        return stocks;
	}

    public List<Stock> getCustomerBestsellers(String customerID) {

		/*
		 * The students code to fetch data from the database will be written here.
		 * Get list of customer bestseller stocks
		 */
        List<Stock> stocks = new ArrayList<Stock>();
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
            ResultSet resultSet = myStatement.executeQuery("SELECT S.*, SUM(O.NumShares * O.PricePerShare) AS Revenue FROM Stock S, Orderr O, Trade T WHERE S.PricePerShare = O.PricePerShare AND T.OrderId = O.Id AND T.BrokerId IS NULL GROUP BY S.StockSymbol ORDER BY Revenue DESC;");
            while (resultSet.next()) {
                Stock myStock = new Stock();
                myStock.setName(resultSet.getString("StockName"));
                myStock.setSymbol(resultSet.getString("StockSymbol"));
                myStock.setType(resultSet.getString("StockType"));
                myStock.setPrice(resultSet.getDouble("PricePerShare"));
                myStock.setNumShares(resultSet.getInt("NumberShares"));
                stocks.add(myStock);
            }
            try {
                myConnection.close();
            } catch (Exception e) {
                System.out.print(e);
            }
        }
        catch (Exception e) {
            System.out.print(e);
        }
        return stocks;
    }

    public List<Stock> getStockPriceHistory(String stockSymbol) {

        List<Stock> stocks = new ArrayList<Stock>();
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
            ResultSet resultSet = myStatement.executeQuery("SELECT S.StockSymbol, S.StockName, S.StockType, P.PricePerShare, S.NumberShares, P.DateTime FROM Stock S, PriceHistory P WHERE S.StockSymbol = '"+stockSymbol+"' AND S.StockSymbol = P.StockSymbol ORDER BY P.DateTime DESC;");
            while (resultSet.next()) {
                Stock myStock = new Stock();
                myStock.setName(resultSet.getString("StockName"));
                myStock.setSymbol(resultSet.getString("StockSymbol"));
                myStock.setType(resultSet.getString("StockType"));
                myStock.setPrice(resultSet.getDouble("PricePerShare"));
                myStock.setNumShares(resultSet.getInt("NumberShares"));
                stocks.add(myStock);
            }
            try {
                myConnection.close();
            } catch (Exception e) {
                System.out.print(e);
            }
        }
        catch (Exception e) {
            System.out.print(e);
        }
        return stocks;
    }

	public List getStocksByCustomer(String customerId) {
        List<Stock> stocks = new ArrayList<Stock>();
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
            ResultSet resultSet = myStatement.executeQuery("select * from trade T, stock S where T.StockId = S.StockName and T.AccountId='" + customerId +"'GROUP BY T.StockId");
            while(resultSet.next()){
                Stock stock = new Stock();
                stock.setName(resultSet.getString("StockName"));
                stock.setSymbol(resultSet.getString("StockSymbol"));
                stock.setPrice(resultSet.getDouble("PricePerShare"));
                stock.setType(resultSet.getString("StockType"));
                stocks.add(stock);
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
		return stocks;
	}

    public List<Stock> getActivelyTradedStocks() {
        List<Stock> stocks = new ArrayList<Stock>();
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
            ResultSet resultSet = myStatement.executeQuery("SELECT StockId, COUNT(*) AS Number FROM trade GROUP BY StockId ORDER BY Number DESC");
            int number=0;
            int counter =0;
            while(resultSet.next()){
                if(counter!=0 && resultSet.getInt("Number")<number){
                    break;
                }
                else{
                    number = resultSet.getInt("Number");
                }
                String stock = resultSet.getString("StockId");
                stocks.add(getStocksByName(stock).get(0));
                counter++;
            }
        }
        catch (Exception e){
            System.out.print(e);
        }

        return stocks;
    }

    public List<Stock> getStockSuggestions(String customerID) {
        List<Stock> stocks = new ArrayList<Stock>();
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
            if(start==1) {
                myStatement.executeUpdate("DROP View stocklist");
            }
            myStatement.executeUpdate("CREATE VIEW stocklist AS SELECT t.StockId FROM trade t, stock S WHERE t.AccountId ='" + customerID + "' and t.StockId = S.StockName GROUP BY t.StockId");
            start =1;

           ResultSet resultSet = myStatement.executeQuery("SELECT StockName FROM stock WHERE StockName NOT IN (SELECT StockId From stocklist)");
            while(resultSet.next()){
                stocks.add(getStocksByName(resultSet.getString("StockName")).get(0));
            }
        }
        catch (Exception e){
            System.out.print(e);
        }

        return stocks;

    }

    public List<Stock> getStocksByName(String name) {
        List<Stock> stocks = new ArrayList<Stock>();
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
            ResultSet resultSet = myStatement.executeQuery("select * from Stock where StockName='" + name + "';");
            while (resultSet.next()) {
                Stock myStock = new Stock();
                myStock.setName(resultSet.getString("StockName"));
                myStock.setSymbol(resultSet.getString("StockSymbol"));
                myStock.setType(resultSet.getString("StockType"));
                myStock.setPrice(resultSet.getDouble("PricePerShare"));
                myStock.setNumShares(resultSet.getInt("NumberShares"));
                stocks.add(myStock);
            }
            try {
                myConnection.close();
            } catch (Exception e) {
                System.out.print(e);
            }
        }
        catch (Exception e) {
            System.out.print(e);
        }

        return stocks;
    }

    public List<String> getStockTypes() {
        List<String> types = new ArrayList<String>();
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
            ResultSet resultSet = myStatement.executeQuery("select DISTINCT(StockType) from Stock;");
            while (resultSet.next()) {
                types.add(resultSet.getString("StockType"));
            }
        }
        catch (Exception e) {
            System.out.print(e);
        }
        return types;
    }

    public List<Stock> getStockByType(String stockType) {
        List<Stock> stocks = new ArrayList<Stock>();
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
            ResultSet resultSet = myStatement.executeQuery("select * from Stock where StockType='" + stockType + "';");
            while(resultSet.next()) {
                Stock myStock = new Stock();
                myStock.setName(resultSet.getString("StockName"));
                myStock.setSymbol(resultSet.getString("StockSymbol"));
                myStock.setType(resultSet.getString("StockType"));
                myStock.setPrice(resultSet.getDouble("PricePerShare"));
                myStock.setNumShares(resultSet.getInt("NumberShares"));
                stocks.add(myStock);
            }
            try {
                myConnection.close();
            } catch (Exception e) {
                System.out.print(e);
            }
        }
        catch (Exception e) {
            System.out.print(e);
        }
        return stocks;
    }
}
