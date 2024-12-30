-- Include your INSERT SQL statements in this file.
-- Make sure to terminate each statement with a semicolon (;)

-- LEAVE this statement on. It is required to connect to your database.
CONNECT TO COMP421;

-- Remember to put the INSERT statements for the tables with foreign key references
--    ONLY AFTER the insert for the parent tables!

-- This is only an example of how you add INSERT statements to this file.
--   You may remove it.
--INSERT INTO MYTEST01 (id, value) VALUES(4, 1300);

-- A more complex syntax that saves you typing effort.
--INSERT INTO MYTEST01 (id, value) VALUES
-- (7, 5144),
-- (3, 73423),
--(6, -1222)
--;

-- CREATING ALL THE USER ACCOUNTS FIRST
-- BUSINESS ACCOUNTS 
INSERT INTO BUSINESSACCOUNT VALUES ('daldongnae@mail.com', 'daldongnae', 'KOREANBBQ', '5148781111');
INSERT INTO BUSINESSACCOUNT VALUES ('copperbranch@mail.com', 'copperbranch', 'veganfood123', '4383856262');
INSERT INTO BUSINESSACCOUNT VALUES ('aw@mail.com', 'aw', 'burgersaregood', '5148496886');
INSERT INTO BUSINESSACCOUNT VALUES ('mitsuki@mail.com', 'mitsuki', 'sushi030', '4503294228');
INSERT INTO BUSINESSACCOUNT VALUES ('timhortons@mail.ca', 'tims', 'coffeeeeee', '5146879000');
INSERT INTO BUSINESSACCOUNT VALUES ('sushiyo@mail.com', 'sushiyo', 'pokepoke', '5149397474');

