--1

select b.naziv,m.ime,m.prezime
from sastav_benda s join bendovi b
	on s.idBenda=b.idBenda join muzicari m
	on m.idMuzicara=s.idMuzicara
where datum2 is null

--2

select p.ime,sum(k.cena) novac
from karte k join posetioci p on k.idPosetioca=p.idPosetioca
group by p.ime

--3

--I nacin
create view tabela as
select distinct k1.idFestivala,k1.cena
from karte k1
where exists (
				select *
				from karte k2
				where k1.idFestivala=k2.idFestivala
			  )

create view rang1 as
select *,
		 (
			select count(*)
			from tabela t2
			where t1.idFestivala=t2.idFestivala and t1.cena<t2.cena
		 )+1 as rang
from tabela t1
order by t1.idFestivala,t1.cena desc

select idFestivala,cena drugaCena
from rang1
where rang=2
order by idFestivala,cena desc

--II nacin

select idFestivala,max(cena) as 'drugaCena'
from karte k1
where cena not in(
					select max(cena)
					from karte k2
					where k1.idFestivala=k2.idFestivala
				 )
group by idFestivala

--4

alter view godine_sviranja as
select *,
	case
		when datum2 is null then datediff(year,datum1,getdate())
		else datediff(year,datum1,datum2)
	end as 'godine'
from sastav_benda


select idMuzicara,idBenda,
	case 
		when godine<5 or godine is null then 'Guster'
		when (godine>=5 and godine<10) then 'Dzomba'
		else 'Stara kajla'
	end as 'Sastus'
from godine_sviranja

--5

alter view ispravni_festivali as
select idFestivala,godina
from lineup_festivala
where dan>=2
group by idFestivala,godina

create view ispravno as
select i.idFestivala,i.godina,count(*) brojPosetioca,sum(k.cena) zarada
from ispravni_festivali i join karte k 
	on i.idFestivala=k.idFestivala and i.godina=k.godina
group by i.idFestivala,i.godina

select i1.idFestivala,i1.godina,
		(
			select count(*)
			from ispravno i2
			where i1.godina=i2.godina and 
			(i1.zarada<i2.zarada or (i1.zarada=i2.zarada and i1.brojPosetioca<i2.brojPosetioca))
		)+1 as 'rang'
from ispravno i1

--6

create view br_clanova as
select *,
		(
			select count(*)
			from sastav_benda s2
			where (((s1.datum1 between s2.datum1 and s2.datum2) or (s1.datum1>=s2.datum1 and s2.datum2 is null)) and s1.idBenda=s2.idBenda)
		) as 'brClanova'
from sastav_benda s1

create view maksimum1 as
select idBenda,max(brClanova) maksimum
from br_clanova
group by idBenda

create view vrh as
select max(maksimum) vrhVrhova
from maksimum1

select m1.idBenda
from maksimum1 m1 join vrh v on m1.maksimum=v.vrhVrhova



