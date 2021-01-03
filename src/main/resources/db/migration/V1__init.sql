create table users(
id serial,
name varchar(50) not null,
email varchar(100) not null unique,
cpf varchar(100) not null unique,
birthday date not null,
primary key (id));