-- REGULAR USERS
INSERT INTO REGULARACCOUNT VALUES ('adney@mail.com','adney','KcBEKanD0F','9021546686');
INSERT INTO REGULARACCOUNT VALUES ('aldo@gmail.com','aldo','0rPZkcHFue','8079096041');
INSERT INTO REGULARACCOUNT VALUES ('aleyn@hotmail.com','aleyn','p88VxcA3iM','9021248847');
INSERT INTO REGULARACCOUNT VALUES ('alford@yahoo.com','alford','wyAs0RqDlR','5068760813');
INSERT INTO REGULARACCOUNT VALUES ('amherst@mail.com','amherst','tQxiDX3pCN','8679242586');
INSERT INTO REGULARACCOUNT VALUES ('angel@gmail.com','angel','ycLapim86t','8675656024');
INSERT INTO REGULARACCOUNT VALUES ('anson@hotmail.com','anson','IxX5puQJCB','4183688205');
INSERT INTO REGULARACCOUNT VALUES ('archibald@yahoo.com','archibald','EePLu2Gk1o','5879735382');
INSERT INTO REGULARACCOUNT VALUES ('aries@mail.com','aries','ApccFt0MQe','2266499115');
INSERT INTO REGULARACCOUNT VALUES ('arwen@gmail.com','arwen','I72fjyK8x6','8255194248');
INSERT INTO REGULARACCOUNT VALUES ('astin@hotmail.com','astin','Mjh9XXgCkZ','2637059236');
INSERT INTO REGULARACCOUNT VALUES ('atley@yahoo.com','atley','m8wBACpRrj','5878053077');
INSERT INTO REGULARACCOUNT VALUES ('atwell@mail.com','atwell','NHl3hrDtkQ','2506957282');
INSERT INTO REGULARACCOUNT VALUES ('audie@gmail.com','audie','P80lXlEXwu','6138067637');
INSERT INTO REGULARACCOUNT VALUES ('ayers@yahoo.com','ayers','fqCzLky63F','5193930486');
INSERT INTO REGULARACCOUNT VALUES ('baker@mail.com','baker','R5pVH6rHEM','5146085259');
INSERT INTO REGULARACCOUNT VALUES ('balder@gmail.com','balder','FekFRD5ziA','4507083207');
INSERT INTO REGULARACCOUNT VALUES ('ballentine@hotmail.com','ballentine','ILwIyFSkJC','2508665986');
INSERT INTO REGULARACCOUNT VALUES ('bardalph@yahoo.com','bardalph','g9A1c3aCIe','3065016134');
INSERT INTO REGULARACCOUNT VALUES ('barker@mail.com','barker','dwfjgMD1ZF','3437295532');
INSERT INTO REGULARACCOUNT VALUES ('barric@gmail.com','barric','iD3BXG7CvU','5871068034');
INSERT INTO REGULARACCOUNT VALUES ('bayard@hotmail.com','bayard','q5YSDBvPH6','8673247686');
INSERT INTO REGULARACCOUNT VALUES ('bishop@yahoo.com','bishop','HjVpuNcRmP','5846081157');
INSERT INTO REGULARACCOUNT VALUES ('blaan@mail.com','blaan','5LK1OEbZh9','6477142903');
INSERT INTO REGULARACCOUNT VALUES ('blackburn@gmail.com','blackburn','sB22pTs4fc','3065029589');
INSERT INTO REGULARACCOUNT VALUES ('blade@hotmail.com','blade','M6JX9g0skQ','3658560199');
INSERT INTO REGULARACCOUNT VALUES ('blaine@yahoo.com','blaine','EjxMz6Ymwl','8078884854');
INSERT INTO REGULARACCOUNT VALUES ('blaze@mail.com','blaze','fLmBngRt49','8256447365');
INSERT INTO REGULARACCOUNT VALUES ('bramwell@gmail.com','bramwell','D3VWS0HUBC','4373624273');
INSERT INTO REGULARACCOUNT VALUES ('brant@hotmail.com','brant','QVJnrMxhOY','7097926812');
INSERT INTO REGULARACCOUNT VALUES ('brawley@yahoo.com','brawley','OanBNA3y3Z','8671619496');
INSERT INTO REGULARACCOUNT VALUES ('breri@mail.com','breri','PmeXBZf0dw','5879331624');
INSERT INTO REGULARACCOUNT VALUES ('briar@gmail.com','briar','qxDBWmOVsD','2892208542');
INSERT INTO REGULARACCOUNT VALUES ('brighton@hotmail.com','brighton','SsGFG6qzCO','6472358108');
INSERT INTO REGULARACCOUNT VALUES ('broderick@yahoo.com','broderick','vwUrE5C2EL','7787449478');
INSERT INTO REGULARACCOUNT VALUES ('bronson@mail.com','bronson','EfSIUxZUz6','9027277611');
INSERT INTO REGULARACCOUNT VALUES ('bryce@gmail.com','bryce','Yk9MAUKeM2','4383510905');
INSERT INTO REGULARACCOUNT VALUES ('burdette@hotmail.com','burdette','U1tbLuPueV','6722620776');
INSERT INTO REGULARACCOUNT VALUES ('burle@yahoo.com','burle','zNxsMlpktg','3433791120');
INSERT INTO REGULARACCOUNT VALUES ('byrd@mail.com','byrd','JY07doKVe8','4744203208');
INSERT INTO REGULARACCOUNT VALUES ('byron@gmail.com','byron','AmKKC6Z3Lb','7428268418');
INSERT INTO REGULARACCOUNT VALUES ('cabal@hotmail.com','cabal','zmv24KmH7Z','7538476049');
INSERT INTO REGULARACCOUNT VALUES ('cage@yahoo.com','cage','KWX91u3dQS','8675608758');
INSERT INTO REGULARACCOUNT VALUES ('cahir@mail.com','cahir','LdcKx37zuM','7789755306');
INSERT INTO REGULARACCOUNT VALUES ('cavalon@gmail.com','cavalon','OkXkhMY3wk','4313004583');
INSERT INTO REGULARACCOUNT VALUES ('cedar@hotmail.com','cedar','bU6IGZwfzC','9028645962');
INSERT INTO REGULARACCOUNT VALUES ('chatillon@yahoo.com','chatillon','K4wdj73CTE','2893767197');
INSERT INTO REGULARACCOUNT VALUES ('churchill@mail.com','churchill','NCUCMlw0B0','4503924402');
INSERT INTO REGULARACCOUNT VALUES ('clachas@gmail.com','clachas','0v9JGDymgB','3063954290');
INSERT INTO REGULARACCOUNT VALUES ('addison@hotmail.com','addison','Pjc73lOOmK','7056831278');
INSERT INTO REGULARACCOUNT VALUES ('alivia@yahoo.com','alivia','B6Y5xJy9dM','3658293232');
INSERT INTO REGULARACCOUNT VALUES ('allaya@mail.com','allaya','dkt98odLlg','7091084029');
INSERT INTO REGULARACCOUNT VALUES ('amarie@gmail.com','amarie','OKx1lt82cz','6471732710');
INSERT INTO REGULARACCOUNT VALUES ('amaris@hotmail.com','amaris','MWBewWmFkk','4166359032');
INSERT INTO REGULARACCOUNT VALUES ('annabeth@yahoo.com','annabeth','zZW9D8Xv4c','8672340919');
INSERT INTO REGULARACCOUNT VALUES ('annalynn@mail.com','annalynn','l14lLTAjGP','7828517131');
INSERT INTO REGULARACCOUNT VALUES ('araminta@gmail.com','araminta','4Szgz90hpg','6833654693');
INSERT INTO REGULARACCOUNT VALUES ('ardys@hotmail.com','ardys','BUOtUsIP25','6139317477');
INSERT INTO REGULARACCOUNT VALUES ('ashland@yahoo.com','ashland','gw16HNEMpI','5145050158');
INSERT INTO REGULARACCOUNT VALUES ('avery@mail.com','avery','saQk9RySog','2639813974');
INSERT INTO REGULARACCOUNT VALUES ('bedegrayne@gmail.com','bedegrayne','GOWEgK5zLn','9029725992');
INSERT INTO REGULARACCOUNT VALUES ('bernadette@hotmail.com','bernadette','swe4mN3JHb','7059386594');
INSERT INTO REGULARACCOUNT VALUES ('billie@yahoo.com','billie','ezZcbP1Bv1','8253790202');
INSERT INTO REGULARACCOUNT VALUES ('birdee@mail.com','birdee','6lUdwuOEYk','2268741699');
INSERT INTO REGULARACCOUNT VALUES ('bliss@gmail.com','bliss','bnis90mp5p','6833277684');
INSERT INTO REGULARACCOUNT VALUES ('brice@hotmail.com','brice','yNILMA3JWb','7536859115');
INSERT INTO REGULARACCOUNT VALUES ('brittany@yahoo.com','brittany','iahb1dRXXl','6477506544');
INSERT INTO REGULARACCOUNT VALUES ('bryony@mail.com','bryony','hSyeaQBVQl','2263781602');
INSERT INTO REGULARACCOUNT VALUES ('cameo@gmail.com','cameo','NA5dJkdSyL','9058404570');
INSERT INTO REGULARACCOUNT VALUES ('carol@hotmail.com','carol','DDvlczODF8','6047965458');
INSERT INTO REGULARACCOUNT VALUES ('chalee@yahoo.com','chalee','TTxQeiqqlf','5871047486');
INSERT INTO REGULARACCOUNT VALUES ('christy@mail.com','christy','Dajrf6HtjX','3439982831');
INSERT INTO REGULARACCOUNT VALUES ('corky@gmail.com','corky','gIhsTNZvZa','8251848473');
INSERT INTO REGULARACCOUNT VALUES ('cotovatre@hotmail.com','cotovatre','KqO7WHB4Gj','7422555976');
INSERT INTO REGULARACCOUNT VALUES ('courage@yahoo.com','courage','TdOdMdPnIa','7782824629');
INSERT INTO REGULARACCOUNT VALUES ('daelen@mail.com','daelen','njt3S1Aj2b','4388618170');
INSERT INTO REGULARACCOUNT VALUES ('dana@gmail.com','dana','xHQThEtD2D','4749137878');
INSERT INTO REGULARACCOUNT VALUES ('darnell@hotmail.com','darnell','l5OjbzMjCQ','2508694707');
INSERT INTO REGULARACCOUNT VALUES ('dawn@yahoo.com','dawn','5S00XlvGQE','8079407267');
INSERT INTO REGULARACCOUNT VALUES ('delsie@mail.com','delsie','jcFLykESrn','3436758432');
INSERT INTO REGULARACCOUNT VALUES ('denita@gmail.com','denita','FHwP9KX82J','8195347338');
INSERT INTO REGULARACCOUNT VALUES ('devon@hotmail.com','devon','yj5P8uV8ui','6398688091');
INSERT INTO REGULARACCOUNT VALUES ('devona@yahoo.com','devona','dDdRvHHdyN','8076509216');
INSERT INTO REGULARACCOUNT VALUES ('diamond@mail.com','diamond','X6Yf6SUcic','4189718513');
INSERT INTO REGULARACCOUNT VALUES ('divinity@gmail.com','divinity','a7xBoQE1K6','5483577439');
INSERT INTO REGULARACCOUNT VALUES ('duff@hotmail.com','duff','QU7fge9xZD','2634981945');
INSERT INTO REGULARACCOUNT VALUES ('dustin@yahoo.com','dustin','BtlViWOzIS','3651551963');
INSERT INTO REGULARACCOUNT VALUES ('dusty@mail.com','dusty','ImR8VpUj1y','5144281259');
INSERT INTO REGULARACCOUNT VALUES ('ellen@gmail.com','ellen','n3GMFR10Qy','4189035329');
INSERT INTO REGULARACCOUNT VALUES ('eppie@hotmail.com','eppie','Y9meIUVj2M','4032952463');
INSERT INTO REGULARACCOUNT VALUES ('evelyn@yahoo.com','evelyn','LIieQzDf28','8678304073');
INSERT INTO REGULARACCOUNT VALUES ('everilda@mail.com','everilda','n8aDlQkxyO','8675853838');
INSERT INTO REGULARACCOUNT VALUES ('falynn@gmail.com','falynn','npvtLqjdFA','2361775709');
INSERT INTO REGULARACCOUNT VALUES ('fanny@hotmail.com','fanny','wvauRsG5XS','4382771729');
INSERT INTO REGULARACCOUNT VALUES ('faren@yahoo.com','faren','4LdATsDk30','8675789655');
INSERT INTO REGULARACCOUNT VALUES ('freedom@mail.com','freedom','h51ABfhDk4','2049008201');
INSERT INTO REGULARACCOUNT VALUES ('gala@gmail.com','gala','Ye0yIf9aH0','7787056314');
INSERT INTO REGULARACCOUNT VALUES ('galen@hotmail.com','galen','UJqKE53fA8','4387722126');
INSERT INTO REGULARACCOUNT VALUES ('gardenia@yahoo.com','gardenia','hWA2E0oz5b','6394220777');


