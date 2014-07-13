-- ===================================================
-- script name: cmsc495_calendar_dml.sql 
-- script purpose: DML for cmsc495_calendar software 
-- script author: matthew wingate jacobs 
-- date: july 7, 2014 
-- ===================================================

-- ===================================================
-- EMPTY ALL TABLE DATA 
-- ===================================================
DELETE FROM TYPE_T;
/
DELETE FROM ATTACH_T;
/
DELETE FROM USER_T;
/
DELETE FROM EVENT_T;
/

-- ===================================================
-- INSERT DATA INTO TYPE TABLE
-- ===================================================
INSERT INTO TYPE_T (TYPEID, TYPETITLE, TYPECOLOR) VALUES(1, '4th of July', '#FFFFFF')
/
INSERT INTO TYPE_T (TYPEID, TYPETITLE, TYPECOLOR) VALUES(2, 'Anniversary', '#FFFFFF')
/
INSERT INTO TYPE_T (TYPEID, TYPETITLE, TYPECOLOR) VALUES(3, 'Baby Shower', '#FFFFFF')
/
INSERT INTO TYPE_T (TYPEID, TYPETITLE, TYPECOLOR) VALUES(4, 'Babyq''s First Year', '#FFFFFF')
/
INSERT INTO TYPE_T (TYPEID, TYPETITLE, TYPECOLOR) VALUES(5, 'Bachelor Party', '#FFFFFF')
/
INSERT INTO TYPE_T (TYPEID, TYPETITLE, TYPECOLOR) VALUES(6, 'Bachelorette Party', '#FFFFFF')
/
INSERT INTO TYPE_T (TYPEID, TYPETITLE, TYPECOLOR) VALUES(7, 'BBQ', '#FFFFFF')
/
INSERT INTO TYPE_T (TYPEID, TYPETITLE, TYPECOLOR) VALUES(8, 'Birthday for Her', '#FFFFFF')
/
INSERT INTO TYPE_T (TYPEID, TYPETITLE, TYPECOLOR) VALUES(9, 'Birthday for Him', '#FFFFFF')
/
INSERT INTO TYPE_T (TYPEID, TYPETITLE, TYPECOLOR) VALUES(10, 'Birthday for Kids', '#FFFFFF')
/
INSERT INTO TYPE_T (TYPEID, TYPETITLE, TYPECOLOR) VALUES(11, 'Birthday Milestones', '#FFFFFF')
/
INSERT INTO TYPE_T (TYPEID, TYPETITLE, TYPECOLOR) VALUES(12, 'Bridal Shower', '#FFFFFF')
/
INSERT INTO TYPE_T (TYPEID, TYPETITLE, TYPECOLOR) VALUES(13, 'Brunch/Lunch', '#FFFFFF')
/
INSERT INTO TYPE_T (TYPEID, TYPETITLE, TYPECOLOR) VALUES(14, 'Charity/Fundraisers', '#FFFFFF')
/
INSERT INTO TYPE_T (TYPEID, TYPETITLE, TYPECOLOR) VALUES(15, 'Clubs/Groups', '#FFFFFF')
/
INSERT INTO TYPE_T (TYPEID, TYPETITLE, TYPECOLOR) VALUES(16, 'Cocktail Party', '#FFFFFF')
/
INSERT INTO TYPE_T (TYPEID, TYPETITLE, TYPECOLOR) VALUES(17, 'Dinner Party', '#FFFFFF')
/
INSERT INTO TYPE_T (TYPEID, TYPETITLE, TYPECOLOR) VALUES(18, 'Family Gathering', '#FFFFFF')
/
INSERT INTO TYPE_T (TYPEID, TYPETITLE, TYPECOLOR) VALUES(19, 'Game Night', '#FFFFFF')
/
INSERT INTO TYPE_T (TYPEID, TYPETITLE, TYPECOLOR) VALUES(20, 'Girl''s Night', '#FFFFFF')
/
INSERT INTO TYPE_T (TYPEID, TYPETITLE, TYPECOLOR) VALUES(21, 'Graduation', '#FFFFFF')
/
INSERT INTO TYPE_T (TYPEID, TYPETITLE, TYPECOLOR) VALUES(22, 'Guy''s Night', '#FFFFFF')
/
INSERT INTO TYPE_T (TYPEID, TYPETITLE, TYPECOLOR) VALUES(23, 'Happy Hour', '#FFFFFF')
/
INSERT INTO TYPE_T (TYPEID, TYPETITLE, TYPECOLOR) VALUES(24, 'Hostess Party', '#FFFFFF')
/
INSERT INTO TYPE_T (TYPEID, TYPETITLE, TYPECOLOR) VALUES(25, 'House Party', '#FFFFFF')
/
INSERT INTO TYPE_T (TYPEID, TYPETITLE, TYPECOLOR) VALUES(26, 'Housewarming', '#FFFFFF')
/
INSERT INTO TYPE_T (TYPEID, TYPETITLE, TYPECOLOR) VALUES(27, 'Kid''s Corner', '#FFFFFF')
/
INSERT INTO TYPE_T (TYPEID, TYPETITLE, TYPECOLOR) VALUES(28, 'Movie/TV Night', '#FFFFFF')
/
INSERT INTO TYPE_T (TYPEID, TYPETITLE, TYPECOLOR) VALUES(29, 'Night on the Town', '#FFFFFF')
/
INSERT INTO TYPE_T (TYPEID, TYPETITLE, TYPECOLOR) VALUES(30, 'Outdoor Fun', '#FFFFFF')
/
INSERT INTO TYPE_T (TYPEID, TYPETITLE, TYPECOLOR) VALUES(31, 'Pool Party', '#FFFFFF')
/
INSERT INTO TYPE_T (TYPEID, TYPETITLE, TYPECOLOR) VALUES(32, 'Potluck', '#FFFFFF')
/
INSERT INTO TYPE_T (TYPEID, TYPETITLE, TYPECOLOR) VALUES(33, 'Professional Events', '#FFFFFF')
/
INSERT INTO TYPE_T (TYPEID, TYPETITLE, TYPECOLOR) VALUES(34, 'Religious', '#FFFFFF')
/
INSERT INTO TYPE_T (TYPEID, TYPETITLE, TYPECOLOR) VALUES(35, 'Reunions', '#FFFFFF')
/
INSERT INTO TYPE_T (TYPEID, TYPETITLE, TYPECOLOR) VALUES(36, 'Save the Date', '#FFFFFF')
/
INSERT INTO TYPE_T (TYPEID, TYPETITLE, TYPECOLOR) VALUES(37, 'Sports/Leagues', '#FFFFFF')
/
INSERT INTO TYPE_T (TYPEID, TYPETITLE, TYPECOLOR) VALUES(38, 'Super Bowl', '#FFFFFF')
/
INSERT INTO TYPE_T (TYPEID, TYPETITLE, TYPECOLOR) VALUES(39, 'Tailgating', '#FFFFFF')
/
INSERT INTO TYPE_T (TYPEID, TYPETITLE, TYPECOLOR) VALUES(40, 'Trips/Getaways', '#FFFFFF')
/
INSERT INTO TYPE_T (TYPEID, TYPETITLE, TYPECOLOR) VALUES(41, 'Watch The Game', '#FFFFFF')
/
INSERT INTO TYPE_T (TYPEID, TYPETITLE, TYPECOLOR) VALUES(42, 'Wedding Engagement', '#FFFFFF')
/
INSERT INTO TYPE_T (TYPEID, TYPETITLE, TYPECOLOR) VALUES(43, 'World Cup', '#FFFFFF')
/

