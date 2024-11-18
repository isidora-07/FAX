--zadatak 1

create view semestar as
select s.Nazivs,s.Ssmer,p.spred,(semestar+1)/2 as godina,
	case
		when semestar=1 then 'zimski'
		when semestar=2 then 'letnji'
		when semestar=3 then 'zimski'
		when semestar=4 then 'letnji'
		when semestar=5 then 'zimski'
		when semestar=6 then 'letnji'
		when semestar=7 then 'zimski'
		when semestar=8 then 'letnji'
	end as 'zimski/letnji semestar'
from planst p join Smer s 
		on p.Ssmer=s.Ssmer
order by (semestar+1)/2 desc,p.Ssmer
--desc 1 2 3 4 ispisuje 4 3 2 1

--zadatak 3

--nije niko polagao ove predmete
create view niko_nije_polozio as
select pr.SPRED,pr.NAZIVP
from PREDMETI pr left join Prijave p
		on pr.SPRED=p.Spred 	
where p.ocena is null

--NIKO NIJE POLOZIO OVE PREDMETE
create view niko1 as
select n.SPRED,
	case
		when n.Spred=n1.SPRED then 0
	end as niko
from niko_nije_polozio n join niko_nije_polozio n1
	on n.SPRED=n1.SPRED

--nepolozeni predmeti
--TABELA SE ZOVE NEPOLOZENI_PREDMETI
create view nepolozeni_predmeti as
select distinct pr.SPRED,
	case
		when p.Spred=pr.SPRED then 5 
	end as nepolozeno
from Prijave p join PREDMETI pr
		ON p.Spred=pr.SPRED
where ocena =5

--najbolja ocena iz svakog predmeta
alter view najveca1 as
select distinct p.Spred,pr.NAZIVP,p.Ocena
from prijave p join prijave p1
		on  p.Spred=p1.Spred and p.Ocena<p1.Ocena
		join PREDMETI pr 
		on pr.SPRED=p.Spred and pr.SPRED=p1.Spred
where p.Ocena>5 and p1.Ocena>5

select Spred,NAZIVP,max(ocena)
from najveca1 
group by Spred,NAZIVP
order by spred asc 

--a)
--NAJVECA OCENA ZA SVAKI PREDMET
--TABELA SE ZOVE NAJVECA7
create view najveca7 as
select spred,NAZIVP,max(ocena) as najbolja_ocena
from najveca1 
group by Spred,NAZIVP
order by spred asc 



		


 


				