-- ACCOUNT FOLLOWINGS
INSERT INTO ACCOUNTFOLLOWING VALUES ('galen@hotmail.com', 'archibald@yahoo.com');
INSERT INTO ACCOUNTFOLLOWING VALUES ('devon@hotmail.com', 'cameo@gmail.com');
INSERT INTO ACCOUNTFOLLOWING VALUES ('ayers@yahoo.com', 'amherst@mail.com');
INSERT INTO ACCOUNTFOLLOWING VALUES ('duff@hotmail.com', 'byron@gmail.com');
INSERT INTO ACCOUNTFOLLOWING VALUES ('brant@hotmail.com', 'cabal@hotmail.com');
INSERT INTO ACCOUNTFOLLOWING VALUES ('dustin@yahoo.com', 'evelyn@yahoo.com');
INSERT INTO ACCOUNTFOLLOWING VALUES ('blackburn@gmail.com', 'astin@hotmail.com');
INSERT INTO ACCOUNTFOLLOWING VALUES ('cedar@hotmail.com', 'brittany@yahoo.com');
INSERT INTO ACCOUNTFOLLOWING VALUES ('bardalph@yahoo.com', 'blaine@yahoo.com');
INSERT INTO ACCOUNTFOLLOWING VALUES ('bliss@gmail.com', 'dawn@yahoo.com');
INSERT INTO ACCOUNTFOLLOWING VALUES ('blaan@mail.com', 'brighton@hotmail.com');
INSERT INTO ACCOUNTFOLLOWING VALUES ('aries@mail.com', 'amarie@gmail.com');
INSERT INTO ACCOUNTFOLLOWING VALUES ('amaris@hotmail.com', 'chalee@yahoo.com');
INSERT INTO ACCOUNTFOLLOWING VALUES ('clachas@gmail.com', 'ardys@hotmail.com');
INSERT INTO ACCOUNTFOLLOWING VALUES ('ballentine@hotmail.com', 'barker@mail.com');
INSERT INTO ACCOUNTFOLLOWING VALUES ('falynn@gmail.com', 'faren@yahoo.com');
INSERT INTO ACCOUNTFOLLOWING VALUES ('breri@mail.com', 'avery@mail.com');
INSERT INTO ACCOUNTFOLLOWING VALUES ('bayard@hotmail.com', 'bronson@mail.com');
INSERT INTO ACCOUNTFOLLOWING VALUES ('devona@yahoo.com', 'birdee@mail.com');
INSERT INTO ACCOUNTFOLLOWING VALUES ('billie@yahoo.com', 'adney@mail.com');
INSERT INTO ACCOUNTFOLLOWING VALUES ('cotovatre@hotmail.com', 'brice@hotmail.com');
INSERT INTO ACCOUNTFOLLOWING VALUES ('broderick@yahoo.com', 'arwen@gmail.com');
INSERT INTO ACCOUNTFOLLOWING VALUES ('briar@gmail.com', 'brawley@yahoo.com');
INSERT INTO ACCOUNTFOLLOWING VALUES ('baker@mail.com', 'dana@gmail.com');
INSERT INTO ACCOUNTFOLLOWING VALUES ('bishop@yahoo.com', 'blaze@mail.com');
INSERT INTO ACCOUNTFOLLOWING VALUES ('burle@yahoo.com', 'fanny@hotmail.com');
INSERT INTO ACCOUNTFOLLOWING VALUES ('everilda@mail.com', 'cage@yahoo.com');
INSERT INTO ACCOUNTFOLLOWING VALUES ('corky@gmail.com', 'addison@hotmail.com');
INSERT INTO ACCOUNTFOLLOWING VALUES ('balder@gmail.com', 'darnell@hotmail.com');
INSERT INTO ACCOUNTFOLLOWING VALUES ('annabeth@yahoo.com', 'bryce@gmail.com');
INSERT INTO ACCOUNTFOLLOWING VALUES ('aldo@gmail.com', 'aleyn@hotmail.com');
INSERT INTO ACCOUNTFOLLOWING VALUES ('gala@gmail.com', 'alivia@yahoo.com');
INSERT INTO ACCOUNTFOLLOWING VALUES ('bramwell@gmail.com', 'gardenia@yahoo.com');
INSERT INTO ACCOUNTFOLLOWING VALUES ('cahir@mail.com', 'denita@gmail.com');
INSERT INTO ACCOUNTFOLLOWING VALUES ('atley@yahoo.com', 'araminta@gmail.com');
INSERT INTO ACCOUNTFOLLOWING VALUES ('blade@hotmail.com', 'courage@yahoo.com');
INSERT INTO ACCOUNTFOLLOWING VALUES ('dusty@mail.com', 'chatillon@yahoo.com');
INSERT INTO ACCOUNTFOLLOWING VALUES ('christy@mail.com', 'burdette@hotmail.com');
INSERT INTO ACCOUNTFOLLOWING VALUES ('diamond@mail.com', 'annalynn@mail.com');
INSERT INTO ACCOUNTFOLLOWING VALUES ('ellen@gmail.com', 'divinity@gmail.com');
INSERT INTO ACCOUNTFOLLOWING VALUES ('freedom@mail.com', 'barric@gmail.com');
INSERT INTO ACCOUNTFOLLOWING VALUES ('allaya@mail.com', 'carol@hotmail.com');
INSERT INTO ACCOUNTFOLLOWING VALUES ('cavalon@gmail.com', 'audie@gmail.com');
INSERT INTO ACCOUNTFOLLOWING VALUES ('angel@gmail.com', 'bedegrayne@gmail.com');
INSERT INTO ACCOUNTFOLLOWING VALUES ('atwell@mail.com', 'churchill@mail.com');
INSERT INTO ACCOUNTFOLLOWING VALUES ('eppie@hotmail.com', 'alford@yahoo.com');
INSERT INTO ACCOUNTFOLLOWING VALUES ('daelen@mail.com', 'bryony@mail.com');
INSERT INTO ACCOUNTFOLLOWING VALUES ('byrd@mail.com', 'delsie@mail.com');
INSERT INTO ACCOUNTFOLLOWING VALUES ('anson@hotmail.com', 'ashland@yahoo.com');

