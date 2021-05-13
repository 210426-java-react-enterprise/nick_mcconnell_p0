--create schema bank;
select * from customers;
select * from accounts;
select * from balances;

truncate customers *;

create table customers (
	customer_id serial,
	username varchar(20) unique not null,
	password varchar(255) not null,
	email varchar(255) not null,
	first_name varchar(25) not null,
	last_name varchar(25) not null,
	age int check(age>0),
	
	constraint pk_users primary key (customer_id)
	
);

select * from accounts;
delete from accounts where customer_id =6;
drop table accounts;
insert into accounts(account_type, customer_id)
 values (
'savings',
4
),
('checking', 4);


create table accounts (
	account_id serial,
	account_type varchar(20) not null,
	customer_id int,
	
	constraint pk_accounts primary key (account_id),
	
	constraint fk_account_customer
		foreign key (customer_id)
		references customers
	
);
drop table balances
select * from balances;
delete from balances where balance_id = 8;
insert into balances (balance, account_id)
values (100.25, 1), (57025.56, 2);

update balances
set balance=100
where balance_id = 7;


create table balances (
	balance_id serial,
	balance float not null,
	account_id int,
	
	constraint pk_balances primary key (balance_id),
	
	constraint fk_balance_account
		foreign key (account_id)
		references accounts
);

select accounts.account_type, balances.balance
from customers
inner join accounts 
on customers.customer_id = accounts.customer_id
inner join balances 
on accounts.account_id = balances.account_id;
