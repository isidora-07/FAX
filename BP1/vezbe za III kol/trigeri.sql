--TRIGERI

alter trigger provera_godine_upisa on studenti
instead of insert
as
begin
	if exists (select upisan from inserted where Upisan > datepart(yyyy, getdate()))
	begin
		
		declare @indeks int
		declare @upisan int
		declare @imes varchar(100)

		--deklarisali kursor za neki upit
		declare kursorKrozStudente cursor for
		select indeks, upisan, Imes
		from Studenti 
		
		--otvorimo kursor
		open kursorKrozStudente

		--ukoliko postoji prvi red ovde ucitavamo pa posle ide u while za sve ostale redove ukoliko postoje
		fetch next from kursorKrozStudente into @indeks, @upisan, @imes

		--da li smo dosli do kraja?
		--kada je tabela prazna ovaj while se ne izvrsava
		while @@FETCH_STATUS = 0
		begin 
			print concat(@upisan, ' ', @indeks, ' ',  @imes)
			fetch next from kursorKrozStudente into @indeks, @upisan, @imes
		end

		close kursorKrozStudente
		deallocate kursorKrozStudente

	end
	
	--mi upisujemo vrednosti ukoliko nije greska
		insert into Studenti 
		select * from inserted 
		where Upisan <= datepart(yyyy, getdate())
end

insert into Studenti values (8, 3019, 'Pera1', 'Kragujevac', '1994-01-01', 1)

insert into Studenti values (100, 2018, 'Pera1', 'Kragujevac', '1994-01-01', 1),
							(100, 2019, 'Pera2', 'Kragujevac', '1994-01-01', 1),
							(100, 2020, 'Pera2', 'Kragujevac', '1994-01-01', 1),
							(100, 2021, 'Pera2', 'Kragujevac', '1994-01-01', 1)

select *
from Studenti
where indeks = 100

delete from Studenti where upisan>=2019

--kreirati tabelu Arhiva studenata
--Sve studente koji se brisu sacuvati u "Arhiva studenata"

create trigger arhiva_brisanja on Studenti
after delete
as
begin
	insert into Arhiva_studenata 
	select * from deleted
end

select * from Arhiva_studenata
select * from Studenti where upisan = 2019
delete from Studenti where upisan = 2019


create table Arhiva_studenata
(
	indeks int,
	upisan int,
	imes varchar(100),
	mesto varchar(100),
	datumr datetime,
	ssmer int
)
