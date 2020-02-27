begin;

create table Locations
(
	country VARCHAR(45) NOT NULL,
    city VARCHAR(45) NOT NULL,
    tipeOfPersonality VARCHAR(20),
    photo LONGBLOB,
    description VARCHAR(4096),
    primary key(city)
);

create table Usr
(
	username VARCHAR(20) NOT NULL unique,
    passw VARCHAR(20) NOT NULL,
    nome VARCHAR(20) NOT NULL,
    surname VARCHAR(20) NOT NULL,
    dateOfBirth VARCHAR(20) NOT NULL,
    gender VARCHAR(10),
    tipeOfUser VARCHAR(20),
    tipeOfPersonality VARCHAR(20),
    primary key(username,passw)
);

create table TravelGroups
(
	groupID int,
	travCity VARCHAR(45)
			 references Locations(city),
	groupOwner VARCHAR(20) not null
			 references Usr(username),
	title VARCHAR(50) NOT NULL,
	primary key(groupID, groupOwner)
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
    services BLOB,
    squareMetres VARCHAR(5),
    tipologia VARCHAR(20),
    tipeOfPost VARCHAR(20),
    primary key(ID, utente)
);

insert into Locations(country,city,tipeOfPersonality)
values ("Zanzibar","Tanzania", "Curious");

insert into Locations(country,city,tipeOfPersonality)
values ("Turkey","Istanbul", "Curious");

insert into Locations(country,city,tipeOfPersonality)
values ("Morocco","Marrakech", "Curious");

insert into Locations(country,city,tipeOfPersonality)
values ("UK","London", "Lone wolf");

insert into Locations(country,city,tipeOfPersonality)
values ("Australia","Ayers rock", "Lone wolf");

insert into Locations(country,city,tipeOfPersonality)
values ("Russia","Transiberiana", "Lone wolf");

insert into Locations(country,city,tipeOfPersonality, description)
values ("Netherlands","Amsterdam", "Friendly", "Amsterdam, conosciuta anche come la Venezia del Nord 
per i suoi numerosi canali, è una delle città più affascinanti del mondo, 
un piccolo paradiso tanto amato ed apprezzato dai turisti per il suo aspetto romantico e mutevole, là dove perdersi tra le molteplici attività culturali, 
le architetture antiche e moderne ed i suoi musei. 
Amsterdam è caratterizzata anche da un lato più giovanile, quello che l'ha portata a conquistarsi il titolo capitale del divertimento e del proibito, 
famosa per i suoi coffeeshop e l'atmosfera rilassata e liberale che si respira in ogni angolo della città.");

insert into Locations(country,city,tipeOfPersonality)
values ("Spain","Ibiza", "Friendly");

insert into Locations(country,city,tipeOfPersonality)
values ("USA","San Francisco", "Friendly");

insert into Locations(country,city,tipeOfPersonality)
values ("Ungary","Budapest", "Lazybone");

insert into Locations(country,city,tipeOfPersonality)
values ("UK","Bath", "Lazybone");

insert into Locations(country,city,tipeOfPersonality)
values ("Italy","Saturnia", "Lazybone");

insert into Locations(country,city,tipeOfPersonality)
values ("Uganda","Paraa", "Adventurer");

insert into Locations(country,city,tipeOfPersonality)
values ("Tanzania","Kilimangiaro", "Adventurer");

insert into Locations(country,city,tipeOfPersonality)
values ("Turkmenistan","Karakum desert", "Adventurer");

insert into Usr(username,passw,nome,surname,dateOfBirth,gender,tipeOfUser,tipeOfPersonality)
values ("pierC","pippo","Pierciro","Caliandro","18/02/1998","Male","Traveler","Friendly");

insert into Usr(username,passw,nome,surname,dateOfBirth,gender,tipeOfUser)
values ("shockGiammy","pluto","Gian Marco","Falcone","16/03/1998","Male","Traveler");

insert into Usr(username,passw,nome,surname,dateOfBirth,gender,tipeOfUser)
values ("fra998","paperino","Francesco","Fanali","26/06/1998","Male","Renter");

insert into Usr(username,passw,nome,surname,dateOfBirth,gender,tipeOfUser)
values ("adrianRob","pippo","Adrian","Minut","18/02/1998","Male", "Traveler");

insert into travelgroups(groupID, travCity, groupOwner, title)
values(001, "Amsterdam", "shockGiammy", "Amsterdam Museums (I swear)");

insert into travelgroups(groupID, travCity, groupOwner, title)
values(002, "San Francisco", "adrianRob", "Trip to the USA");




    
    
    
	




