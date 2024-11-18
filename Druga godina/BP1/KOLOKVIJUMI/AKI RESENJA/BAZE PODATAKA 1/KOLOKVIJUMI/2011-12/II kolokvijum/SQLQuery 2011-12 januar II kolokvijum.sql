--1

select (semestar+1)/2 as godina, 
								 case
								 when Semestar=1 then 'zimski'
								 when Semestar=2 then 'letnji'
								 end as semestar
								 , Ssmer, Spred
from Planst
order by godina,semestar desc,Ssmer

--2

select Ssmer, count(Spred) as broj_predmeta, (((count(Spred)*2)-1)/6)+1 as potrebno_profesora					  
from Planst
where Semestar=1
group by Ssmer


--3

--a

select p2.NAZIVP, max(p1.Ocena) as najbolja_ocena
from Prijave p1 join PREDMETI p2
    on p1.Spred=p2.SPRED
where p1.Spred not in (
					select Spred
					from Prijave
					where Spred not in (
										select Spred
										from Prijave
										where Ocena > 5
										group by Spred
										)
					)
group by p1.Spred, p2.NAZIVP

--b

--don't understand :D


