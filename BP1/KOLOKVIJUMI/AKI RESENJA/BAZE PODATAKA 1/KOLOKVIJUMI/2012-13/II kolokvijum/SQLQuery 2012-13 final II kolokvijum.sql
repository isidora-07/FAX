--1

select Indeks, Upisan, Imes, datediff(year, Datr, getdate()) as starost
from Studenti

--2

select distinct pr.NAZIVP, n.Imen, 
						case
						when datepart(MONTH,p.Datump) = 1 then 'Januarski'
						when datepart(MONTH,p.Datump) = 4 then 'Aprilski'
						when datepart(MONTH,p.Datump) = 6 then 'Junski'
						when datepart(MONTH,p.Datump) = 9 then 'Septembarski'
						when datepart(MONTH,p.Datump) = 10 then 'Oktobarski'
						end as Rok
from Prijave p join PREDMETI pr
    on p.Spred = pr.SPRED
	join Nastavnici n
	on p.Snast = n.Snast
where datepart(MONTH,p.Datump) = 1 or
	  datepart(MONTH,p.Datump) = 4 or
	  datepart(MONTH,p.Datump) = 6 or
	  datepart(MONTH,p.Datump) = 9 or
	  datepart(MONTH,p.Datump) = 10	

--3

select Spred,
			case
			when Count(Ocena) <= 5 then 'LAGAN'
			when Count(Ocena) <= 15 then 'OPTIMALAN'
			else 'TEZAK'
			end
from Prijave
where Ocena < 6
group by Spred