-- RESTAURANTS
INSERT INTO RESTAURANT VALUES ('DALDONGNAE1', 'Daldongnae', '5148781111', 'daldongnae@mail.com');
INSERT INTO RESTAURANT VALUES ('COPPERBRANCH1', 'Copper Branch', '4383856262', 'copperbranch@mail.com');
INSERT INTO RESTAURANT VALUES ('AW1', 'A&W', '5148496886', 'aw@mail.com');
INSERT INTO RESTAURANT VALUES ('AW2', 'A&W', '5149375001', 'aw@mail.com');
INSERT INTO RESTAURANT VALUES ('MITSUKI1', 'Mitsuki', '4506788828', 'mitsuki@mail.com');
INSERT INTO RESTAURANT VALUES ('TIMS1', 'Tim Hortons', '5146879000', 'timhortons@mail.ca');
INSERT INTO RESTAURANT VALUES ('SUSHIYO1', 'Sushiyo', '5149397474', 'sushiyo@mail.com');

-- RESTAURANT ADDRESSES
INSERT INTO ADDRESS VALUES ('Bishop Street', '1216', 'Montreal', 'Quebec', 'H3G2E3', 'DALDONGNAE1');
INSERT INTO ADDRESS VALUES ('Boulevard de Maisonneuve Ouest', '600', 'Montreal', 'Quebec', 'H3A3J2', 'COPPERBRANCH1');
INSERT INTO ADDRESS VALUES ('Sainte-Catherine Street West', '453', 'Montreal', 'Quebec', 'H3B1B1', 'AW1');
INSERT INTO ADDRESS VALUES ('Boulevard de Maisonneuve Ouest', '1540', 'Montreal', 'Quebec', 'H3G1N1', 'AW2');
INSERT INTO ADDRESS VALUES ('Boulevard Leduc', '8840', 'Brossard', 'Quebec', 'J4Y0G4', 'MITSUKI1');
INSERT INTO ADDRESS VALUES ('Sherbrooke Street West', '3040', 'Montreal', 'Quebec', 'H3Z1A4', 'TIMS1');
INSERT INTO ADDRESS VALUES ('Sherbrooke Street West', '666', 'Montreal', 'Quebec', 'H3A1E7', 'SUSHIYO1');

