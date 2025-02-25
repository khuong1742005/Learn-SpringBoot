create table user
(
    userId      INT auto_increment primary key,
    username    varchar(30),
    password    varchar(30),
    email       varchar(30),
    phoneNumber varchar(30),
    role        varchar(30)
);