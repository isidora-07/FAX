--1

select Imes, CAST(DATEPART(DAY,DATR) as varchar(5)) + CAST(DATEPART(MONTH,DATR) as varchar(5)) + CAST(DATEPART(YEAR,DATR) as varchar(5)) as maticni, Datr
from Studenti
order by Upisan, Datr desc

--2

select distinct p1.Spred
from Planst p1 join Planst p2
   on p1.Spred=p2.Spred and p1.Ssmer!=p2.Ssmer
   and p1.Semestar!=p2.Semestar

--3


select distinct a.Snast, a.Spred,
						case
						when a.Ssmer is not null then a.Ssmer
						when a.Ssmer is null then p.Ssmer
						end 
from Angazovanje a join Planst p
   on a.Spred=p.Spred
order by a.Snast, a.Spred

