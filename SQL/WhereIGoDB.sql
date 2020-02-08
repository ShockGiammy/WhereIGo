
begin;

create table Locations
(
	country VARCHAR(45) NOT NULL,
    city VARCHAR(45) NOT NULL,
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
	photo longblob,
    utente VARCHAR(20)
			references Usr(username),
	descr VARCHAR(4096),
    tipeOfPost varchar(10)
);

insert into Locations(country,city)
values ("Italy","Rome");

insert into Locations(country,city)
values ("Italy","Milan");

insert into Locations(country,city)
values ("UK","London");

insert into Locations(country,city)
values ("USA","Washington");

insert into Usr(username,passw,nome,surname,age,dateOfBirth,gender,tipeOfUser)
values ("pierC","pippo","Pierciro","Caliandro",21,"18/02/1998","Male","Traveler");

insert into Usr(username,passw,nome,surname,age,dateOfBirth,gender,tipeOfUser)
values ("shockGianmy","pluto","Gian Marco","Falcone",21,"15/03/1998","Male","Traveler");

insert into Usr(username,passw,nome,surname,age,dateOfBirth,gender,tipeOfUser)
values ("fra998","paperino","Francesco","Fanali",21,"26/06/1998","Male","Renter");

insert into Usr(username,passw,nome,surname,age,dateOfBirth,gender,tipeOfUser)
values ("adrianRob","pippo","Adrian","Minut",21,"18/02/1998","Male","Male", "Traveler");






    
    
    
	