-- BUSINESS HOURS
INSERT INTO BUSINESSHOUR VALUES (1, '09:00:00', '18:00:00', 'COPPERBRANCH1');
INSERT INTO BUSINESSHOUR VALUES (2, '09:00:00', '18:00:00', 'COPPERBRANCH1');
INSERT INTO BUSINESSHOUR VALUES (3, '09:00:00', '20:00:00', 'COPPERBRANCH1');
INSERT INTO BUSINESSHOUR VALUES (4, '09:00:00', '20:00:00', 'COPPERBRANCH1');
INSERT INTO BUSINESSHOUR VALUES (5, '09:00:00', '20:00:00', 'COPPERBRANCH1');
INSERT INTO BUSINESSHOUR VALUES (6, '09:00:00', '18:00:00', 'COPPERBRANCH1');
INSERT INTO BUSINESSHOUR VALUES (7, '09:00:00', '18:00:00', 'COPPERBRANCH1');
INSERT INTO BUSINESSHOUR VALUES (1, '07:00:00', '23:00:00', 'TIMS1');
INSERT INTO BUSINESSHOUR VALUES (2, '07:00:00', '23:00:00', 'TIMS1');
INSERT INTO BUSINESSHOUR VALUES (3, '07:00:00', '23:00:00', 'TIMS1');
INSERT INTO BUSINESSHOUR VALUES (4, '07:00:00', '23:00:00', 'TIMS1');
INSERT INTO BUSINESSHOUR VALUES (5, '07:00:00', '23:00:00', 'TIMS1');
INSERT INTO BUSINESSHOUR VALUES (6, '07:00:00', '23:00:00', 'TIMS1');
INSERT INTO BUSINESSHOUR VALUES (7, '07:00:00', '23:00:00', 'TIMS1');
INSERT INTO BUSINESSHOUR VALUES (1, '08:00:00', '00:00:00', 'AW1');
INSERT INTO BUSINESSHOUR VALUES (2, '08:00:00', '00:00:00', 'AW1');
INSERT INTO BUSINESSHOUR VALUES (3, '08:00:00', '04:00:00', 'AW1');
INSERT INTO BUSINESSHOUR VALUES (4, '08:00:00', '04:00:00', 'AW1');
INSERT INTO BUSINESSHOUR VALUES (5, '08:00:00', '04:00:00', 'AW1');
INSERT INTO BUSINESSHOUR VALUES (6, '08:00:00', '04:00:00', 'AW1');
INSERT INTO BUSINESSHOUR VALUES (7, '08:00:00', '23:00:00', 'AW1');
INSERT INTO BUSINESSHOUR VALUES (1, '09:00:00', '17:00:00', 'SUSHIYO1');
INSERT INTO BUSINESSHOUR VALUES (2, '09:00:00', '17:00:00', 'SUSHIYO1');
INSERT INTO BUSINESSHOUR VALUES (3, '09:00:00', '17:00:00', 'SUSHIYO1');
INSERT INTO BUSINESSHOUR VALUES (4, '09:00:00', '17:00:00', 'SUSHIYO1');
INSERT INTO BUSINESSHOUR VALUES (5, '09:00:00', '17:00:00', 'SUSHIYO1');
INSERT INTO BUSINESSHOUR (day, restaurantID) VALUES (6, 'SUSHIYO1');
INSERT INTO BUSINESSHOUR (day, restaurantID) VALUES (7, 'SUSHIYO1');


