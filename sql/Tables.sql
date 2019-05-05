use new_schema;

CREATE TABLE Employee (
	LastName CHAR(20),
    FirstName CHAR(20),
    Email CHAR(40),	
    Address CHAR(50),
    City CHAR(20),
    State CHAR(20),
	ZipCode CHAR(20),
	Telephone CHAR(20),
	ID INTEGER,
	SSN INTEGER,
	StartDate DATE,
	HourlyRate DOUBLE,
	PRIMARY KEY (ID),
    CHECK(ID>0 and ID<99999)
);

CREATE TABLE Login(
	UserName CHAR(50),
    Password CHAR(30),
    Role CHAR(30)
);

CREATE TABLE Customer (
	LastName CHAR(20),
    FirstName CHAR(20),
    Address CHAR(50),
    City CHAR(20),
    State CHAR(20),
	ZipCode CHAR(20),
	Telephone CHAR(20),
	Email CHAR(40),
	Rating INTEGER,
	CreditCardNumber CHAR(50),
	Id  INTEGER,
	PRIMARY KEY (Id),
    CHECK(Id>0)
);

CREATE TABLE Account (
	Id CHAR(20),
	DateOpened DATE,
	clientId INTEGER,
	PRIMARY KEY (Id),
	FOREIGN KEY (clientId) REFERENCES Customer (Id)
		ON DELETE NO ACTION
		ON UPDATE CASCADE, 
    CHECK (clientId>0)
);

CREATE TABLE Stock (
	StockSymbol CHAR(20) NOT NULL,
	StockName CHAR(20) NOT NULL,	
	StockType CHAR(20) NOT NULL,
	PricePerShare DOUBLE,
    NumberShares INTEGER,
	PRIMARY KEY (StockSymbol) 
);
	
CREATE TABLE HasStock(
	clientId INTEGER,
    StockSymbol CHAR(20) NOT NULL,
    NumberShares INTEGER,
    PRIMARY KEY(clientId, StockSymbol),
    FOREIGN KEY (clientId) REFERENCES Customer (Id)
		ON DELETE NO ACTION
		ON UPDATE CASCADE, 
	FOREIGN KEY (StockSymbol) REFERENCES Stock (StockSymbol)
		ON DELETE NO ACTION
		ON UPDATE CASCADE
);

CREATE TABLE Orderr (
	NumShares INTEGER,
	PricePerShare DOUBLE,
	Id INTEGER,
	DateTime DATETIME,
	PriceType CHAR(20),
	PRIMARY KEY (Id),
    CHECK(PriceType IN(‘Market’, ‘MarketOnClose’, ‘TrailingStop’, ‘HiddenStop’))
);

CREATE TABLE Trade (
	AccountId CHAR(20),
	BrokerId INTEGER,
	OrderId INTEGER,
	StockId CHAR(20),
	PRIMARY KEY (AccountId, OrderId, StockId)
);

CREATE TABLE TrailingStop(
	Id INTEGER,
    Percentage DOUBLE
);

CREATE TABLE HiddenStop(
	Id INTEGER,
    price DOUBLE
);

CREATE TABLE Market(
	Id INTEGER,
    type CHAR(10)
);

CREATE TABLE MarketClose(
	Id INTEGER,
    type CHAR(10)
); 


