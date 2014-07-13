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
DROP SEQUENCE ATTACHID_SEQ;
/
DROP SEQUENCE USERID_SEQ;
/
DROP SEQUENCE EVENTID_SEQ;
/

-- ===================================================
-- DROP ALL TABLES
-- ===================================================
DROP TABLE TYPE_T CASCADE CONSTRAINTS;
/
DROP TABLE ATTACH_T CASCADE CONSTRAINTS;
/
DROP TABLE USER_T CASCADE CONSTRAINTS;
/
DROP TABLE EVENTS_T CASCADE CONSTRAINTS;
/

-- ===================================================
-- CREATE TYPE TABLE
-- ===================================================
CREATE TABLE TYPE_T
    (TYPEID                 NUMBER(6)     NOT NULL
    ,TYPETITLE              VARCHAR2(25)    NOT NULL
    ,TYPECOLOR              VARCHAR2(7)     DEFAULT '#FFFFFF',
    CONSTRAINT TYPE_TYPEID_PK PRIMARY KEY (TYPEID));
/
     
-- ===================================================
-- CREATE ATTACH TABLE
-- ===================================================
CREATE TABLE ATTACH_T
    (ATTACHID               NUMBER(6)     NOT NULL
    ,ATTACHNAME             VARCHAR2(25)    NOT NULL
    ,ATTACHMIME             VARCHAR2(5)     NOT NULL
    ,ATTACHSIZE             VARCHAR2(10)    NOT NULL
    ,ATTACHDATE             DATE            DEFAULT SYSDATE,
    CONSTRAINT ATTACH_ATTACHID_PK PRIMARY KEY (ATTACHID));
/
     
-- ===================================================
-- CREATE USER TABLE
-- ===================================================
CREATE TABLE USER_T
    (USERID                 NUMBER(6)     NOT NULL
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
    (EVENTID                NUMBER(6)     NOT NULL
    ,EVENTTITLE             VARCHAR2(50)    NOT NULL
    ,EVENTTYPEID            NUMBER(6)     NOT NULL
    ,EVENTCREATORUSERID     NUMBER(6)     NOT NULL
    ,EVENTSTARTDATE         DATE            DEFAULT SYSDATE
    ,EVENTENDDATE           DATE            DEFAULT SYSDATE
    ,EVENTSTARTTIME         VARCHAR2(8)     NULL
    ,EVENTENDTIME           VARCHAR2(8)     NULL
    ,EVENTALLDAY            Number(1)       DEFAULT '0'
    ,EVENTMESSAGE           Varchar2(500)   NULL
    ,EVENTLOCATION          VARCHAR2(50)    NULL
    ,EVENTADDRESS           VARCHAR2(50)    NULL
    ,EVENTCITY              VARCHAR2(50)    NULL
    ,EVENTSTATE             VARCHAR2(2)     NULL
    ,EVENTZIP               VARCHAR2(5)     NULL
    ,EVENTATTACHID          NUMBER(6)     NULL
    ,CREATEDDATE            DATE            DEFAULT SYSDATE
    ,LASTUPDATEDATE         DATE            NULL,
    CONSTRAINT EVENT_EVENTID_PK PRIMARY KEY (EVENTID),
    CONSTRAINT EVENT_ATTACHID_FK FOREIGN KEY (EVENTATTACHID) REFERENCES ATTACH_T(ATTACHID),
    CONSTRAINT EVENT_TYPEID_FK FOREIGN KEY (EVENTTYPEID) REFERENCES TYPE_T(TYPEID),
    CONSTRAINT EVENT_CREATORUSERID_FK FOREIGN KEY (EVENTCREATORUSERID) REFERENCES USER_T(USERID));
/

-- ===================================================
-- CREATE SEQUENCES
-- ===================================================
CREATE SEQUENCE ATTACH_SEQ
    INCREMENT BY 1
    START WITH 55
    NOMAXVALUE
    MINVALUE 1
    NOCYCLE
    NOCACHE
/

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
DESCRIBE TYPE_T;
/
DESCRIBE ATTACH_T;
/
DESCRIBE USER_T;
/
DESCRIBE EVENT_T;
/
