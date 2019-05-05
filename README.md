CSE 305 Project: StockTrade

We did our project a little differently from the solution that the professor gave to the previous homeworks. Our 
sql tables are not based on the solution and are created based on our implementation. The sql tables and demo data
are attached above. For the demo data, we only added stocks, initial customers, initial employees, a single manager and 
login information to start out with. We using IntelliJ to complete our project.


Database name is "new_schema" <br/>
Database username is "root" <br/>
Database password is "root" <br/>

Here are the last of initial informations.  <br/>
syang@cs.sunysb.edu,1,Customer <br/>
vicdu@cs.sunysb.edu,2, Customer <br/>
jsmith@ic.sunysb.edu,3, Customer <br/>
pml@cs.sunysb.edu,4, Customer <br/>
davidsm@cs.sunysb.edu,5, Customer Rep <br/>
davidwa@cs.sunysb.edu,6, Customer Rep <br/>
bob@cs.sunysb.edu,7, Manager <br/>

For the some of the stock transactions since it didn't explicit say how we should do it, we explain how we thought we 
should implement it to prevent confusion.

1. getActivelyTradedStocks() = Group by stockname and number of orders placed per stock. Return the stock with
                               the highest number or return more than one if more than one stock has the same max number. 

2. getStockSuggestions() = Return all stocks that the customer hasn't brought yet. 

3. getStocksByCustomer() = Returns all stocks the customer has brought, the number of shares when displayed is set to 0. 
