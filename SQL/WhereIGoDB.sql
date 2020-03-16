begin;

create table Locations
(
	country VARCHAR(45) NOT NULL,
    city VARCHAR(45) NOT NULL primary key,
    tipeOfPersonality VARCHAR(20),
    photo1 LONGBLOB,
    descr VARCHAR(4096)
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
    profilePicture LONGBLOB,
    userStatus VARCHAR(15),
    primary key(username,passw)
);

create table TravelGroups
(
	travCity VARCHAR(45)
			 references Locations(city),
	groupOwner VARCHAR(20) not null
			 references Usr(username),
	title VARCHAR(50) NOT NULL,
	primary key(title, groupOwner)
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
    squareMetres VARCHAR(10),
    tipologia VARCHAR(20),
    tipeOfPost VARCHAR(20),
    primary key(ID, utente)
);

create table Chat
(
	ID int auto_increment primary key,
	sender VARCHAR(20)
			references Usr(username),
	receiver VARCHAR(20)
			references Usr(username),
	message VARCHAR(1000)
);

create table Tickets
(
	ID int primary key AUTO_INCREMENT,
	depCity VARCHAR(50),
    arrCity VARCHAR(50),
	dateOfDep date,
    dateOfArr date,
    cost float,
	numOfTick int
);

create table ParticipatesTo
(
	participant VARCHAR(20)
			references Usr(username),
	grp int 
			references TravelGroups(ID)
);

create table Buys
(
	ticket int 
			references tickets(ID),
	passenger varchar(20)
			references Usr(username)
);
	
insert into Chat(ID, sender, receiver, message)
values ("0","ciao", "shockGiammy", "come stai");

insert into Post(ID,  photo, utente, descr, beds, city, address, services, squareMetres, tipologia, tipeOfPost)
values(1, null, "ciao2", "desc", "4", "cave", "mia via", null, "< 20", "appartamento", null);

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

