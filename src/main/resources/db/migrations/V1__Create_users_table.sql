create table user
(
    userId      INT primary key,
    username    varchar(30),
    password    varchar(30),
    email       varchar(30),
    phoneNumber varchar(30),
    age         INT,
    role        varchar(30)
);