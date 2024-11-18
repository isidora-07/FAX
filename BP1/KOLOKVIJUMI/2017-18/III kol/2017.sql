--1

create table arhiva_prolaznih_vremena
(
	id int identity(1,1),
	idVozaca int,
	idEtape int,
	redniBrojStaze int,
	vremeStarta datetime,
	vremeProlaskaKrozCilj datetime,
	primary key (id),
	foreign key (idVozaca) references vozaci(id_vozaca),
	foreign key (idEtape,redniBrojStaze) references etape(id_etape, redni_broj_staze)
)

select * from arhiva_prolaznih_vremena

create trigger upis_u_prolazna_vremena on prolazna_vremena
instead of insert
as
begin
	declare @id int,
			@idVozaca int,
			@idEtape int,
			@redniBrojStaze int,
			@vremeStarta datetime,
			@vremeProlaskaKrozCilj datetime
	
	declare kursor cursor for
	select id_vozaca,id_etape,redni_broj_staze,vreme_starta,vreme_prolaska_kroz_cilj
	from inserted

	open kursor

	fetch next from kursor into @idVozaca,@idEtape,@redniBrojStaze,@vremeStarta,@vremeProlaskaKrozCilj

	while @@FETCH_STATUS=0
	begin
	if exists(
				select *
				from prolazna_vremena
				where id_vozaca=@idVozaca and id_etape=@idEtape
						and redni_broj_staze=@redniBrojStaze
			 )
	begin
		insert into arhiva_prolaznih_vremena
		select *
		from prolazna_vremena
		where id_vozaca=@idVozaca and id_etape=@idEtape
						and redni_broj_staze=@redniBrojStaze
		update prolazna_vremena set vreme_starta=@vremeStarta,vreme_prolaska_kroz_cilj=@vremeProlaskaKrozCilj
		where id_vozaca=@idVozaca and id_etape=@idEtape
						and redni_broj_staze=@redniBrojStaze
	end
	else
	begin
		insert into prolazna_vremena values (@idVozaca,@idEtape,@redniBrojStaze,@vremeStarta,@vremeProlaskaKrozCilj)
	end

	fetch next from inserted into  @idVozaca,@idEtape,@redniBrojStaze,@vremeStarta,@vremeProlaskaKrozCilj

	end
	close kursor
	DEALLOCATE kursor
end

--2

create function Plasman(@idVozaca int, @idEtape int, @redniBrojStaze int, @godina int)
returns int
as
begin
	declare @idTipa int
	select @idTipa=t.id_tipa
	from tip_vozila t join vozila v
		on t.id_tipa=v.id_tipa join vozaci v1
		on v.id_vozila=v1.id_vozila
	where v1.id_vozaca=@idVozaca

	declare @ostvarenoVreme int
	select @ostvarenoVreme=datediff(MILLISECOND,vreme_starta,vreme_prolaska_kroz_cilj)
	from prolazna_vremena
	where id_vozaca=@idVozaca and id_etape=@idEtape and redni_broj_staze=@redniBrojStaze

	declare @plasman int
	select @plasman=count(*)+1
	from (
			select pv.id_vozaca, datediff(MILLISECOND, pv.vreme_starta, pv.vreme_prolaska_kroz_cilj) ostvareno_vreme
			from prolazna_vremena pv join vozaci v
					on pv.id_vozaca=v.id_vozila join vozila v1 
					on v.id_vozila=v1.id_vozila
			where datepart(year, pv.vreme_prolaska_kroz_cilj) = @godina 
					and v1.id_tipa = @idTipa 
					and pv.id_etape = @idEtape and redni_broj_staze = @redniBrojStaze
		 ) as TMP
	where TMP.id_vozaca != @idVozaca and @ostvarenoVreme > TMP.ostvareno_vreme

	return @plasman
end

--3

create function DaLiJeOborenRekord(@idVozaca int, @idEtape int, @redniBrojStaze int)
returns int
as
begin
	
	declare @ostvareno_vreme int 
	declare @kada_je_ostvareno_vreme datetime 

	select @ostvareno_vreme = datediff(MILLISECOND, vreme_starta, vreme_prolaska_kroz_cilj), @kada_je_ostvareno_vreme = vreme_prolaska_kroz_cilj
	from prolazna_vremena
	where id_etape = @id_etape and redni_broj_staze = @redni_broj_staze and id_vozaca = @id_vozaca
	
	declare @id_tipa int
	
	select @id_tipa = v2.id_tipa 
	from vozaci v1 join vozila v2 on v1.id_vozila = v2.id_vozila
	where v1.id_vozaca = @id_vozaca 

	if @ostvareno_vreme is null
	begin
		return -1
	end
	


end
