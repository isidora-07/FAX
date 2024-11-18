--1

select distinct n.Imen, Indeks, Upisan
from Prijave p join Nastavnici N
    on p.Snast=n.Snast

--2

select Ssmer, (semestar+1)/2 as godina, count(Spred)
from Planst
group by Ssmer, (semestar+1)/2


--3
--ne postoji u bazi podataka student 1/2001

select *
from Angazovanje a2
where Snast not in (
					select Snast
					from Angazovanje a
					where Ssmer in (
									select Ssmer
									from Studenti s
									where s.Indeks=1 and s.Upisan=2000
									)	   
				   )

--4

 
alter view predmeti_po_g as
select distinct Spred, (semestar+1)/2 as godina
from Planst

select pr.Indeks, pr.Upisan, ppg.godina, count(Ocena) as polozeno
from Prijave pr join predmeti_po_g ppg
    on pr.Spred = ppg.Spred	
where pr.Ocena > 5
group by pr.Indeks, pr.Upisan, ppg.godina



