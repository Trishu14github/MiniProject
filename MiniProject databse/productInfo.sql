use miniproject;
create table ProductInfo(
P_Id int not null auto_increment,
P_Name varchar(255) not null,
P_description varchar(255) not null,
Price int not null,
Quantity int not null,
primary key(P_Id));

insert into ProductInfo(P_Name,P_description,Price,Quantity)
values("Samsung","samsung galaxy A51",70000,60),
("Apple","Apple iphone11,11pro",90000,80),
("Oppo","OPPO A31,OPPO A510",40000,10),
("VIVO","Vivo v17 pro",20000,5),
("Lenovo","Linovo vibe k5 note",30000,30),
("Air conditioner","used for cooling",50000,3),
("Digital Camera","Records Photographs",7000,9),
("EarPhones","place by a headband",2000,7),
("External Hard Disk","Saved the document",4000,6),
("Printer","used for printing",5000,32);
select * from ProductInfo;






