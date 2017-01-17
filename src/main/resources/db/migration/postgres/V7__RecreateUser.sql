
delete from users;

ALTER TABLE users ALTER COLUMN mobile type varchar(15);

insert into users(id, name, email, username, password, address, mobile, gender, dob, email_verified, authority)
values (1, 'admin1', 'admin1@gmail.com', 'superadmin', '$2a$10$J21D9RIcyuHUndPACG2jT.SyNeaa8rhauZVEuSa6pG03aOYIMu2cO', 'Viman Nagar, Pune', '1234567891', 'M', CURRENT_DATE,
'false','ROLE_SELLER');

insert into users(id, name, email, username, password, address, mobile, gender, dob, email_verified,authority)
values (2, 'admin2', 'admin2@gmail.com', 'admin', '$2a$10$.xqGSeNfUkd0G469tdAkaOL9Lc4OBZubK5isZxu1KhpGchT./jR3O', 'Kharadi, Pune', '1234567891', 'M', CURRENT_DATE,
'false', 'ROLE_BUYER');