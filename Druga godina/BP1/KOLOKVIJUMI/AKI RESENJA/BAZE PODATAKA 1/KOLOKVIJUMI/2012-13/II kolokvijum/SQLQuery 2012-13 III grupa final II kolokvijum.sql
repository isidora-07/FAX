--1

select distinct datepart(year, Datump) as godina, DATENAME(WEEKDAY, datump) as dan
from Prijave

--2

--a

select Spred, Upisan, min(Datump) as prvi_put_polozen
from Prijave
where Ocena > 5
group by Spred, Upisan


--3

--a

select distinct p1.Upisan, p1.Spred
from Prijave p1
where not exists (
					select *
					from Prijave p2
					where p2.Ocena = 5 and p1.Spred=p2.Spred
									   and p1.Upisan=p2.Upisan
					group by p2.Spred, p2.Upisan
				  )
order by Upisan



--b

select distinct p1.Upisan, p.NAZIVP
from Prijave p1 join PREDMETI p
    on p1.Spred = p.SPRED
where not exists (
					select *
					from Prijave p2
					where p2.Ocena = 5 and p1.Spred=p2.Spred
									   and p1.Upisan=p2.Upisan
					group by p2.Spred, p2.Upisan
				  )
order by Upisan



