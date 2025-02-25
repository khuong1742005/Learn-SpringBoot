create table room
(
    id           INT auto_increment primary key,
    roomName     varchar(30),
    roomType     varchar(30),
    checkInDate  varchar(30),
    checkOutDate varchar(30),
    price        INT,
    status       varchar(30),
    userId       int
);