
drop table users;

CREATE TABLE users (
	id serial primary key,
	name varchar(40) not null,
	email varchar(40) not null,
	username varchar(15) not null,
	password varchar(16) not null,
	address varchar(100) not null,
	mobile numeric(10) not null,
	type varchar(6) not null,
	gender char(1) default null,
	dob DATE not null,
	is_email_verified boolean
);

insert into users(id, name, email, username, password, address, mobile, type, gender, dob, is_email_verified)
values (1, 'admin1', 'admin1@gmail.com', 'superadmin', 'superadmin123', 'Viman Nagar, Pune', '1234567891', 'Buyer', 'M', CURRENT_DATE, 'false');

insert into users(id, name, email, username, password, address, mobile, type, gender, dob, is_email_verified)
values (2, 'admin2', 'admin2@gmail.com', 'admin', 'admin123', 'Kharadi, Pune', '1234567891', 'Seller', 'M', CURRENT_DATE, 'false');

