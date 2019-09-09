Drop Table if exists users;

Create Table users (
    id INT auto_increment primary key,
    username VARCHAR not null,
    password VARCHAR not null,
    isAdmin BIT not null
);

Insert Into users (username, password, isAdmin) values (Ethan, pass, 0);
Insert Into users (username, password, isAdmin) values (admin, admin, 1);