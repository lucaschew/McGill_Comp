-- Include your drop table DDL statements in this file.
-- Make sure to terminate each statement with a semicolon (;)

-- LEAVE this statement on. It is required to connect to your database.
CONNECT TO COMP421;

-- Remember to put the drop table ddls for the tables with foreign key references
--    BEFORE the ddls to drop the parent tables (reverse of the creation order).

Drop table ContainsMenuItem;

Drop Table Reservation;

Drop Table Recommendation;

Drop Table AccountFollowing;

Drop Table Menu;

Drop Table BusinessHour;

Drop Table Address;

Drop Table MenuItem;

Drop Table Review;

Drop Table Restaurant;

Drop Table BusinessAccount;

Drop Table RegularAccount;
