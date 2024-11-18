--zadatak 1

select max(datumObjave) as poslednja_objava
from post p join tipPosta t on t.tip='slika' and p.idTipa=t.idTipa

--zadatak 2


select k.ime,count(idKorisnika2) as broj_prijatelja
from Prijatelji p join Korisnik k on
	p.idKorisnika1=k.id
group by p.idKorisnika1,k.ime

--zadatak 3

select distinct ime 
from Korisnik
where id in (
			select idKorisnika1
			from Prijatelji
			where DATEPART(month,datum)=DATEPART(month,GETDATE())
			)