-- MENUS
INSERT INTO MENU VALUES ('Lunch Menu', '1', 'DALDONGNAE1');
INSERT INTO MENU VALUES ('Lunch Menu', '1', 'COPPERBRANCH1');
INSERT INTO MENU VALUES ('Breakfast Menu', '1', 'AW1');
INSERT INTO MENU VALUES ('Regular Menu', '1', 'AW1');
INSERT INTO MENU VALUES ('Breakfast Menu', '0', 'AW2');
INSERT INTO MENU VALUES ('Regular Menu', '0', 'AW2');
INSERT INTO MENU VALUES ('Lunch Menu', '1', 'MITSUKI1');
INSERT INTO MENU VALUES ('Evening Menu', '1', 'MITSUKI1');
INSERT INTO MENU VALUES ('Special Menu', '0', 'MITSUKI1');
INSERT INTO MENU VALUES ('Regular Menu', '1', 'SUSHIYO1');
INSERT INTO MENU VALUES ('Breakfast Menu', '1', 'TIMS1');
INSERT INTO MENU VALUES ('Regular Menu', '1', 'TIMS1');
INSERT INTO MENU VALUES ('Holidays Special Menu', '1', 'TIMS1');

-- MENU ITEMS
INSERT INTO MENUITEM VALUES ('AW1-001','Bacon & Egger', '5.60', 'A freshly cracked egg, topped with a delicious layer of cheddar cheese and crispy smoked bacon, served on your choice of a toasted English Muffin, Sesame seed bun, or our new limited time buttery and flaky croissant.');
INSERT INTO MENUITEM VALUES ('AW1-002','Sausage & Egger', '5.60', 'A freshly cracked egg, topped with a delicious layer of cheddar cheese and a juicy sausage patty, served on your choice of a toasted English Muffin, Sesame seed bun, or our new limited time buttery and flaky croissant.');
INSERT INTO MENUITEM VALUES ('AW1-003','Teen Burger', '9.23', 'A perfectly seasoned grass-fed beef patty topped with real cheddar cheese, mouth-watering bacon from pork raised without the use of antibiotics, crisp lettuce, onion, tomato, pickles and Teen® sauce, served on a freshly toasted sesame seed bun.');
INSERT INTO MENUITEM VALUES ('AW1-004','Double Teen Burger', '10.99', 'Two perfectly seasoned grass-fed beef patties topped with real cheddar cheese, mouth-watering bacon from pork raised without the use of antibiotics, crisp lettuce, tomato, pickles and Teen® sauce, served on a freshly toasted bun.');
INSERT INTO MENUITEM VALUES ('SUSHIYO1-001', 'Salmon Poke', '15.99', 'Poke with fresh salmon on top');
INSERT INTO MENUITEM VALUES ('SUSHIYO1-002', 'Tuna Poke', '17.99', 'Poke with fresh tuna on top');
INSERT INTO MENUITEM VALUES ('SUSHIYO1-003', 'Shrimp and Crab Poke', '15.99', 'Poke with fresh shrimp and imitation crab on top');
INSERT INTO MENUITEM VALUES ('SUSHIYO1-004', 'Vegetarian Poke', '15.99', 'Poke topped with edamame and corn');
INSERT INTO MENUITEM VALUES ('SUSHIYO1-005', '12 pieces sushi combo', '11.49', '9 maki and 3 hosomaki');
INSERT INTO MENUITEM VALUES ('COPPERBRANCH1-001', 'Asian Fusion Power Bowl', '17.29', 'With General Tao Sauce, Edamame, diced tomatoes, mung beans, kimchi, black sesame, chickpeas, broccoli, beets, carrot, lettuce');
INSERT INTO MENUITEM VALUES ('COPPERBRANCH1-002', 'Aztec Power Bowl', '17.29', 'With Basil Olive Oil Vinaigrette, Sweet potato cubes, corn and mango salsa, black beans, guacamole, sour cream, broccoli, beets, carrot, lettuce, mung beans, pumpkin seeds, corn chips');
INSERT INTO MENUITEM VALUES ('TIMS1-001', 'Sweet Chili Chicken Loaded Bowl', '9.99', 'Bowl with sweet chili, chicken');
INSERT INTO MENUITEM VALUES ('TIMS1-002', 'Sweet Chili Chicken Loaded Wrap', '8.79', 'Wrap with sweet chili, chicken');
INSERT INTO MENUITEM VALUES ('TIMS1-003', 'Sea Salt Wedges', '2.79', 'Potato wedges covered with sea salt.');