-- ===================================================
-- INSERT DATA INTO ATTACH TABLE
-- ===================================================
-- [MJ: NEEDS LOCATION REFERENCE FOR ACTUAL FILE (CURRENTLY WORKING ON THIS ISSUE)
-- INSERT INTO ATTACH_T (ATTACHID, ATTACHNAME, ATTACHMIME, ATTACHSIZE, ATTACHDATE) VALUES(1, 'matts_pic', '.jpg', '35 KB', TO_DATE(SYSDATE));

-- ===================================================
-- INSERT DATA INTO USER TABLE
-- ===================================================
INSERT INTO USER_T (USERID, FIRSTNAME, LASTNAME, USERNAME, PASSWORD) VALUES(1, 'Matthew', 'Jacobs', 'mattJ@cmsc495', 'mattpw495');
/
INSERT INTO USER_T (USERID, FIRSTNAME, LASTNAME, USERNAME, PASSWORD) VALUES(2, 'Elijah', 'Robertson', 'elijahR@cmsc495', 'elijapw495');
/
INSERT INTO USER_T (USERID, FIRSTNAME, LASTNAME, USERNAME, PASSWORD) VALUES(3, 'Matthew', 'McArthur', 'matthewM@cmsc495', 'matthewpw495');
/

-- ===================================================
-- INSERT DATA INTO EVENT TABLE
-- ===================================================
INSERT INTO EVENT_T (EVENTID, EVENTTITLE, EVENTTYPEID, EVENTCREATORUSERID, EVENTSTARTDATE, EVENTENDDATE, EVENTSTARTTIME, EVENTENDTIME, EVENTALLDAY, EVENTMESSAGE, EVENTLOCATION, EVENTADDRESS, EVENTCITY, EVENTSTATE, EVENTZIP, EVENTATTACHID, CREATEDDATE, LASTUPDATEDATE) VALUES(1, 'My Birthday', 9, 1, To_date(SYSDATE), TO_DATE(SYSDATE), '9:30 AM', '10:30 PM', 0, 'Hi all, it''s my birthday', 'My House', '555 Maple Way', 'Boston', 'MA', '55689', '', To_Date(SYSDATE), '');
/
INSERT INTO EVENT_T (EVENTID, EVENTTITLE, EVENTTYPEID, EVENTCREATORUSERID, EVENTSTARTDATE, EVENTENDDATE, EVENTSTARTTIME, EVENTENDTIME, EVENTALLDAY, EVENTMESSAGE, EVENTLOCATION, EVENTADDRESS, EVENTCITY, EVENTSTATE, EVENTZIP, EVENTATTACHID, CREATEDDATE, LASTUPDATEDATE) VALUES(2, 'My Graduation', 21, 2, To_date(SYSDATE), TO_DATE(SYSDATE), '1:00 AM', '3:30 PM', 0, 'I am graduating today, Hurray!', 'UMUC Main Campus', '3501 Unicersity Blvd E', 'Adelphi', 'MD', '20783', '', To_Date(SYSDATE), '');
/
INSERT INTO EVENT_T (EVENTID, EVENTTITLE, EVENTTYPEID, EVENTCREATORUSERID, EVENTSTARTDATE, EVENTENDDATE, EVENTSTARTTIME, EVENTENDTIME, EVENTALLDAY, EVENTMESSAGE, EVENTLOCATION, EVENTADDRESS, EVENTCITY, EVENTSTATE, EVENTZIP, EVENTATTACHID, CREATEDDATE, LASTUPDATEDATE) VALUES(3, 'My Class Reunion', 35, 3, To_date(SYSDATE), TO_DATE(SYSDATE), '', '', 1, 'It''s an 80''s kinda-thing', 'Hilton Garden Inn', '23 Pennsylvania Ave NW', 'Washington', 'DC', '20002', '', To_Date(SYSDATE), '');
/
INSERT INTO EVENT_T (EVENTID, EVENTTITLE, EVENTTYPEID, EVENTCREATORUSERID, EVENTSTARTDATE, EVENTENDDATE, EVENTSTARTTIME, EVENTENDTIME, EVENTALLDAY, EVENTMESSAGE, EVENTLOCATION, EVENTADDRESS, EVENTCITY, EVENTSTATE, EVENTZIP, EVENTATTACHID, CREATEDDATE, LASTUPDATEDATE) VALUES(4, 'My Second Event', 26, 1, To_date(SYSDATE), TO_DATE(SYSDATE), '', '', 1, 'Hi all, it''s my second event', 'My House', '555 Maple Way', 'Boston', 'MA', '55689', '', To_Date(SYSDATE), '');
/
INSERT INTO EVENT_T (EVENTID, EVENTTITLE, EVENTTYPEID, EVENTCREATORUSERID, EVENTSTARTDATE, EVENTENDDATE, EVENTSTARTTIME, EVENTENDTIME, EVENTALLDAY, EVENTMESSAGE, EVENTLOCATION, EVENTADDRESS, EVENTCITY, EVENTSTATE, EVENTZIP, EVENTATTACHID, CREATEDDATE, LASTUPDATEDATE) VALUES(5, 'My Second Event', 28, 2, To_date(SYSDATE), TO_DATE(SYSDATE), '', '', 1, 'Hi all, it''s my second event', 'My House', '66 Unicorn St', 'Denver', 'CO', '64589', '', To_Date(SYSDATE), '');
/
INSERT INTO EVENT_T (EVENTID, EVENTTITLE, EVENTTYPEID, EVENTCREATORUSERID, EVENTSTARTDATE, EVENTENDDATE, EVENTSTARTTIME, EVENTENDTIME, EVENTALLDAY, EVENTMESSAGE, EVENTLOCATION, EVENTADDRESS, EVENTCITY, EVENTSTATE, EVENTZIP, EVENTATTACHID, CREATEDDATE, LASTUPDATEDATE) VALUES(6, 'My Second Event', 29, 3, To_date(SYSDATE), TO_DATE(SYSDATE), '6:30 PM', '8:30 PM', 0, 'Hi all, it''s my second event', 'My House', '216 Fulton St', 'San Diego', 'CA', '90601', '', To_Date(SYSDATE), '');
/

-- ===================================================
-- RUN QUERY ON ALL TABLES TO VERIFY DATA
-- ===================================================
SELECT * FROM TYPE_T;
/
SELECT * FROM ATTACH_T;
/
SELECT * FROM USER_T;
/
SELECT * FROM EVENT_T;
/


















