-- Include your create table DDL statements in this file.
-- Make sure to terminate each statement with a semicolon (;)

-- LEAVE this statement on. It is required to connect to your database.
CONNECT TO COMP421;

-- Remember to put the create table ddls for the tables with foreign key references
--    ONLY AFTER the parent tables have already been created.

Create Table RegularAccount
(
	email varchar(30) Not Null,
	username varchar(50) Not Null Unique,
	password varchar(30) Not Null,
	phoneNumber char(10) Not Null Unique,
	primary key(email)
);

Create Table BusinessAccount
(
	email varchar(30) Not Null,
	username varchar(50) Not Null Unique,
	password varchar(30) Not Null,
	phoneNumber char(10) Not Null Unique,
	primary key(email)
);

Create Table Restaurant
(
	restaurantId varchar(30) Not Null,
	name varchar(50) Not Null,
	phoneNumber char(10) Not Null Unique,
	ownerEmail varchar(30) Not Null,
	primary key(restaurantId),
	foreign key(ownerEmail) references BusinessAccount(email)
);

Create Table Review
(
	reviewId varchar(30) Not Null,
	rating Integer Not Null,
	comment varchar(300) Not Null,
	postedAt datetime Not Null,
	accountEmail varchar(30) Not Null,
	restaurantId varchar(30) Not Null,
	primary key(reviewId),
	foreign key(accountEmail) references RegularAccount(email),
	foreign key(restaurantId) references Restaurant(restaurantId)
);

Create Table MenuItem
(
	itemId varchar(30) Not Null,
	name varchar(50) Not Null,
	price decimal(19, 2) Not Null,
	description varchar(300) Not Null,
	primary key(itemId)
);

Create Table Address
(
	street varchar(30) Not Null,
	unit integer Not Null,
	city varchar(30) Not Null,
	province varchar(30) Not Null,
	zipCode varchar(6) Not Null,
	restaurantId varchar(30) Not Null,
	primary key(unit, zipCode),
	foreign key(restaurantId) references Restaurant(restaurantId)
);

Create Table BusinessHour
(
	day integer Not Null,
	openTime time,
	closeTime time,
	restaurantId varchar(30) Not Null,
	primary key(day, restaurantId),
	foreign key(restaurantId) references Restaurant(restaurantId)
);

Create Table Menu
(
	menuName varchar(50) Not Null,
	isValid char(1) Not Null,
	restaurantId varchar(30) Not Null,
	primary key(menuName, restaurantId),
	foreign key(restaurantId) references Restaurant(restaurantId)
);

Create Table AccountFollowing
(
	account varchar(50) Not Null,
	follower varchar(50) Not Null,
	primary key(account, follower),
	foreign key(account) references RegularAccount(email),
	foreign key(follower) references RegularAccount(email)
);

Create Table Recommendation
(
	accountEmail varchar(30) Not NUll,
	restaurantId varchar(30) Not Null,
	primary key(accountEmail, restaurantId),
	foreign key(accountEmail) references RegularAccount(email),
	foreign key(restaurantId) references Restaurant(restaurantId)
);

Create Table Reservation
(
	date datetime Not Null,
	numberOfPeople Integer Not Null,
	isValid char(1) Not Null,
	accountEmail varchar(30) Not Null,
	restaurantId varchar(30) Not Null,
	primary key(date, accountEmail),
	foreign key(accountEmail) references RegularAccount(email),
	foreign key(restaurantId) references Restaurant(restaurantId)
);

Create Table ContainsMenuItem
(
	menuName varchar(50) Not Null,
	restaurantId varchar(30) Not Null,
	itemId varchar(30) Not Null,
	primary key(menuName, restaurantId, itemId),
	foreign key(menuName, restaurantId) references Menu(menuName, restaurantId),
	foreign key(restaurantId) references Restaurant(restaurantId),
	foreign key(itemId) references MenuItem(itemId)
);
