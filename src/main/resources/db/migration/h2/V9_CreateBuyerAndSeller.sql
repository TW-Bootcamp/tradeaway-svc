CREATE TABLE sellers (
id serial primary key,
pan_number varchar(255) not null,
experience_in_months int not null,
user_id INT not null,
FOREIGN KEY (user_id) REFERENCES users (id)
);
CREATE TABLE buyers (
id serial primary key,
gender char(1) default null,
dob DATE not null,
user_id INT not null,
FOREIGN KEY (user_id) REFERENCES users (id)
);