-- CONTAINS MENU ITEM
INSERT INTO CONTAINSMENUITEM VALUES ('Breakfast Menu', 'AW1', 'AW1-001');
INSERT INTO CONTAINSMENUITEM VALUES ('Breakfast Menu', 'AW1', 'AW1-002');
INSERT INTO CONTAINSMENUITEM VALUES ('Regular Menu', 'AW1', 'AW1-003');
INSERT INTO CONTAINSMENUITEM VALUES ('Regular Menu', 'AW1', 'AW1-004');
INSERT INTO CONTAINSMENUITEM VALUES ('Regular Menu', 'SUSHIYO1', 'SUSHIYO1-001');
INSERT INTO CONTAINSMENUITEM VALUES ('Regular Menu', 'SUSHIYO1', 'SUSHIYO1-002');
INSERT INTO CONTAINSMENUITEM VALUES ('Regular Menu', 'SUSHIYO1', 'SUSHIYO1-003');
INSERT INTO CONTAINSMENUITEM VALUES ('Regular Menu', 'SUSHIYO1', 'SUSHIYO1-004');
INSERT INTO CONTAINSMENUITEM VALUES ('Regular Menu', 'SUSHIYO1', 'SUSHIYO1-005');
INSERT INTO CONTAINSMENUITEM VALUES ('Lunch Menu', 'COPPERBRANCH1', 'COPPERBRANCH1-001');
INSERT INTO CONTAINSMENUITEM VALUES ('Lunch Menu', 'COPPERBRANCH1', 'COPPERBRANCH1-002');
INSERT INTO CONTAINSMENUITEM VALUES ('Regular Menu', 'TIMS1', 'TIMS1-001');
INSERT INTO CONTAINSMENUITEM VALUES ('Regular Menu', 'TIMS1', 'TIMS1-002');
INSERT INTO CONTAINSMENUITEM VALUES ('Regular Menu', 'TIMS1', 'TIMS1-003');


-- RESERVATIONS
INSERT INTO RESERVATION VALUES ('2024-01-14 12:00:00', 4, '1', 'briar@gmail.com', 'DALDONGNAE1');
INSERT INTO RESERVATION VALUES ('2024-01-14 12:01:00', 4, '0', 'divinity@gmail.com', 'DALDONGNAE1');
INSERT INTO RESERVATION VALUES ('2024-01-14 12:30:00', 2, '1', 'cahir@mail.com', 'DALDONGNAE1');
INSERT INTO RESERVATION VALUES ('2024-01-22 12:15:03', 5, '1', 'aleyn@hotmail.com', 'MITSUKI1');
INSERT INTO RESERVATION VALUES ('2024-02-13 11:30:00', 4, '1', 'briar@gmail.com', 'MITSUKI1');
INSERT INTO RESERVATION VALUES ('2024-02-14 11:45:00', 2, '0', 'amarie@gmail.com', 'COPPERBRANCH1');

