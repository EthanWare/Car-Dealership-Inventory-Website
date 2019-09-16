Drop Table if exists user;

Create Table user (
    id INT auto_increment primary key,
    user_name VARCHAR(250) not null,
    password VARCHAR(250) not null,
    is_admin INT not null
);

Insert Into user (user_name, password, is_admin) values ('Ethan', 'pass', 0);
Insert Into user (user_name, password, is_admin) values ('admin', 'admin', 1);