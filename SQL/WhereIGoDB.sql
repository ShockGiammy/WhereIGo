flush binary logs;
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
    primary key(username)
);

create table TravelGroups
(
    travCity VARCHAR(45),
	groupOwner VARCHAR(20) not null,
	title VARCHAR(50) unique NOT NULL,
	foreign key (groupOwner)
			references Usr(username) on delete cascade,
	primary key(groupOwner, title)
);

create table Post
(
	ID int not null,
	photo LONGBLOB,
    utente VARCHAR(20),
	descr VARCHAR(4096),
    beds VARCHAR(5),
    city VARCHAR(30),
    address VARCHAR(40),
    services BLOB,
    squareMetres VARCHAR(10),
    tipologia VARCHAR(20),
    foreign key (utente) 
			references Usr(username) on delete cascade,
    primary key(ID, utente)
);

create table Chat
(
	ID int auto_increment primary key,
	sender VARCHAR(20),
	receiver VARCHAR(50),
	message VARCHAR(1000),
    foreign key (sender)
			references Usr(username) on delete cascade
);

create table Tickets
(
	ID int primary key,
	depCity VARCHAR(50),
    arrCity VARCHAR(50),
	dateOfDep date,
    dateOfArr date,
    cost float,
	numOfTick int
);

create table ParticipatesTo
(
	participant VARCHAR(20),
	grp VARCHAR(50),
    foreign key (participant)
			references Usr(username) on delete cascade,
	foreign key (grp)
			references travelgroups(title) on delete cascade
);

create table Buys
(
	ticket int, 
	passenger varchar(20),
    foreign key (ticket)
			references tickets(ID) on delete cascade,
	foreign key (passenger)
			references Usr(username) on delete cascade
);

