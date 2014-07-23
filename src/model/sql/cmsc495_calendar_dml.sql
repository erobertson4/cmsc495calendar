-- ===================================================
-- script name: cmsc495_calendar_dml.sql 
-- script purpose: DML for cmsc495_calendar software 
-- script author: matthew wingate jacobs 
-- date: july 7, 2014 
-- ===================================================

-- ===================================================
-- EMPTY ALL TABLE DATA 
-- ===================================================

DELETE FROM USER_T;
/
DELETE FROM EVENT_T;
/


-- ===================================================
-- INSERT DATA INTO USER TABLE
-- ===================================================
INSERT INTO USER_T (USERID, FIRSTNAME, LASTNAME, USERNAME, PASSWORD) VALUES(USER_SEQ.NEXTVAL, 'Matthew', 'Jacobs', 'm', 'j');
/
INSERT INTO USER_T (USERID, FIRSTNAME, LASTNAME, USERNAME, PASSWORD) VALUES(USER_SEQ.NEXTVAL, 'Elijah', 'Robertson', 'e', 'e');
/
INSERT INTO USER_T (USERID, FIRSTNAME, LASTNAME, USERNAME, PASSWORD) VALUES(USER_SEQ.NEXTVAL, 'Matthew', 'McArthur', 'm', 'm');
/

-- ===================================================
-- INSERT DATA INTO EVENT TABLE
-- ===================================================
INSERT INTO EVENT_T (ID, TITLE, CREATORID, SDATE, SHOUR, SMIN, EHOUR, EMIN, SAM, EAM, ALLDAY, MESSAGE) VALUES(EVENT_SEQ.NEXTVAL, 'My Birthday', 1, TO_DATE(SYSDATE, 'YYYY-MM-DD'), 2, 30, 3, 30, 0, 0, 0, 'Hi all, it''s my birthday');
/
INSERT INTO EVENT_T (ID, TITLE, CREATORID, SDATE, SHOUR, SMIN, EHOUR, EMIN, SAM, EAM, ALLDAY, MESSAGE) VALUES(EVENT_SEQ.NEXTVAL, 'My Graduation', 2, TO_DATE(SYSDATE, 'YYYY-MM-DD'), 6, 30, 7, 30, 0, 1, 0, 'I am graduating today, Hurray!');
/
INSERT INTO EVENT_T (ID, TITLE, CREATORID, SDATE, SHOUR, SMIN, EHOUR, EMIN, SAM, EAM, ALLDAY, MESSAGE) VALUES(EVENT_SEQ.NEXTVAL, 'My Class Reunion', 3, TO_DATE(SYSDATE, 'YYYY-MM-DD'), 5, 0, 6, 0, 1, 1, 0, 'It''s an 80''s kinda-thing');
/


-- ===================================================
-- RUN QUERY ON ALL TABLES TO VERIFY DATA
-- ===================================================

SELECT * FROM USER_T;
/
SELECT * FROM EVENT_T;
/


















