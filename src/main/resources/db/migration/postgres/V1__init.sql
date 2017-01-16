
CREATE TABLE users (
	id serial primary key,
	username varchar(255) not null,
	password varchar(255) not null
);

insert into users(id, username, password) values (1, 'superadmin', 'superadmin123');
insert into users(id, username, password) values (2, 'admin', 'admin123');
