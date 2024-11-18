--1

select Imes, Indeks, 
					case
					when Indeks%2 = 0 then 'GRUPA 1'
					when Indeks%2 = 1 then 'GRUPA 2'
					end
from Studenti

--2

select p1.Indeks, p1.Upisan, p1.Spred
from Prijave p1 join Prijave p2
    on p1.Indeks=p2.Indeks and p1.Upisan=p2.Upisan
	and p1.Snast!=p2.Snast and p1.Spred=p2.Spred

--3

select distinct s.Nazivs,pre.NAZIVP
from Planst pl join Prijave pr
    on pl.Spred=pr.Spred
	join Smer s
	on pl.Ssmer=s.Ssmer
	join PREDMETI pre
	on pl.Spred=pre.SPRED
order by s.Nazivs,pre.NAZIVP


--4

select pl.Ssmer, s.Nazivs, count(Ocena) as neuspeli
from Planst pl join Prijave pr
    on pl.Spred=pr.Spred and pr.Ocena < 6
	join Smer s
	on pl.Ssmer=s.Ssmer
group by pl.Ssmer,s.Nazivs
order by neuspeli desc

