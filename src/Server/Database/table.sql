SET DATABASE DEFAULT TABLE TYPE CACHED;
SET AUTOCOMMIT TRUE;
CREATE TABLE IF NOT EXISTS user(
    id varchar(8) not null,
    passwd varchar(100) not null,
    fullname varchar(50),
    status varchar(3),
    sockethash int);
INSERT INTO user(id,passwd,fullname) VALUES('s3342135','1234','Huy');
INSERT INTO user(id,passwd,fullname) VALUES('s3342174','1234','Nghia');
INSERT INTO user(id,passwd,fullname) VALUES('s3342133','1234','San');
INSERT INTO user(id,passwd,fullname) VALUES('s3342137','1234','Hung');