insert into Locations(country,city,tipeOfPersonality, descr)
values ("Netherlands","Amsterdam", "Friendly", "Amsterdam, conosciuta anche come la Venezia del Nord 
per i suoi numerosi canali, è una delle città più affascinanti del mondo, 
un piccolo paradiso tanto amato ed apprezzato dai turisti per il suo aspetto romantico e mutevole, là dove perdersi tra le molteplici attività culturali, 
le architetture antiche e moderne ed i suoi musei. 
Amsterdam è caratterizzata anche da un lato più giovanile, quello che l'ha portata a conquistarsi il titolo capitale del divertimento e del proibito, 
famosa per i suoi coffeeshop e l'atmosfera rilassata e liberale che si respira in ogni angolo della città.");

insert into Locations(country,city,tipeOfPersonality, descr)
values ("Spain","Ibiza", "Friendly", "Rinomata per la vita notturna e per i locali di tendenza, che la rendono una delle mete preferite dei giovani di tutta l’Europa e non solo,
Ibiza è la terza isola per estensione dell’Arcipelago spagnolo delle Baleari.
Dichiarata Patrimonio dell’Umanità nel 1999, Ibiza è un’isola calda sotto tutti i punti di vista:
climatico, caratteriale e geologico. Diversamente da Maiorca e  come Formentera, l’isola è molto arida e rocciosa. 
La modica presenza di pianure verdeggianti viene, però, compensata da un meraviglioso cielo, azzurro praticamente per l’intero arco dell’anno, e da un mare cristallino dai tratti caraibici.
I circa 100.000 abitanti dell’isola si distribuiscono in cinque comuni: il capoluogo Ibiza, Santa Eulària, Sant Antoni, Sant Joan e Sant Josep.
Nella bella Ibiza è impossibile annoiarsi. La vita isolana è movimentata praticamente ventiquattro ore su ventiquattro.
Di giorno, vi è una vasta offerta di attività nautiche, praticabili in quasi ogni angolo dell’isola, anche per conto proprio.
Molto divertenti sono ad esempio le escursioni via mare per raggiungere le spiagge di Formentera.
Le serate sono all’insegna della movida, grazie all’interminabile schiera di locali, discoteche e pub presenti sull’isola.
Notti magiche, quindi, quelle di Ibiza, che proseguono, per i più “audaci”, fino alle prime ore del mattino, nei famosi after hours.");

insert into Locations(country,city,tipeOfPersonality, descr)
values ("USA","San Francisco", "Friendly", "Da città simbolo degli hippie e della controrivoluzione giovanile degli anni Sessanta a cuore pulsante di una rinascita culturale guidata dall’arte e dal design e soprattutto dalla tecnologia.
È questa la storia di San Francisco, la città più liberal d’America nonché uno dei simboli più famosi al mondo della West Coast statunitense.
Adagiata su una baia e perennemente illuminata da una luce naturale abbagliante, San Francisco, è una città distribuita su ben 40 colline.
Per questo motivo i suoi marciapiedi sono spesso sostituiti da scalinate. 
I caratteristici tram a cremagliera (cable car),  aperti su entrambi i lati, arrancano sulle salite della città che poi si trasformano in discese a picco verso l’azzurro dell’Oceano Pacifico.
Come succede a Lombard Street, la strada più fotografata del mondo e postata su Instagram, location perfetta per i rocamboleschi inseguimenti dei film d’azione tanto cari al cinema hollywoodiano.
A colpire subito l’attenzione dei turisti è sicuramente l’eclettismo architettonico ma anche culturale di quella che è stata definita “la città della pioggia, del vento e della nebbia“");

insert into Locations(country,city,tipeOfPersonality, descr)
values ("Ungary","Budapest", "Lazybone", "In origine formata da due città distinte, Buda e Pest, sviluppatesi sulle rive del Danubio, Budapest è considerata da molti come la Parigi dell’Est grazie anche all’atmosfera fin de siècle che si respira lungo i suoi viali.
Da un lato l’antica e Buda, con il suo castello, patrimonio dell’UNESCO, il Palazzo Reale, la chiesa di Matthias e i bastioni dei Pescatori che dall’alto di un colle offrono una splendida vista sulla città.
Dall’altro la moderna Pest, vivace e dalle ampie vie accoglie i più famosi monumenti della città, come il Parlamento e la Basilica di Santo Stefano, insieme a fulgidi esempi di Art Nouveau, tra questi spicca il bellissimo Ponte delle Catene.
Da non dimenticare le pasticcerie con la calorica Dobostorta del Café Gerbeaud e le terme.
Budapest infatti vanta diverse stazioni termali, già note all’epoca romana furono ampliate durante il periodo turco fino al 1920 quando si guadagnò la reputazione di città delle terme.
Infine l’Isola Margherita, sospesa in mezzo al Danubio è un piccolo polmone verde in cui passeggiare e rilassarsi nelle calde giornate primaverili ed estive.");

insert into Locations(country,city,tipeOfPersonality, descr)
values ("UK","Bath", "Lazybone" , "Elegante e armoniosa cittadina termale sul fiume Avon, nel distretto unitario di Bath e North East Somerset (un tempo contea di Avon), 18 km a SE di Bristol.
È centro residenziale e meta turistica molto frequentata, la cui importanza e lo stesso nome sono legati alle terme, già note ai Romani.
Dell'antica Aquae Sulis gli scavi iniziati nel 1755 hanno portato alla luce notevoli resti.
La nuova Bath si formò attorno a un'abbazia benedettina e prese a svolgere un'attività prevalentemente commerciale e manifatturiera (lanifici).
A partire dal sec. XVIII si affermò come località di soggiorno e centro termale. Per il fascino dell'impianto urbanistico la città è stata dichiarata Patrimonio mondiale dell'umanità dall'UNESCO.
Nel 1966 è stato fondato il Politecnico di Bath.
Bath è una città vivace dal punto di vista artistico-culturale.
Ogni mese vi si svolge una manifestazione diversa, ma è soprattutto durante la stagione estiva che i teatri, le piazze e i parchi cittadini sono caratterizzati da svariati concerti e mostre.");

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

insert into Usr(username,passw,nome,surname,dateOfBirth,gender,tipeOfUser, userStatus)
values ("shockGiammy","pluto","Gian Marco","Falcone","16/03/1998","Male","Traveler", "online");

insert into Usr(username,passw,nome,surname,dateOfBirth,gender,tipeOfUser, userStatus)
values ("fra998","paperino","Francesco","Fanali","26/06/1998","Male","Renter", "offline");

insert into Usr(username,passw,nome,surname,dateOfBirth,gender,tipeOfUser, userStatus)
values ("ciao2","paperino","Francesco","Fanali","26/06/1998","Male","Renter", "offline");

insert into Usr(username,passw,nome,surname,dateOfBirth,gender,tipeOfUser)
values ("adrianRob","pippo","Adrian","Minut","18/02/1998","Male", "Traveler");

insert into travelgroups(travCity, groupOwner, title)
values("Amsterdam", "shockGiammy", "Amsterdam Museums (I swear)");

insert into travelgroups(travCity, groupOwner, title)
values("San Francisco", "adrianRob", "Trip to the USA");

insert into Tickets (ID, depCity, arrCity, dateOfDep, dateOfArr, cost,numOfTick)
values (002, "Roma-Fiumicino" , "Berlino", "2020-04-24", "2020-04-30", 550.15,40);

insert into Tickets (ID, depCity, arrCity, dateOfDep, dateOfArr, cost,numOfTick)
values (003, "Roma-Ciampino" , "Amsterdam", "2020-03-25", "2020-03-26", 650.15,30);

insert into Tickets (ID, depCity, arrCity, dateOfDep, dateOfArr, cost,numOfTick)
values (004, "Milano-Malpensa" , "Budapest", "2020-03-20", "2020-03-22", 250.35,20);

insert into Tickets (ID, depCity, arrCity, dateOfDep, dateOfArr, cost,numOfTick)
values (005, "Milano-Linate" , "Bath", "2020-06-20", "2020-06-22", 340,25);

insert into Tickets (ID, depCity, arrCity, dateOfDep, dateOfArr, cost,numOfTick)
values (006, "Torino-Caselle" , "San Francisco", "2020-07-23", "2020-07-25", 850,28);

insert into Tickets (ID, depCity, arrCity, dateOfDep, dateOfArr, cost,numOfTick)
values (007, "Roma-Fiumicino" , "Berlino", "2020-03-20", "2020-03-22", 450.15,40);

insert into Tickets (ID, depCity, arrCity, dateOfDep, dateOfArr, cost,numOfTick)
values (008, "Roma-Fiumicino" , "Berlino", "2020-03-20", "2020-03-22", 452.15,0);

    
    
    
	




