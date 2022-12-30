create database MiniProject;
use miniproject;
create table customerinfo(
Email varchar(50) not null primary key,
Password varchar(50) not null,
CustomerName varchar(255) not null,
MobileNumber varchar(255) not null
);
insert into customerinfo(Email,Password,CustomerName,MobileNumber)
values("karishma@gmail.com",123,"karishma",818181990),
("Minu@gmail.com",456,"Minu",8989786789),
("mahi@gmail.com",789,"mahi",9898786767),
("ashu@gmail.com",890,"ashu",9089786756),
("Dhairya@gmail.com",908,"dhairya",9089786756),
("Trisha@gmail.com",908,"Trisha",90898765);
select * from customerinfo;