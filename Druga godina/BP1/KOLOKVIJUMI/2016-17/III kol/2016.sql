--1

create table NevalidnePonude
(
	id int not null primary key,
	idProizvoda int not null,
	idClanaPonudjaca int not null,
	novcaniIznos float not null,
	datumponude datetime not null
)


create trigger triger on ponude
instead of insert 
as
begin
	declare @id int ,
			@idProizvoda int,
			@idClanaPonudjaca int,
			@novcaniIznos float,
			@datumponude datetime

	declare kursor cursor for 
	select * from inserted
	open kursor
	fetch next from kursor into @id, @idProizvoda,@idClanaPonudjaca,@novcaniIznos,@datumponude
	while @@FETCH_STATUS=0
	begin

	declare @najveci_iznos float

	select @najveci_iznos=max(novcaniIznos)
	from ponude
	where idProizvoda=@idProizvoda
	group by idProizvoda

	if exists (
				select *
				from proizvodi pr join ponude po on pr.id=po.idProizvoda 
				where @datumPonude>=pr.pocetakLicitacije and
					@datumPonude<=pr.krajLicitacije and
					@novcaniIznos>@najveci_iznos
			  )	
	begin
		insert into ponude values (@id,
									@idProizvoda,
									@idClanaPonudjaca,
									@novcaniIznos,
									@datumponude
								    )
	end
	else
	begin
		insert into NevalidnePonude values (@id,
											@idProizvoda,
											@idClanaPonudjaca,
											@novcaniIznos,
											@datumponude
											)
	end
	fetch next from kursor into @id, @idProizvoda,@idClanaPonudjaca,@novcaniIznos,@datumponude
	end

	close kursor
	deallocate kursor
end

--2

create function ProveraPotraznje(@idTipaProizvoda int,@Datum1 datetime, @Datum2 datetime)
returns int
as
begin

	declare @prosek1 float,
			@prosek2 float,
			@tmp_datum datetime	
	
	set @tmp_datum=dateadd(month,1,@Datum1)
	
	while(@tmp_datum<@Datum2)
	begin
		select @prosek1=avg(p1.novcaniIznos)
		from ponude p1 join proizvodi p2 on 
			p1.idProizvoda=p2.id
		where p2.idTipa=@idTipaProizvoda and @tmp_datum=p1.datumponude

		select @prosek2=avg(p1.novcaniIznos)
		from ponude p1 join proizvodi p2 on
			p1.idProizvoda=p2.id
		where p2.idTipa=@idTipaProizvoda and DATEADD(month,-1,@tmp_datum)=p1.datumponude

		if(@prosek1<@prosek2)
		begin
			return -1
		end
		set @tmp_datum=dateadd(month,1,@tmp_datum)
	end

	return 1
end

--3

create function PopularniTipoviProizvoda()
returns table
as
return (
		select idTipa
		from proizvodi
		where dbo.ProveraPotraznje(idTipa,dateadd(month,-6,getdate()),getdate())=1
		)
	
select * from dbo.PopularniTipoviProizvoda()

--4

create table AktivnaStatistika
(
	idKorisnika int,
	brojAktivnihKorisnika int,
	brojPopularnihProizvoda int,
	foreign key (idKorisnika) references clanovi(id)
)

create procedure procedura
as 
begin
	declare @idKorisnika int,
			@brojAktivnihKorisnika int,
			@brojPopularnihProizvoda int
	
	declare kursor1 cursor for 
	select id from clasovi

	open kursor1
	fetch next from kursor1 into @idKorisnika

	while @@FETCH_STATUS=0
	begin
		select @brojAktivnihKorisnika=count(*)
		from proizvodi
		where idClana=@idKorisnika and krajLicitacije is null	
		group by idClana

		select @brojPopularnihProizvoda=count(*)
		from Proizvodi p1
		where idClana=@idKorisnika and exists(
												select *
												from dbo.PopularniTipoviProizvoda() 
												where p1.idTipa=idTipa
											  )
		insert into AktivnaStatistika values (@idKorisnika,@brojPopularnihProizvoda,@brojAktivnihKorisnika)
				
	end

	fetch next from kursor1 into @idKorisnika
	close kursor1
end

