--1

create table VecProdateKarte
(
	id int identity(1,1) primary key,
	idFestivala int not null,
	godina int not null,
	dan int not null,
	idPosetioca int not null,
	cena float not null,
	foreign key (idFestivala) references festivali(idFestivala),
	foreign key (idPosetioca) references posetioci(idPosetioca)
)

create trigger triger_karte on karte
instead of insert
as
begin
	insert into VecProdateKarte
	select * 
	from inserted i
	where exists (
					select * 
					from karte 
					where	i.godina = godina 
						and i.dan = dan 
						and i.idPosetioca = idPosetioca
						and i.idFestivala = idFestivala
				 )

	insert into karte
	select * 
	from inserted i
	where NOT exists (
						select * 
						from karte 
						where	i.godina = godina 
							and i.dan = dan 
							and i.idPosetioca = idPosetioca
							and i.idFestivala = idFestivala
					 )
end


--2

create function dajSastavBenda(@idBenda int, @godina int)
returns table
as
 return (
			select m.idMuzicara,m.ime,m.prezime
		    from sastav_benda s join muzicari m on
				 s.idBenda=@idBenda and
				 s.idMuzicara=m.idMuzicara and
				 datepart(year,s.datum1)<=@godina and
				 (s.datum2 is null or @godina<=datepart(year,s.datum2))

	   )

select * from dbo.dajSastavBenda(1,1998)
--3

alter function dajZaradu(@idFestivala int, @godina int)
returns float
as 
begin
	declare @zarada float

	select @zarada=sum(cena * 1.0)
	from karte k
	where k.idFestivala=@idfestivala and k.godina=@godina



	declare @br int

	select @br=count(*)
	from lineup_festivala l
	where l.idFestivala=@idfestivala and l.godina=@godina

	declare @ukupno float

	set @ukupno=@zarada/@br

	return @ukupno
end


--4

alter function dajZaraduBendovaPoFestivalima()
returns table
as
return (
			select idFestivala,godina,idBenda,dbo.dajZaradu(idFestivala,godina) as 'zaradaBenda'
			from lineup_festivala
		)


select dbo.dajZaradu(1,2004)
select * from dajZaraduBendovaPoFestivalima()


--5

create procedure prikaziZaraduPoClanuBenda(@idFestivala int, @godina int, @idBenda int, @zaradaBenda int)
as 
begin
	declare @idMuzicara int
	declare @ime varchar(20)
	declare @prezime varchar(20)
	declare @brojClanova int
	declare @nazivBenda varchar(20)
	declare @nazivFestivala varchar(20)

	-- Uzmi broj clanova benda
	set @brojClanova = (
						select count(*) 
						from dbo.dajSastavBenda(@idBenda, @godina)
						)

	-- Uzmi naziv benda
	set @nazivBenda = (
						select naziv 
						from bendovi
						where idBenda = @idBenda
						)

	
end
























