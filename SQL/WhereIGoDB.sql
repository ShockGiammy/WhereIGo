
begin;

create table Locations
(
	country VARCHAR(45) NOT NULL,
    city VARCHAR(45) NOT NULL,
    tipeOfPersonality VARCHAR(20),
    primary key(country,city)
);

create table Usr
(
	username VARCHAR(20) NOT NULL unique,
    passw VARCHAR(20) NOT NULL,
    nome VARCHAR(20) NOT NULL,
    surname VARCHAR(20) NOT NULL,
    age INT NOT NULL,
    dateOfBirth VARCHAR(20) NOT NULL,
    gender VARCHAR(10),
    tipeOfUser VARCHAR(20),
    tipeOfPersonality VARCHAR(20),
    primary key(username,passw)
);

create table TravelGroups
(
	groupID int,
    travName varchar(20)
			references Usr(nome),
	travSurname varchar(20)
			references Usr(surname)
);

create table Post
(
	ID int not null,
	photo LONGBLOB,
    utente VARCHAR(20)
			references Usr(username),
	descr VARCHAR(4096),
    beds VARCHAR(5),
    city VARCHAR(30),
    address VARCHAR(40),
    services int(4),
    squareMetres VARCHAR(5),
    tipologia VARCHAR(20),
    tipeOfPost VARCHAR(20),
    primary key(ID, utente)
);

insert into Locations(country,city,tipeOfPersonality)
values ("Italy","Rome", "Curious");

insert into Locations(country,city,tipeOfPersonality)
values ("Italy","Milan", "Lone wolf");

insert into Locations(country,city,tipeOfPersonality)
values ("UK","London", "Lone wolf");

insert into Locations(country,city,tipeOfPersonality)
values ("USA","Washington", "Friendly");

insert into Usr(username,passw,nome,surname,age,dateOfBirth,gender,tipeOfUser)
values ("pierC","pippo","Pierciro","Caliandro",21,"18/02/1998","Male","Traveler");

insert into Usr(username,passw,nome,surname,age,dateOfBirth,gender,tipeOfUser)
values ("shockGianmy","pluto","Gian Marco","Falcone",21,"15/03/1998","Male","Traveler");

insert into Usr(username,passw,nome,surname,age,dateOfBirth,gender,tipeOfUser)
values ("fra998","paperino","Francesco","Fanali",21,"26/06/1998","Male","Renter");

insert into Usr(username,passw,nome,surname,age,dateOfBirth,gender,tipeOfUser)
values ("adrianRob","pippo","Adrian","Minut",21,"18/02/1998","Male", "Traveler");






    
    
    
	