insert into Usr(username,passw,nome,surname,dateofbirth,gender,tipeofuser,tipeOfPersonality,profilepicture,userstatus)
values("Traveler","Traveler","Traveler","Traveler","16-03-1998","Female","Traveler","Friendly",LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/PicImages/avatar.png'),"offline");

insert into Usr(username,passw,nome,surname,dateofbirth,gender,tipeofuser,tipeOfPersonality,profilepicture,userstatus)
values("Skyler","Skyler","Skyler","White","1970-08-11","Female","Traveler","Adventurer",LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/PicImages/skyler.jpg'),"offline");

insert into Usr(username,passw,nome,surname,dateofbirth,gender,tipeofuser,tipeOfPersonality,profilepicture,userstatus)
values("Walter","Walter","Walter","White","1959-09-07","Male","Traveler","Lazybone",LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/PicImages/walter.jpg'),"offline");

insert into Usr(username,passw,nome,surname,dateofbirth,gender,tipeofuser,tipeOfPersonality,profilepicture,userstatus)
values("Gus","Gus","Gus","Fring","1950-06-12","Male","Traveler","Lone wolf",LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/PicImages/gus.jpg'),"offline");

insert into Usr(username,passw,nome,surname,dateofbirth,gender,tipeofuser,tipeOfPersonality,profilepicture,userstatus)
values("Renter","Renter","Renter","Renter","1998-03-16","Female","Renter",null,LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/PicImages/avatar.png'),"offline");

insert into Usr(username,passw,nome,surname,dateofbirth,gender,tipeofuser,tipeOfPersonality,profilepicture,userstatus)
values("Hank","Hank","Hank","Schrader","1970-03-13","Male","Renter",null,LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/PicImages/hank.jpg'),"offline");

insert into Usr(username,passw,nome,surname,dateofbirth,gender,tipeofuser,tipeOfPersonality,profilepicture,userstatus)
values("Jesse","Jesse","Jesse","Pinkman","1984-09-27","Male","Renter",null,LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/PicImages/jesse.jpg'),"offline");

insert into Locations(country,city,tipeOfPersonality, photo1, descr)
values ("Tanzania","Zanzibar", "Adventurer", LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/Locations/zanzibar.jpg'), "Zanzibar is the semi-autonomous part of Tanzania in East Africa.
It is composed of the Zanzibar Archipelago in the Indian Ocean, 25–50 kilometres (16–31 mi) off the coast of the mainland, and consists of many small islands and two large ones: Unguja (the main island, referred to informally as Zanzibar) and Pemba.
The capital is Zanzibar City, located on the island of Unguja.
Its historic centre is Stone Town, which is a World Heritage Site.
Zanzibar’s coastline offers some of the best beaches in the world, but sand and surf vary depending on what side of the island you’re on.
On the east coast, waves break over coral reefs and sand bars offshore, and low tide reveals small pools of starfish, small minnows, and anemones.
Up north, ocean swimming is much less susceptible to the tides, and smooth beaches and white sand make for dazzling days in the sun.");

insert into Locations(country,city,tipeOfPersonality, photo1, descr)
values ("Turkey","Istanbul", "Adventurer", LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/Locations/istanbul.jpg'), "Istanbul, a fascinating city built on two Continents, divided by the Bosphorus Strait.
This is one of the greatest cities in the world where you can see a modern western city combined with a traditional eastern city, it's a melting pot of many civilizations and different people.
Istanbul was also announced by the European Union as the 2010 European Capital of Culture.
Since the Republic in 1923, Istanbul continued to grow and today it boosted its population over 15,5 million people living in this spread out city.
This makes Istanbul the largest city of Turkey and one of the biggest cities in the world.");

insert into Locations(country,city,tipeOfPersonality, photo1, descr)
values ("Morocco","Marrakech", "Adventurer", LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/Locations/marrakech.jpg'), "Marrakech is the second largest city of Morocco and is known as the Red City.
It has about 800,000 inhabitants and most of the houses are colored read.
Marrakech MedinaIt is truly a colorful city of entertainment and is called the one of the pearls of morocco.
It is the major economic center and has several upcoming industries and markets.
You would find it very interesting to roam around the city of Marrakech.
The little souks in the small alleys and squares are very interesting and you can find them dedicated to specific crafts.
You can even watch part of their process of making them.");

insert into Locations(country,city,tipeOfPersonality, photo1, descr)
values ("UK","London", "Lone wolf", LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/Locations/london.jpg'), "Nothing prepares you for your first taste of London.
This great world city is far more than just the capital of the United Kingdom of Great Britain and Northern Ireland.
London is bustling, vibrant, multicultural and cosmopolitan.
London is both old and new, a place where traditional pubs rub shoulders with the newest cocktail bars.
Its most ancient castle is right next door to its newest skyscrapers.
London's energy, as 8.6 million people go about their everyday lives, is tangible in every journey on the Underground and every walk along the banks of the River Thames.
From the palaces of shopping to the real Buckingham Palace, London really does have it all.");

insert into Locations(country,city,tipeOfPersonality, photo1, descr)
values ("Australia","Ayers rock", "Lone wolf", LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/Locations/ayers_rock.jpg'), "The monoliths unbelievable size and immense cultural significance to local Indigenous people has made it an icon of Australia, and its magnificence cannot be understated.
The unmitigated remoteness of this destination has done nothing to curb the enthusiasm of people from around the world to come and visit the rock, watching it stand magnificently above the flat, arid landscape that’s spread out around it in every direction.
More than 250,000 people visit the Uluru-Kata Tjuta National Park each year, despite it being hundreds of kilometres from the nearest major town and almost 1000km from the Northern Territory’s capital Darwin. 
Although this may seem like a setback, it does wonders to make this destination a truly special one. In an environment largely unimpacted by European settlement, Uluru and the Red Centre have mostly maintained their original beauty.
A trip to Australia is simply not complete without a visit to Uluru. 
Do not miss your opportunity to explore the majestic monolith and have your breath stolen by the stunning horizon in front of you.");

insert into Locations(country,city,tipeOfPersonality, photo1, descr)
values ("South Korea","Gyeongju", "Lone wolf", LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/Locations/gyeongju.jpg'), "If you’re into ancient cultures and traditions – or just want a break from the fast cars and bright lights of South Korea’s cities – take a day trip to Gyeongju, South Korea’s ancient capital.
Gyeongju is a city in the North Gyeongsang Province, near the southeastern coast of mainland Korea. It’s an hour’s drive or bus ride north of Busan, a perfect day trip for those seeking a cultural experience.
If you’re a foodie and wondering about the cuisine, food in Gyeongju is generally typical of the cuisine elsewhere in Gyeongsang province – spicy and salty. However, they do have local specialties that include Gyeongju bread, a red-bean pastry, and beopju, a traditional Korean liquor.
If you’re looking for seafood dishes, though, head to Gampo-eup, a coastal town on the east district of Gyeongju where you can enjoy fresh seafood and jeotgal (fermented salted seafood). You’ll find hundreds of seafood restaurants along Gampo Harbor where you can get raw fish dishes, abalone soup, grilled seafood, and more.");

insert into Locations(country,city,tipeOfPersonality, photo1, descr)
values ("Netherlands","Amsterdam", "Friendly",LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/Locations/amsterdam.jpg'), "Amsterdam is one of the greatest small cities in the world.
From Amsterdam canals to world-famous Amsterdam museums and historical Amsterdam sights, it is one of the most romantic and beautiful cities in Europe.
Canal cruises are a popular way to see the city from the perspective of its canals.
Amsterdam is also a city of tolerance and diversity.
It has all the advantages of a big city: rich culture, lively Amsterdam nightlife, international restaurants, good transport - but is quiet, and largely thanks to its extensive canals, has a little road traffic.
In this city your destination is never far away, but get a bike for an authentic local experience.");

insert into Locations(country,city,tipeOfPersonality,photo1, descr)
values ("Spain","Ibiza", "Friendly", LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/Locations/ibiza.jpg'), "Ibiza is often referred to as the “white island” and “party capital of the world” - a neat pair of monikers to describe the difference between day and night in this beautiful Spanish paradise.
Ibiza is home to more than 60 beaches that attract visitors from all over the globe, and boasts some of the world’s best nightclubs, which keep partiers entertained from sundown until dawn.
There are plenty of things to see and do in Ibiza, with a sunset boat cruise topping the list of relaxation.
Beneath the waves, giant grouper and moray eels haunt the reefs and wrecks of the Mediterranean.
Alternatively, visitors can explore the island’s mystery and history on land at the Can Marca smugglers’ caves, historic cemeteries, quaint villages, and pirate towers dotted about the island.");

insert into Locations(country,city,tipeOfPersonality, photo1, descr)
values ("USA","San Francisco", "Friendly", LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/Locations/sanfrancisco.jpg'), "San Francisco holds a secure place in the United States’ romantic dream of itself : a cool, elegant, handsome, worldly seaport whose steep streets offer breathtaking views of one of the world’s greatest bays.
According to the dream, San Franciscans are sophisticates whose lives hold full measures of such civilized pleasures as music, art, and good food.
Their children are to be pitied, for, as the wife of publishing magnate Nelson Doubleday once said, “They will probably grow up thinking all cities are so wonderful.
” To San Franciscans their city is a magical place, almost an island, saved by its location and history from the sprawl and monotony that afflicts so much of urban California.");

insert into Locations(country,city,tipeOfPersonality, photo1, descr)
values ("Ungary","Budapest", "Lazybone", LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/Locations/budapest.jpg'),"Originally formed by two different cities, Buda and Pest, which developed on the shores of the Danube, Budapest is considered like the East Paris thanks to its 'fin de siècle' atmosphere that can be smelled along its avenues.
On one hand the ancient Buda, with its Castle, UNESCO heritage, the Royal Palace Matthias' curch and Fishmen's ramparts.
On the other hand the modern Pest, lively and with wide avenues, which welcomes the most famous city's monuments, like the Parliament and Saint Stephen's curch.
Last, but not least, the spas : Budapest has a lot of them, well know since the Roman's age, which were expanded during the Turkish persiod until 1920, when Budapest achieved the title of 'City of spas'");

insert into Locations(country,city,tipeOfPersonality,photo1, descr)
values ("UK","Bath", "Lazybone", LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/Locations/bath.jpg'), "Built for pleasure and relaxation, beautiful Bath has been a wellbeing destination since Roman times. 
The waters are still a big draw, both at the ancient Roman Baths and the thoroughly modern Thermae Bath Spa, which houses the only natural thermal hot springs in Britain you can bathe in.
Bath’s compact, visitor-friendly centre is overflowing with places to eat and drink, plus some of the finest independent shops in Britain, making it the ideal city break.
Immerse yourself in Bath’s remarkable collection of museums and galleries, and enjoy year-round festivals, theatre, music and sports.");

insert into Locations(country,city,tipeOfPersonality, photo1, descr)
values ("Dominican Republic","Santo Domingo", "Lazybone", LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/Locations/santo_domingo.jpg'), "The big city with Caribbean small town flair - the capital Santo Domingo did not seem as much to us as a big city.
The relaxed atmosphere of the locals, the historic buildings and countless street food stands made us feel like being in a small fishing village.
Families and people interested in culture romp around in the narrow alleys, armed with a sun hat, beach towel and a freshly squeezed juice.
If you want to immerse yourself in the Dominican culture and experience history as well as long beach days, Santo Domingo is the place for you.");

insert into Tickets (ID, depCity, arrCity, dateOfDep, dateOfArr, cost,numOfTick)
values(0,"Roma-Fumicino","Zanzibar","2020-07-14", "2020-07-22", 850.78, 20);


insert into Tickets (ID, depCity, arrCity, dateOfDep, dateOfArr, cost,numOfTick)
values(1,"Roma-Fumicino","Istanbul","2020-07-28","2020-08-05",866.23, 22);


insert into Tickets (ID, depCity, arrCity, dateOfDep, dateOfArr, cost,numOfTick)
values(2,"Roma-Fumicino","Marrakech","2020-08-20","2020-08-30",817.56,23);


insert into Tickets (ID, depCity, arrCity, dateOfDep, dateOfArr, cost,numOfTick)
values(3,"Roma-Fumicino","London","2020-08-02","2020-08-12",641.40, 30);


insert into Tickets (ID, depCity, arrCity, dateOfDep, dateOfArr, cost,numOfTick)
values(4,"Roma-Fumicino","Ayers rock","2020-07-05","2020-07-17",787.10, 33);


insert into Tickets (ID, depCity, arrCity, dateOfDep, dateOfArr, cost,numOfTick)
values(5,"Roma-Fumicino","Amsterdam","2020-08-12","2020-08-20",893.75, 34);


insert into Tickets (ID, depCity, arrCity, dateOfDep, dateOfArr, cost,numOfTick)
values(6,"Roma-Fumicino","Ibiza","2020-07-02","2020-07-14",911.87, 40);


insert into Tickets (ID, depCity, arrCity, dateOfDep, dateOfArr, cost,numOfTick)
values(7,"Roma-Ciampino","Zanzibar","2020-07-18","2020-07-27",755.38, 40);


insert into Tickets (ID, depCity, arrCity, dateOfDep, dateOfArr, cost,numOfTick)
values(8,"Roma-Ciampino","Istanbul","2020-08-16","2020-08-30",707.24, 45);


insert into Tickets (ID, depCity, arrCity, dateOfDep, dateOfArr, cost,numOfTick)
values(9,"Roma-Ciampino","Marrakech","2020-07-28","2020-08-05",883.27, 60);


insert into Tickets (ID, depCity, arrCity, dateOfDep, dateOfArr, cost,numOfTick)
values(10,"Roma-Ciampino","London","2020-08-05","2020-08-13",840.93, 70);


insert into Tickets (ID, depCity, arrCity, dateOfDep, dateOfArr, cost,numOfTick)
values(11,"Roma-Ciampino","Ayers rock","2020-08-21","2020-08-31",904.54, 12);


insert into Tickets (ID, depCity, arrCity, dateOfDep, dateOfArr, cost,numOfTick)
values(12,"Roma-Ciampino","Amsterdam","2020-08-25","2020-08-31",858.66, 34);


insert into Tickets (ID, depCity, arrCity, dateOfDep, dateOfArr, cost,numOfTick)
values(13,"Roma-Ciampino","Ibiza","2020-07-12","2020-07-22",661.92, 2);


insert into Tickets (ID, depCity, arrCity, dateOfDep, dateOfArr, cost,numOfTick)
values(14,"Milano-Linate","Zanzibar","2020-07-09","2020-07-19",617.46, 54);


insert into Tickets (ID, depCity, arrCity, dateOfDep, dateOfArr, cost,numOfTick)
values(15,"Milano-Linate","Istanbul","2020-08-20","2020-08-31",623.59, 44);


insert into Tickets (ID, depCity, arrCity, dateOfDep, dateOfArr, cost,numOfTick)
values(16,"Milano-Linate","Marrakech","2020-07-30","2020-08-13",979.39, 78);


insert into Tickets (ID, depCity, arrCity, dateOfDep, dateOfArr, cost,numOfTick)
values(17,"Milano-Linate","London","2020-08-23","2020-08-30",752.22, 80);


insert into Tickets (ID, depCity, arrCity, dateOfDep, dateOfArr, cost,numOfTick)
values(18,"Milano-Linate","Ayers rock","2020-08-12","2020-08-20",664.44, 67);


insert into Tickets (ID, depCity, arrCity, dateOfDep, dateOfArr, cost,numOfTick)
values(19,"Milano-Linate","Amsterdam","2020-07-23","2020-07-30",665.85, 56);


insert into Tickets (ID, depCity, arrCity, dateOfDep, dateOfArr, cost,numOfTick)
values(20,"Milano-Linate","Ibiza","2020-08-20","2020-08-31",898.81, 55);


insert into Tickets (ID, depCity, arrCity, dateOfDep, dateOfArr, cost,numOfTick)
values(21,"Milano-Malpensa","Bath","2020-07-12","2020-07-22",922.50, 50);


insert into Tickets (ID, depCity, arrCity, dateOfDep, dateOfArr, cost,numOfTick)
values(22,"Milano-Malpensa","Santo Domingo","2020-08-17","2020-08-27",846.23, 30);


insert into Tickets (ID, depCity, arrCity, dateOfDep, dateOfArr, cost,numOfTick)
values(23,"Torino-Caselle","Bath","2020-07-27","2020-08-02",691.25, 24);


insert into Tickets (ID, depCity, arrCity, dateOfDep, dateOfArr, cost,numOfTick)
values(24,"Torino-Caselle","Santo Domingo","2020-07-20","2020-08-04",963.84, 25);


insert into Tickets (ID, depCity, arrCity, dateOfDep, dateOfArr, cost,numOfTick)
values(25,"Roma-Fiumicino","San Francisco","2020-07-03","2020-08-13",923.56, 26);


insert into Tickets (ID, depCity, arrCity, dateOfDep, dateOfArr, cost,numOfTick)
values(26,"Roma-Ciampino","San Francisco","2020-07-24","2020-08-02",1028.47, 36);


insert into Tickets (ID, depCity, arrCity, dateOfDep, dateOfArr, cost,numOfTick)
values(27,"Roma-Ciampino","Budapest","2020-07-15","2020-07-20",464.62, 12);


insert into Tickets (ID, depCity, arrCity, dateOfDep, dateOfArr, cost,numOfTick)
values(28,"Roma-Ciampino","Gyeongju","2020-07-15","2020-07-25",864.62, 15);


insert into Tickets (ID, depCity, arrCity, dateOfDep, dateOfArr, cost,numOfTick)
values(29,"Roma-Fiumicino","Gyeongju","2020-07-23","2020-07-30",962.62, 65);

insert into Post (ID, photo, utente, descr, beds, city, address, services, squareMetres, tipologia)
values (100001, LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/Accommodations/house.jpg'), "Jesse", "My beautiful 2 bedroom apartment, in the vibrant area of Southwark, is a stylish and comfortable place to stay with great transport links.", "6", "London", "Waterloo Road", null, "40 - 59", "apartment");

insert into Post (ID, photo, utente, descr, beds, city, address, services, squareMetres, tipologia)
values (100002, LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/Accommodations/house2.jpg'), "Jesse", "Amazing Bed and Breakfast in the middle of Amsterdam , only 5 minutes walking from Central Station and 3 min walking from Dam Square .", "4", "London", "Van Hallstrat", null, "20 - 39", "studio flat");

insert into Post (ID, photo, utente, descr, beds, city, address, services, squareMetres, tipologia)
values (100003, LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/Accommodations/house3.jpg'), "Hank", "Enjoy a convenient location, beautiful ocean views from the bedrooms, and modern amenities at my spacious Mbweni apartment.", "5", "Marrakech", "Avenue Moulay", null, "40 - 59", "cottage");

insert into Post (ID, photo, utente, descr, beds, city, address, services, squareMetres, tipologia)
values (100004, LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/Accommodations/house4.jpg'), "Hank", "Welcome to our home! If you are looking for a quiet, peaceful and relaxing stay in privacy on the country side, you will not be disappointed.", "8", "Zanzibar", "Nyerere Rd", null, "> 60", "apartment");
