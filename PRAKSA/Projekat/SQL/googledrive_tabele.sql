create table [type](
	id int primary key identity(1,1),
	[name] varchar(100) not null
);

create table [user](
	id int primary key identity(1,1),
	firstname varchar(100),
	lastname varchar(100),
	email varchar(100) unique not null
);

create table [document](
	id int primary key identity(1,1),
	[name] varchar(100),
	drive_path varchar(100),
	creation_date datetime not null,
	drive_document_id varchar(100) unique,
	[type_id] int not null foreign key references [type](id) on delete cascade
);

create table share(
	id int primary key identity(1,1),
	drive_share_id varchar(100),
	[user_id] int not null foreign key references [user](id) on delete cascade,
	document_id int not null foreign key references [document](id) on delete cascade
);

insert into [type] values('Licna karta');
insert into [type] values('Ugovor');
insert into [type] values('Vozacka dozvola');
insert into [type] values('Polisa osiguranje');

insert into [user] values('Isidora', 'Arandjelovic', 'isixaran7@gmail.com');
insert into [user] values('Nikola', 'Vasic', 'nikola.vasic17.12.98@gmail.com');

select * from [user]
select * from [type]

select * from document
select * from share

alter table document alter column drive_path nvarchar(100)