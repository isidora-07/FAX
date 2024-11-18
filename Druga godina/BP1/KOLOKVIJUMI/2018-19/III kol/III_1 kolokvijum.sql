--1

create table vecProdateKarte
(
	id int identity(1,1) primary key,
	idFestivala int not null,
	godina int not null,
	dan int not null,
	idPosetioca int not null,
	cena float,
	foreign key (idFestivala) references festivali(idFestivala),
	foreign key (idPosetioca) references posetioci(idPosetioca)
)

select * from vecProdateKarte

create trigger triger on karte
instead of insert
as
begin
	insert into vecProdateKarte
	select * from inserted i
	where exists
				(
					select *
					from karte k
					where i.cena=k.cena and
							i.idFestivala=k.idFestivala and
							i.godina=k.godina and
							i.idPosetioca=k.idPosetioca and
							i.dan=k.dan
				)
	insert into karte
	select * from inserted i
	where not exists
					(
						select *
						from karte k
						where i.cena=k.cena and
								i.idFestivala=k.idFestivala and
								i.godina=k.godina and
								i.idPosetioca=k.idPosetioca and
								i.dan=k.dan
					)
end

--2

create function dajSastavBenda(@idBenda int, @godina int)
returns table
as return 
(
	select s.idMuzicara,m.ime,m.prezime
	from sastav_benda s join muzicari m
			on s.idMuzicara=m.idMuzicara
	where datepart(year,datum1)<=@godina and (@godina<=datepart(year,datum2) or datum2 is null) 
			and @idBenda=s.idBenda
)

--3

create function dajZaradu(@idFestivala int, @godina int)
returns float
begin
	declare @sum_ukupno float
	select @sum_ukupno=sum(cena*1.0)
	from karte
	where @idFestivala=idFestivala and @godina=godina

	declare @sum1 int
	select @sum1=count(*)
	from lineup_festivala
	where @idFestivala=idFestivala and @godina=godina

	declare @ukupno float

	set @ukupno=@sum_ukupno/@sum1

	return @ukupno
end

--4

create function dajZaraduBendovaPoFestivalima()
returns table
return
	(
		select idFestivala,idBenda,godina,dbo.dajZaradu(idFestivala,godina) zaradaBenda
		from lineup_festivala
		
	)

select * from dajZaraduBendovaPoFestivalima()

--5

create procedure prikaziZaraduPoClanuBenda(@idFestivala int, @godina int, @idBenda int, @zarada float)
as
begin
	declare @clanoviBenda int

	select @clanoviBenda=count(*)
	from dbo.dajSastavBenda(@idBenda,@godina)

	declare @zaradaPoClanu float

	set @zaradaPoClanu=@zarada/@clanoviBenda

	if @clanoviBenda != 0 and @zaradaPoClanu > 1000
	begin 
		select m.ime,m.prezime,s.idBenda,@zaradaPoClanu zarada
		from muzicari m join sastav_benda s on
			m.idMuzicara=s.idMuzicara join lineup_festivala l on
			s.idBenda=l.idBenda
		where @idBenda=s.idBenda and @godina=l.godina and @idFestivala=l.idFestivala

	end
end


