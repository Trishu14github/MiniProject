use miniproject;
create table Cart(
C_id int not null auto_increment primary key,
pro_Id int not null,
email varchar(255) not null,
foreign key(pro_Id) references Productinfo(p_id));
commit;

insert into Cart(pro_Id,email)
values(1,"Trisha@gmail.com"),
(2,"minu@gmail.com");
select * from Cart;

create table BuyProduct(
pro_Id int,
email varchar(50),
BuyProId int not null primary key auto_increment,
foreign key(Pro_id) references productinfo(p_id)
);

insert into BuyProduct(pro_Id,email)
values(1,"kari@gmail.com"),
(2,"minu@gmail.com");
select * from BuyProduct;