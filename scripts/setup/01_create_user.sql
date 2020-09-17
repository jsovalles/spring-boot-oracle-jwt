alter session set "_ORACLE_SCRIPT" = true;

CREATE USER intro_user IDENTIFIED BY mypassword;

GRANT CONNECT TO intro_user;

GRANT CREATE SESSION, GRANT ANY PRIVILEGE TO intro_user;

GRANT UNLIMITED TABLESPACE TO intro_user;

GRANT CREATE TABLE, CREATE SEQUENCE TO intro_user;

COMMIT;