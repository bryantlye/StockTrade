#customers
INSERT INTO Customer (LastName, FirstName, Address, City, State, ZipCode, Telephone, Email, Rating, CreditCardNumber, Id)
VALUES ('Yang', 'Shang', '123 Success Street', 'Stony Brook', 'NY', '11790', '5166328959', 'syang@cs.sunysb.edu', 1, 234567812345678, 111111111);
INSERT INTO Customer (LastName, FirstName, Address, City, State, ZipCode, Telephone, Email, Rating, CreditCardNumber, Id)
VALUES ('Du', 'Victor', '456 Fortune Road', 'Stony Brook', 'NY', '11790', '5166324360', 'vicdu@cs.sunysb.edu', 1, 5678123456781234, 222222222);
INSERT INTO Customer (LastName, FirstName, Address, City, State, ZipCode, Telephone, Email, Rating, CreditCardNumber, Id)
VALUES ('Smith', 'John', '789 Peace Blvd', 'Los Angeles', 'CA', '93536', '3154434321', 'jsmith@ic.sunysb.edu', 1, 2345678923456789, 333333333);
INSERT INTO Customer (LastName, FirstName, Address, City, State, ZipCode, Telephone, Email, Rating, CreditCardNumber, Id)
VALUES ('Philip', 'Lewis', '135 Knowledge Lane', 'Stony Brook', 'NY', '11794', '5166668888', 'pml@cs.sunysb.edu', 1, 6789234567892345, 444444444);

INSERT INTO Employee (LastName, FirstName, Address, City, State, ZipCode, Telephone, ID, SSN, StartDate, HourlyRate, Email)
VALUES ('Smith', 'David', '123 College Road', 'Stony Brook', 'NY', '11790', 5162152345,'22', 123456789, '2005-11-01 00:00:00', 60, 'davidsm@cs.sunysb.edu');
INSERT INTO Employee (LastName, FirstName, Address, City, State, ZipCode, Telephone, ID, SSN, StartDate, HourlyRate, Email)
VALUES ('Warren', 'David', '456 Sunken Street', 'Stony Brook', 'NY', '11794', 6316329987,'11', 789123456, '2006-02-02 00:00:00', 50,'davidwa@cs.sunysb.edu');

INSERT INTO Login()
VALUES('syang@cs.sunysb.edu','1','Customer');
INSERT INTO Login()
VALUES('vicdu@cs.sunysb.edu','2','Customer');
INSERT INTO Login()
VALUES('jsmith@ic.sunysb.edu','3','Customer');
INSERT INTO Login()
VALUES('pml@cs.sunysb.edu','4','Customer');
INSERT INTO Login()
VALUES('davidsm@cs.sunysb.edu','5','customerRepresentative');
INSERT INTO Login()
VALUES('davidwa@cs.sunysb.edu','6','customerRepresentative');
INSERT INTO Login()
VALUES('bob@cs.sunysb.edu','7','Manager');

#stocks
INSERT INTO Stock (StockSymbol, StockName, StockType,PricePerShare, NumberShares)
VALUES ('GM', 'General Motors', 'automotive', 34.23, 1000);

INSERT INTO Stock (StockSymbol, StockName, StockType, PricePerShare, NumberShares)
VALUES ('IBM', 'IBM', 'computer', 91.41, 500);

INSERT INTO Stock (StockSymbol, StockName, StockType, PricePerShare, NumberShares)
VALUES ('F', 'Ford', 'automotive', 9.0, 750);

