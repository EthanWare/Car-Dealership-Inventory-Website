GRANT ALL PRIVILEGES ON DATABASE docker TO docker;

CREATE TABLE IF NOT EXISTS user_table (
    id SERIAL primary key,
    user_name VARCHAR(250) not null,
    password VARCHAR(250) not null,
    is_admin INT not null
);
Insert Into user_table (user_name, password, is_admin) values ('Ethan', 'pass', 0);
Insert Into user_table (user_name, password, is_admin) values ('admin', 'admin', 1);


Create Table IF NOT EXISTS car_table (
    id SERIAL primary key,
    year INT not null,
    make VARCHAR(250) not null,
    model VARCHAR(250) not null,
    type VARCHAR(250) not null,
    color VARCHAR(250) not null
);
Insert Into car_table (year, make, model, type, color) values
    (2005, 'Honda', 'Accord', 'Coupe', 'Grey'),
    (1997, 'Ford', 'Ranger', 'Truck', 'Gold'),
    (2004, 'Toyota', 'Camry', 'Sedan', 'Blue'),
    (1995, 'Mitsubishi', 'Eclipse', 'Convertible', 'Orange'),
    (2006, 'Ford', 'GT', 'Coupe', 'Red');