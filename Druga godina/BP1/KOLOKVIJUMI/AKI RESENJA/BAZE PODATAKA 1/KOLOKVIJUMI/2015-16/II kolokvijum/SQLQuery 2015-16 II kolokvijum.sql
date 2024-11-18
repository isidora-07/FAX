--1

select max(p.datumObjave) as poslednja_objava
from post p join tipPosta tp
    on p.idTipa = tp.idTipa and tip='slika'

--2

select k.ime, count(idKorisnika2)
from Prijatelji p join Korisnik k
    on p.idKorisnika1=k.id
group by p.idKorisnika1, k.ime


--3


select ime
from Korisnik
where id in (
			select distinct idKorisnika1
			from Prijatelji
			where datepart(month,datum) = datepart(month,getdate())
			)


--4

create view slike as
select p.id, p.idTipa, p.idVlasnika, p.datumObjave
from tipPosta tp join post p
    on tp.idTipa = p.idTipa and tp.tip='slika'

create view likepic as
select s.idVlasnika, s.id, count(*) as lajk_po_slici
from lajk l join slike s
    on l.idPosta = s.id
group by s.idVlasnika, s.id

select idVlasnika, avg(lajk_po_slici*1.0) as prosek
from likepic
group by idVlasnika
having avg(lajk_po_slici*1.0) > 1


--nacin sa casa, pogresan xD

create view brojSlika as
select idVlasnika, count(id) as brojSlika
from slike
group by idVlasnika

create view brojLajkova as
select s.idVlasnika, count(l.idKorisnika) as brojLajkova
from slike s join lajk l
    on s.id=l.idPosta
group by s.idVlasnika


select bs.idVlasnika, bl.brojLajkova*1.0/bs.brojSlika as prosek
from  brojSlika bs join brojLajkova bl
    on bs.idVlasnika=bl.idVlasnika
where bl.brojLajkova*1.0/bs.brojSlika >= 1


--5

create view potencijalni as
select p1.idKorisnika1 as p1k1, p1.idKorisnika2 as p1k2, p2.idKorisnika1 as p2k1, p2.idKorisnika2 as p2k2
from Prijatelji p1 join Prijatelji p2
   on p1.idKorisnika2=p2.idKorisnika1 and 
      p1.idKorisnika1!=p2.idKorisnika2

create view novi as
select p.p1k1, p.p2k2
from potencijalni p left join Prijatelji pr
   on p.p1k1=pr.idKorisnika1 and
      p.p2k2=pr.idKorisnika2
where pr.idKorisnika2 is null

select p1k1, count(p2k2) as upoznao_novih_osoba
from novi
group by p1k1



--6

create view brojZajednickih as
select p1.idKorisnika1, p2.idKorisnika1 as idKorisnika2, count(*) as broj_zajednickih
from Prijatelji p1 join Prijatelji p2
   on p1.idKorisnika2=p2.idKorisnika2 and
      p1.idKorisnika1!=p2.idKorisnika1
group by p1.idKorisnika1, p2.idKorisnika1


create view predlozi as
select *
from brojZajednickih b
where not exists (
				 select *
				 from Prijatelji p
				 where b.idKorisnika1=p.idKorisnika1 and
				       b.idKorisnika2=p.idKorisnika2
				 )

select p1.idKorisnika1, p1.idKorisnika2, p1.broj_zajednickih
from predlozi p1 left join predlozi p2
   on p1.idKorisnika1=p2.idKorisnika1 and
	  p1.broj_zajednickih < p2.broj_zajednickih
where p2.broj_zajednickih is null


--7

create view poslednjih_30 as 
select p.idVlasnika, p.id, count(*) as broj_lajkova_po_postu 
from post p join lajk l
    on p.id=l.idPosta
--where DATEDIFF(DAY,l.datum,getdate()) < 30
where DATEDIFF(DAY,l.datum,'2016-9-20') < 30
group by p.idVlasnika, p.id


create view pre_30 as 
select p.idVlasnika, p.id, count(*) as broj_lajkova_po_postu 
from post p join lajk l
    on p.id=l.idPosta
--where DATEDIFF(DAY,l.datum,getdate()) < 30
where DATEDIFF(DAY,l.datum,'2016-9-20') >= 30
group by p.idVlasnika, p.id

create view prosek_do_30 as
select idVlasnika, avg(broj_lajkova_po_postu*1.0) as prosek
from poslednjih_30
group by idVlasnika

create view prosek_od_30 as
select idVlasnika, avg(broj_lajkova_po_postu*1.0) as prosek
from pre_30
group by idVlasnika


select 
		case
		when pre30.idVlasnika is null then pos30.idVlasnika
		else pre30.idVlasnika
		end as vlasnik, 
						case
						when pos30.prosek > pre30.prosek then 'raste'
						when pos30.prosek = pre30.prosek then 'ista'
						when pos30.prosek < pre30.prosek then 'opada'
						when pos30.prosek is NULL then 'opada'
						when pre30.prosek is NULL then 'raste'
						when pre30.prosek is NULL and pos30.prosek is null then 'ista'
						end as POPULARNOST
from prosek_od_30 pre30 full outer join prosek_do_30 pos30
    on pre30.idVlasnika=pos30.idVlasnika
