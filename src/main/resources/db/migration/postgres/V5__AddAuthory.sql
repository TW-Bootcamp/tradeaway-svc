
delete from users;

ALTER TABLE users ALTER COLUMN password type varchar(100);

ALTER TABLE users ADD authority varchar(50) DEFAULT 'ROLE_BUYER' NOT NULL;

insert into users(id, name, email, username, password, address, mobile, type, gender, dob, email_verified, authority)
values (1, 'admin1', 'admin1@gmail.com', 'superadmin', '$2a$10$J21D9RIcyuHUndPACG2jT.SyNeaa8rhauZVEuSa6pG03aOYIMu2cO', 'Viman Nagar, Pune', '1234567891', 'Buyer', 'M', CURRENT_DATE,
'false','ROLE_SELLER');

insert into users(id, name, email, username, password, address, mobile, type, gender, dob, email_verified,authority)
values (2, 'admin2', 'admin2@gmail.com', 'admin', '$2a$10$.xqGSeNfUkd0G469tdAkaOL9Lc4OBZubK5isZxu1KhpGchT./jR3O', 'Kharadi, Pune', '1234567891', 'Seller', 'M', CURRENT_DATE,
'false', 'ROLE_BUYER');

