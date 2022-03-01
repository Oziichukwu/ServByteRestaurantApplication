create database serv_db;

create user 'serv_byte_user'@'localhost' identified by 'password';
grant all privileges on serv_db.* to 'serv_byte_user'@'localhost';
flush privileges;
