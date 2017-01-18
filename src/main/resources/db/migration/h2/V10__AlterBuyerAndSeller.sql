alter table users drop column address;
alter table users drop column mobile;
alter table users drop column name;
alter table users drop column email;



alter table buyers add column address varchar(100);
alter table buyers add column mobile  numeric(10) not null;
alter table buyers add column name varchar(40) not null;
alter table buyers add column email varchar(40) not null;


alter table sellers add column address varchar(100);
alter table sellers add column mobile  numeric(10) not null;
alter table sellers add column name varchar(40) not null;
alter table sellers add column email varchar(40) not null;
ALTER TABLE sellers ALTER COLUMN pan_number type varchar(50);