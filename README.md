CSE 305 Project: StockTrade

We did our project a little differently from the solution that the professor gave to the previous homeworks. Our 
sql tables are not based on the solution and are created based on our implementation. The sql tables and demo data
are attached above. For the demo data, we only added stocks, initial customers, initial employees, a single manager and 
login information to start out with. We using IntelliJ to complete our project.


Database name is "new_schema"
Database username is "root"
Database password is "root"

Here are the last of initial informations. 
syang@cs.sunysb.edu,1,Customer
vicdu@cs.sunysb.edu,2, Customer
jsmith@ic.sunysb.edu,3, Customer
pml@cs.sunysb.edu,4, Customer
davidsm@cs.sunysb.edu,5, Customer Rep
davidwa@cs.sunysb.edu,6, Customer Rep
bob@cs.sunysb.edu,7, Manager

For the some of the stock transactions since it didn't explicit say how we should do it, we explain how we thought we 
should implement it to prevent confusion.

1. getActivelyTradedStocks() = Group by stockname and number of orders placed per stock. Return the stock with
                               the highest number or return more than one if more than one stock has the same max number. 

2. getStockSuggestions() = Return all stocks that the customer hasn't brought yet. 

3. getStocksByCustomer() = Returns all stocks the customer has brought, the number of shares when displayed is set to 0. 
