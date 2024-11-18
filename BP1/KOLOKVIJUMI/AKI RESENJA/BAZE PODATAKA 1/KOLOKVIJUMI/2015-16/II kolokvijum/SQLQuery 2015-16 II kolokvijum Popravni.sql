--1

select f.Naziv, f.Sediste, t.naziv
from Firma f join ProfilFirme pf
    on f.IDFirme=pf.IDFirme and (f.Sediste='Kragujevac'
								or f.Sediste='Kraljevo')
	join Tehnologije t
	on pf.TehID=t.TehID and (t.naziv='JavaScript' or
							 t.naziv='CSS' or
							 t.naziv='PHP')
order by t.naziv, f.Naziv desc

--2

select *
from Student
where JMBG in (	
				select JMBGVlasnika
				from Firma
			   )

--3

--a

select IDFirme, count(*) as navodjene
from AnketaStudent
group by IDFirme 
order by navodjene desc

--b

select f.Naziv, count(*) as navodjene
from AnketaStudent a join Firma f
    on a.IDFirme=f.IDFirme
group by a.IDFirme, f.Naziv 
order by navodjene desc


--4

create view potencijalni as
select s.Indeks, s.Upisan, s.prosek, a.IDFirme, a.rbr
from Student s join	AnketaStudent a
   on s.Indeks=a.Indeks and s.Upisan=a.Upisan
where a.rbr=1


select p1.Indeks, p1.Upisan, p1.IDFirme
from potencijalni p1
where not exists (
				 select *
				 from potencijalni p2
				 where p1.IDFirme=p2.IDFirme and
				       p1.prosek < p2.prosek
				 )


--5

create view tri_zelje as
select s.Indeks, s.Upisan, s.prosek,a.IDFirme, a.rbr
from Student s join AnketaStudent a
    on s.Indeks=a.Indeks and s.Upisan=a.Upisan
where a.rbr=1 or a.rbr=2 or a.rbr=3

create view best as
select IDFirme, count(*) as broj
from tri_zelje tz1
where not exists (
				 select *
				 from tri_zelje tz2
				 where tz1.IDFirme=tz2.IDFirme and
				       tz1.Upisan=tz2.Upisan and
					   tz1.prosek < tz2.prosek
				 )
group by IDFirme

select b1.IDFirme, b1.broj
from best b1 left join best b2
    on b1.IDFirme=b2.IDFirme and
	   b1.broj<b2.broj
where b2.broj is null
