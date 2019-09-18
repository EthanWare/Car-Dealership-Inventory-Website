Drop Table if exists car;

Create Table car (
    id INT auto_increment primary key,
    year INT not null,
    make VARCHAR(250) not null,
    model VARCHAR(250) not null,
    type VARCHAR(250) not null,
    color VARCHAR(250) not null
);

Insert Into car (year, make, model, type, color) values
    (2005, 'Honda', 'Accord', 'Coupe', 'Grey'),
    (1997, 'Ford', 'Ranger', 'Truck', 'Gold'),
    (2004, 'Toyota', 'Camry', 'Sedan', 'Blue'),
    (1995, 'Mitsubishi', 'Eclipse', 'Convertible', 'Orange'),
    (2006, 'Ford', 'GT', 'Coupe', 'Red');