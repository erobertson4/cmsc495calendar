-- ===================================================
-- script name: cmsc495_calendar_ddl.sql 
-- script purpose: DDL for cmsc495_calendar software 
-- script author: matthew wingate jacobs 
-- date: july 7, 2014 
-- ===================================================

-- ===================================================
-- SET SESSION SETTINGS
-- ===================================================
SET LINESIZE 120
/
SET PAGESIZE 80
/

-- ===================================================
-- DROP SEQUENCE
-- ===================================================

DROP SEQUENCE USER_SEQ;
/
DROP SEQUENCE EVENT_SEQ;
/

-- ===================================================
-- DROP ALL TABLES
-- ===================================================

DROP TABLE USER_T CASCADE CONSTRAINTS;
/
DROP TABLE EVENT_T CASCADE CONSTRAINTS;
/

    
-- ===================================================
-- CREATE USER TABLE
-- ===================================================
CREATE TABLE USER_T
    (USERID                 NUMBER(6)     	NOT NULL
    ,FIRSTNAME              VARCHAR2(25)    NOT NULL
    ,LASTNAME               VARCHAR2(25)    NOT NULL
    ,USERNAME               VARCHAR2(25)    NOT NULL
    ,PASSWORD               VARCHAR2(25)    NOT NULL,
    CONSTRAINT USER_USERID_PK PRIMARY KEY (USERID));
/
    
-- ===================================================
-- CREATE EVENT TABLE
-- ===================================================
CREATE TABLE EVENT_T
    (ID                	NUMBER(6)     	NOT NULL
    ,TITLE             	VARCHAR2(50)    NOT NULL
    ,CREATORID     		NUMBER(6)     	NOT NULL
    ,SDATE	         	DATE     		NOT NULL
    ,SHOUR				NUMBER(2)		NULL
    ,SMIN				NUMBER(2)		NULL
    ,EHOUR				NUMBER(2)		NULL
    ,EMIN				NUMBER(2)		NULL
    ,SAM				NUMBER(1)		NULL
    ,EAM				NUMBER(1)		NULL
    ,ALLDAY            	Number(1)       DEFAULT '0'
    ,MESSAGE           	Varchar2(500)   NULL,
    CONSTRAINT EVENT_EVENTID_PK PRIMARY KEY (ID));
/

-- ===================================================
-- CREATE SEQUENCES
-- ===================================================

CREATE SEQUENCE USER_SEQ
    INCREMENT BY 1
    START WITH 15
    NOMAXVALUE
    MINVALUE 1
    NOCYCLE
    NOCACHE
/

CREATE SEQUENCE EVENT_SEQ
    INCREMENT BY 1
    START WITH 25
    NOMAXVALUE
    MINVALUE 1
    NOCYCLE
    NOCACHE
/

-- ===================================================
-- DESCRIBE ALL TABLES
-- ===================================================

DESCRIBE USER_T;
/
DESCRIBE EVENT_T;
/
