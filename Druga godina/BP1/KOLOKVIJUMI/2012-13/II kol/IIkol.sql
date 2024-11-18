-- zadatak 1

select indeks, upisan,Imes,datepart(year,getdate())-datepart(year,datr) as starost
from Studenti

--zadatak 2

select pr.NAZIVP,n.Imen,
	case datepart(month,p.datump)
		when 1 then 'Januarski'
		when 4 then 'Aprilski'
		when 6 then 'Junski'
		when 9 then 'Septembarski'
		when 10 then 'Oktobarski'
	end as rok
from Prijave p join nastavnici n on p.snast=n.snast
			   join PREDMETI pr on pr.SPRED=p.Spred		
where datepart(month,p.datump)=1 or datepart(month,p.datump)=4 or datepart(month,p.datump)=6
	 or datepart(month,p.datump)=9 or datepart(month,p.datump)=10	
			   				

--zadatak 3

select spred,
	case 
		when count(*)<=5 then 'LAGAN'
		when count(*)>5 and count(*)<=15 then 'OPTIMALAN'
		when count(*)>15 then 'TEZAK'
	end as tezina
from prijave 
where ocena=5
group by spred

