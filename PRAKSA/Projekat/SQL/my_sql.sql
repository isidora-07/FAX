create table genre(
	idGenre int identity(1,1) primary key,
	nameGenre varchar(100)
);

create table bend (
	idBend int identity(1,1) primary key,
    nameBend varchar(100) NOT NULL,
	idGenre int not null foreign key references genre(idGenre) on delete cascade
);

create table artist (
	idArtist int identity(1,1) primary key,
    nameArtist varchar(100) NOT NULL,
	dateArtist DateTime,
	idBend int not null foreign key references bend(idBend) on delete cascade
);

create table song (
	idSong int identity(1,1) primary key,
    nameSong varchar(100) NOT NULL,
    idBend int not null foreign key references bend(idBend) on delete cascade
);

create table [user](
	id int identity(1,1) primary key,
	username varchar(50) NOT NULL,
	pass varchar(50) NOT NULL,
	user_role varchar(50) NOT NULL
);

create table [login](
	id int identity(1,1) primary key,
	pass varchar(100) not null,
	username varchar(100) not null
);

insert into genre values('Rok'), ('Alternativni rok'), ('Fank'), ('Tehno'), ('Disko');

insert into bend values('EKV', 1017), ('Bijelo dugme', 1018), ('Azra', 1017), ('Debela nensi', 1017);

insert into artist values('Margita Stefanovi', '2020-07-27 20:23:32', 1021),
						('Bojan Peca', '2020-07-27 20:23:32', 1021),
						('Srdjan Todorovic', '2020-07-27 20:23:32', 1021),
						('Ivica Vdovic', '2020-07-27 20:23:32', 1021),
						('Zeljko Bebek', '2020-07-27 20:23:32', 1022),
						('Tifa', '2020-07-27 20:23:32', 1022),
						('Branimir Stulic', '2020-07-27 20:23:32', 1023),
						('Ime prezime', '2020-07-27 20:23:32', 1024);

insert into song values('Par godina za nas', 1021), ('Oci boje meda', 1021), ('Srce', 1021), ('Iznad grada', 1021), ('Budi sam na ulici', 1021);

insert into [user] values('isidoriska','isi123', 'user');
insert into [user] values('nikola','nikola123', 'admin');

insert into [login] values('isi123', 'isidoriska');
insert into [login] values('nikola123', 'nikola');


select * from genre
select * from bend
select * from song
select * from artist

select * from [user]
select * from [login]



