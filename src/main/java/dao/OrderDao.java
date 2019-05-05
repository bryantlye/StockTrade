package dao;

import com.sun.org.apache.xpath.internal.operations.Or;
import jdk.nashorn.internal.ir.CatchNode;
import model.*;
import sun.security.ssl.HandshakeInStream;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDao {

    static int orderid = 15;

    public List<OrderPriceEntry> getOrderPriceHistory(String orderId) {

        /*
         * The students code to fetch data from the database will be written here
         * Query to view price history of hidden stop order or trailing stop order
         * Use setPrice to show hidden-stop price and trailing-stop price
         */
        List<OrderPriceEntry> orderPriceHistory = new ArrayList<OrderPriceEntry>();

        for (int i = 0; i < 10; i++) {
            OrderPriceEntry entry = new OrderPriceEntry();
            entry.setOrderId(orderId);
            entry.setDate(new Date());
            entry.setStockSymbol("aapl");
            entry.setPricePerShare(150.0);
            entry.setPrice(100.0);
            orderPriceHistory.add(entry);
        }
        return orderPriceHistory;
    }

    public String submitOrder(Order order, Customer customer, Employee employee, Stock stock) {
        Class cls = order.getClass();
        String type=cls.getName().substring(6, cls.getName().length()-5);

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
            String query = " insert into Orderr values (?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = myConnection.prepareStatement(query);
            preparedStmt.setInt(1, order.getNumShares());
            preparedStmt.setDouble (2, stock.getPrice());
            preparedStmt.setInt (3, orderid);
            preparedStmt.setDate (4, new java.sql.Date(new java.util.Date().getTime()));
            preparedStmt.setString (5, type);
            preparedStmt.executeUpdate();

            if(type.equals("TrailingStop")) {
                TrailingStopOrder temp = (TrailingStopOrder) order;
                query = " insert into TrailingStop values (?, ?)";
                preparedStmt = myConnection.prepareStatement(query);
                preparedStmt.setInt(1, orderid);
                preparedStmt.setDouble (2, temp.getPercentage());
                preparedStmt.executeUpdate();
            }
            else if(type.equals("HiddenStop")){
                HiddenStopOrder temp = (HiddenStopOrder) order;
                query = " insert into HiddenStop values (?, ?)";
                preparedStmt = myConnection.prepareStatement(query);
                preparedStmt.setInt(1, orderid);
                preparedStmt.setDouble (2, temp.getPricePerShare());
                preparedStmt.executeUpdate();
            }
            else if(type.equals("Market")){
                MarketOrder temp = (MarketOrder) order;
                query = " insert into Market values (?, ?)";
                preparedStmt = myConnection.prepareStatement(query);
                preparedStmt.setInt(1, orderid);
                preparedStmt.setString (2, temp.getBuySellType());
                preparedStmt.executeUpdate();
            }
            else if(type.equals("MarketOnClose")){
                MarketOnCloseOrder temp = (MarketOnCloseOrder) order;
                query = " insert into MarketClose values (?, ?)";
                preparedStmt = myConnection.prepareStatement(query);
                preparedStmt.setInt(1, orderid);
                preparedStmt.setString (2, temp.getBuySellType());
                preparedStmt.executeUpdate();
            }

            query = " insert into Trade values (?, ?, ?, ?)";
            preparedStmt = myConnection.prepareStatement(query);
            preparedStmt.setString(1, customer.getId());
            if(employee!=null) {
                preparedStmt.setInt(2, Integer.parseInt(employee.getId()));
            }
            else{
                preparedStmt.setString(2, null);
            }
            preparedStmt.setInt(3,orderid);
            preparedStmt.setString(4,stock.getName());
            preparedStmt.executeUpdate();
            orderid++;

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

    public List<Order> getOrderByStockSymbol(String stockSymbol) {
        List<Order> orders = new ArrayList<Order>();
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
            Statement myStatement2 = myConnection.createStatement();
            ResultSet resultSet = myStatement.executeQuery("select * from Trade T, stock S, orderr O where T.StockId = S.StockName and T.orderId = O.Id and S.stockSymbol='"+stockSymbol+"';");
            while(resultSet.next()){
                if(resultSet.getString("PriceType").equals("Market")){
                    ResultSet resultSet2 = myStatement2.executeQuery("select * from market where Id='"+resultSet.getString("OrderId")+"';");
                    if(resultSet2.next()) {
                        MarketOrder order = new MarketOrder();
                        order.setBuySellType(resultSet2.getString("type"));
                        order.setId(Integer.parseInt(resultSet.getString("OrderId")));
                        order.setDatetime(resultSet.getDate("DateTime"));
                        order.setNumShares((int) resultSet.getDouble("PricePerShare"));
                        orders.add(order);
                    }
                }
                else if(resultSet.getString("PriceType").equals("MarketOnClose")) {
                    ResultSet resultSet2 = myStatement2.executeQuery("select * from marketclose where Id='"+resultSet.getString("OrderId")+"';");
                    if(resultSet2.next()) {
                        MarketOnCloseOrder order = new MarketOnCloseOrder();
                        order.setBuySellType(resultSet2.getString("type"));
                        order.setId(Integer.parseInt(resultSet.getString("OrderId")));
                        order.setDatetime(resultSet.getDate("DateTime"));
                        order.setNumShares((int) resultSet.getDouble("PricePerShare"));
                        orders.add(order);
                    }
                }
                else if(resultSet.getString("PriceType").equals("TrailingStop")) {
                    ResultSet resultSet2 = myStatement2.executeQuery("select * from trailingstop where Id='"+resultSet.getString("OrderId")+"';");
                    if(resultSet2.next()) {
                        TrailingStopOrder order = new TrailingStopOrder();
                        order.setPercentage(resultSet2.getDouble("Percentage"));
                        order.setId(Integer.parseInt(resultSet.getString("OrderId")));
                        order.setDatetime(resultSet.getDate("DateTime"));
                        order.setNumShares((int) resultSet.getDouble("PricePerShare"));
                        orders.add(order);
                    }
                }
                else if(resultSet.getString("PriceType").equals("HiddenStop")) {
                    ResultSet resultSet2 = myStatement2.executeQuery("select * from hiddenstop where Id='"+resultSet.getString("OrderId")+"';");
                    if(resultSet2.next()) {
                        HiddenStopOrder order = new HiddenStopOrder();
                        order.setPricePerShare(resultSet2.getDouble("price"));
                        order.setId(Integer.parseInt(resultSet.getString("OrderId")));
                        order.setDatetime(resultSet.getDate("DateTime"));
                        order.setNumShares((int) resultSet.getDouble("PricePerShare"));
                        orders.add(order);
                    }
                }
            }
        }
        catch(Exception e){
            System.out.print(e);
        }
        return orders;
    }

    public List<Order> getOrderByCustomerName(String customerName) {
        List<Order> orders = new ArrayList<Order>();
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
            Statement myStatement2 = myConnection.createStatement();
            ResultSet resultSet = myStatement.executeQuery("select * from Trade T, orderr O, customer C where T.OrderId = O.Id and C.Id = T.AccountId and C.firstName ='"+customerName+"';");
            while(resultSet.next()){
                if(resultSet.getString("PriceType").equals("Market")){
                    ResultSet resultSet2 = myStatement2.executeQuery("select * from market where Id='"+resultSet.getString("OrderId")+"';");
                    if(resultSet2.next()) {
                        MarketOrder order = new MarketOrder();
                        order.setBuySellType(resultSet2.getString("type"));
                        order.setId(Integer.parseInt(resultSet.getString("OrderId")));
                        order.setDatetime(resultSet.getDate("DateTime"));
                        order.setNumShares((int) resultSet.getDouble("PricePerShare"));
                        orders.add(order);
                    }
                }
                else if(resultSet.getString("PriceType").equals("MarketOnClose")) {
                    ResultSet resultSet2 = myStatement2.executeQuery("select * from marketclose where Id='"+resultSet.getString("OrderId")+"';");
                    if(resultSet2.next()) {
                        MarketOnCloseOrder order = new MarketOnCloseOrder();
                        order.setBuySellType(resultSet2.getString("type"));
                        order.setId(Integer.parseInt(resultSet.getString("OrderId")));
                        order.setDatetime(resultSet.getDate("DateTime"));
                        order.setNumShares((int) resultSet.getDouble("PricePerShare"));
                        orders.add(order);
                    }
                }
                else if(resultSet.getString("PriceType").equals("TrailingStop")) {
                    ResultSet resultSet2 = myStatement2.executeQuery("select * from trailingstop where Id='"+resultSet.getString("OrderId")+"';");
                    if(resultSet2.next()) {
                        TrailingStopOrder order = new TrailingStopOrder();
                        order.setPercentage(resultSet2.getDouble("Percentage"));
                        order.setId(Integer.parseInt(resultSet.getString("OrderId")));
                        order.setDatetime(resultSet.getDate("DateTime"));
                        order.setNumShares((int) resultSet.getDouble("PricePerShare"));
                        orders.add(order);
                    }
                }
                else if(resultSet.getString("PriceType").equals("HiddenStop")) {
                    ResultSet resultSet2 = myStatement2.executeQuery("select * from hiddenstop where Id='"+resultSet.getString("OrderId")+"';");
                    if(resultSet2.next()) {
                        HiddenStopOrder order = new HiddenStopOrder();
                        order.setPricePerShare(resultSet2.getDouble("price"));
                        order.setId(Integer.parseInt(resultSet.getString("OrderId")));
                        order.setDatetime(resultSet.getDate("DateTime"));
                        order.setNumShares((int) resultSet.getDouble("PricePerShare"));
                        orders.add(order);
                    }
                }
            }
        }
        catch(Exception e){
            System.out.print(e);
        }
        return orders;
    }

    public List<Order> getOrderHistory(String customerId) {
        List<Order> orders = new ArrayList<Order>();
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
            Statement myStatement2 = myConnection.createStatement();
            ResultSet resultSet = myStatement.executeQuery("select * from Trade T, orderr O where T.OrderId=O.Id and T.AccountId='"+customerId+"';");
            while(resultSet.next()) {
                if(resultSet.getString("PriceType").equals("Market")){
                    ResultSet resultSet2 = myStatement2.executeQuery("select * from market where Id='"+resultSet.getString("OrderId")+"';");
                    if(resultSet2.next()) {
                        MarketOrder order = new MarketOrder();
                        order.setBuySellType(resultSet2.getString("type"));
                        order.setId(Integer.parseInt(resultSet.getString("OrderId")));
                        order.setDatetime(resultSet.getDate("DateTime"));
                        order.setNumShares((int) resultSet.getDouble("PricePerShare"));
                        orders.add(order);
                    }
                }
                else if(resultSet.getString("PriceType").equals("MarketOnClose")) {
                    ResultSet resultSet2 = myStatement2.executeQuery("select * from marketclose where Id='"+resultSet.getString("OrderId")+"';");
                    if(resultSet2.next()) {
                        MarketOnCloseOrder order = new MarketOnCloseOrder();
                        order.setBuySellType(resultSet2.getString("type"));
                        order.setId(Integer.parseInt(resultSet.getString("OrderId")));
                        order.setDatetime(resultSet.getDate("DateTime"));
                        order.setNumShares((int) resultSet.getDouble("PricePerShare"));
                        orders.add(order);
                    }
                }
                else if(resultSet.getString("PriceType").equals("TrailingStop")) {
                    ResultSet resultSet2 = myStatement2.executeQuery("select * from trailingstop where Id='"+resultSet.getString("OrderId")+"';");
                    if(resultSet2.next()) {
                        TrailingStopOrder order = new TrailingStopOrder();
                        order.setPercentage(resultSet2.getDouble("Percentage"));
                        order.setId(Integer.parseInt(resultSet.getString("OrderId")));
                        order.setDatetime(resultSet.getDate("DateTime"));
                        order.setNumShares((int) resultSet.getDouble("PricePerShare"));
                        orders.add(order);
                    }
                }
                else if(resultSet.getString("PriceType").equals("HiddenStop")) {
                    ResultSet resultSet2 = myStatement2.executeQuery("select * from hiddenstop where Id='"+resultSet.getString("OrderId")+"';");
                    if(resultSet2.next()) {
                        HiddenStopOrder order = new HiddenStopOrder();
                        order.setPricePerShare(resultSet2.getDouble("price"));
                        order.setId(Integer.parseInt(resultSet.getString("OrderId")));
                        order.setDatetime(resultSet.getDate("DateTime"));
                        order.setNumShares((int) resultSet.getDouble("PricePerShare"));
                        orders.add(order);
                    }
                }
            }
        }
        catch (Exception e) {
            System.out.print(e);
        }
        return orders;
    }
}