-- REVIEWS
INSERT INTO REVIEW VALUES ('COPPERBRANCH1-0000001', '5', 'I love this restaurant so much!', '2023-09-25 18:05:03','fanny@hotmail.com', 'COPPERBRANCH1');
INSERT INTO REVIEW VALUES ('COPPERBRANCH1-0000002', '3', 'It do be  alright, but there are better places for sure', '2024-01-25 13:08:03','audie@gmail.com', 'COPPERBRANCH1');
INSERT INTO REVIEW VALUES ('COPPERBRANCH1-0000003', '2', 'Was better in the past!', '2023-02-12 16:10:03','bayard@hotmail.com', 'COPPERBRANCH1');
INSERT INTO REVIEW VALUES ('COPPERBRANCH1-0000004', '1', 'Terrible.', '2024-10-25 17:05:03','blade@hotmail.com', 'COPPERBRANCH1');
INSERT INTO REVIEW VALUES ('COPPERBRANCH1-0000005', '4', 'Pleasantly surprised', '2023-12-25 17:05:03','evelyn@yahoo.com', 'COPPERBRANCH1');
INSERT INTO REVIEW VALUES ('TIMS-0000003', '2', 'The waitress was not nice to me.', '2015-06-14 09:52:34','cahir@mail.com', 'TIMS1');
INSERT INTO REVIEW VALUES ('TIMS-0000001', '1', 'The service is so bad, I will never come back.', '2009-03-14 09:52:34','churchill@mail.com', 'TIMS1');
INSERT INTO REVIEW VALUES ('TIMS-0000002', '3', 'What is wrong with the service here???!!! I tried to speak to the manager, but apparently there was no one in charge today. WE ARE A MONDAY. WHERE IS THE MANAGER. TERRIBLE PLACE. DO NOT GO.', '2010-03-14 09:52:34','bishop@yahoo.com', 'TIMS1');
INSERT INTO REVIEW VALUES ('TIMS-0000004', '4', 'Exemplary canadian service', '2019-03-14 09:52:34','delsie@mail.com', 'TIMS1');
INSERT INTO REVIEW VALUES ('SUSHIYO1-0000001', '5', 'I came here with my friends and everyone enjoyed the food. The staff was so nice!', '2024-01-10 12:12:10','evelyn@yahoo.com', 'SUSHIYO1');
INSERT INTO REVIEW VALUES ('SUSHIYO1-0000002', '5', '100% would come back! The food was sooooo good', '2022-11-05 14:40:37','divinity@gmail.com', 'SUSHIYO1');
INSERT INTO REVIEW VALUES('SUSHIYO1-0000003', '4', 'What I would expect from a sushi chain. Would definitely come back.', '2024-02-22 16:04:45', 'bliss@gmail.com', 'SUSHIYO1');
INSERT INTO REVIEW VALUES ('AW1-0000001', '4', 'Pleasantly surprised', '2012-04-25 17:05:03','divinity@gmail.com', 'AW1');
INSERT INTO REVIEW VALUES ('AW1-0000002', '4', 'Pleasantly surprised', '2022-06-15 17:05:03','delsie@mail.com', 'AW1');
INSERT INTO REVIEW VALUES ('AW1-0000003', '4', 'Pleasantly surprised', '2015-02-05 17:05:03','churchill@mail.com', 'AW1');
INSERT INTO REVIEW VALUES ('MITSUKI1-0000001', '5', 'Amazing experience', '2020-09-12 19:34:23', 'blaze@mail.com', 'MITSUKI1');
INSERT INTO REVIEW VALUES ('MITSUKI1-0000002', '5', 'Best Japanese food in Brossard', '2020-09-12 19:34:23', 'blaze@mail.com', 'MITSUKI1');

--RECOMMENDATIONS
INSERT INTO RECOMMENDATION VALUES ('evelyn@yahoo.com', 'SUSHIYO1');
INSERT INTO RECOMMENDATION VALUES ('fanny@hotmail.com', 'COPPERBRANCH1');
INSERT INTO RECOMMENDATION VALUES ('divinity@gmail.com', 'SUSHIYO1');
INSERT INTO RECOMMENDATION VALUES ('bronson@mail.com', 'TIMS1');
INSERT INTO RECOMMENDATION VALUES ('evelyn@yahoo.com', 'AW1');
INSERT INTO RECOMMENDATION VALUES ('cabal@hotmail.com', 'AW2');
