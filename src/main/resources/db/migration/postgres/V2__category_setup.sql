
CREATE TABLE category (
	id serial primary key,
	name varchar(255) not null,
	active boolean not null default true
);

insert into category(id, name) values (1, 'Electronics');
insert into category(id, name) values (2, 'Books');