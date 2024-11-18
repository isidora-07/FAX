--1

create view suma as
select idVlasnika, count(*) brojVozila
from vozila v
group by idVlasnika

select o.idOsobe idVlasnika, o.ime, s.brojVozila
from suma s join osobe o on
	s.idVlasnika = o.idOsobe
	
--2

create view ukupanBrGodina as
select godinaProizvodnje, count(*) ukupno
from vozila 
group by godinaProizvodnje

select count(*) Broj_jedinstvenih
from ukupanBrGodina
where ukupno = 1
group by ukupno

--3

create view ponudjeno as
select *,
		(
			select count(*)
			from ponude p2
			where p1.idOglasa = p2.idOglasa and p1.ponudjenaCena = p2.ponudjenaCena
		) broj
from ponude p1

select idOglasa, max(ponudjenaCena) 'Najveca jedinstvena'
from ponudjeno 
where broj = 1
group by idOglasa

--4

alter view pogled as
select p.idPonudjaca, p.datumPonude, p.ponudjenaCena, o.*
from ponude p join oglasi o on
	p.idOglasa = o.idOglasa

alter view najvecePonudjeneCene as
select p1.*
from pogled p1 left join pogled p2 on
	p1.ponudjenaCena < p2.ponudjenaCena and p1.idVozila = p2.idVozila 
where p2.idVozila is null

alter view kupili as
select np1.*
from najvecePonudjeneCene np1 left join najvecePonudjeneCene np2 on
	np1.idVozila = np2.idVozila and np1.datumPonude > np2.datumPonude
where np2.idOglasa is null 

create view kubikazaPonudjac as
select k.idPonudjaca, v.kubikaza
from kupili k join vozila v on
	k.idVozila = v.idVozila

create view prosecnaKubikaza as
select idPonudjaca, avg(kubikaza) prosek
from kubikazaPonudjac
group by idPonudjaca 

select pk1.idPonudjaca
from prosecnaKubikaza pk1 left join prosecnaKubikaza pk2 on
	pk1.prosek < pk2.prosek
where pk2.prosek is null

--5

alter view poslednjiKupljeni as
select k1.*
from kupili k1 left join kupili k2 on
	k1.idPonudjaca = k2.idPonudjaca and k1.datumPonude < k2.datumPonude
where k2.idOglasa is null

select pk.idPonudjaca
from poslednjiKupljeni pk left join kupili k on
	k.idPonudjaca = pk.idPonudjaca and pk.ponudjenaCena < k.ponudjenaCena
where k.idOglasa is null
