--1

select sifra
from paketi
where DATEPART(YEAR, datumSlanja) = 2013

--2

--a

select sifraPaketa
from transferi
where sifraPaketa in (
					 select sifraPaketa
					 from transferi
					 where datumPrimanja is NULL
					 )

--b

select sifraPaketa
from transferi
where datediff(day, datumSlanja, datumPrimanja) > 15 or datumPrimanja is NULL

--3

create view kasne as
select t1.sifraPosteSlanja, count(*) as paketa_kasni
from transferi t1
where exists (
		      select *
			  from transferi t2
			  where t1.datumSlanja>=t2.datumSlanja and
			        t1.datumPrimanja>=t2.datumPrimanja and
					DATEDIFF(day, t1.datumSlanja, t1.datumPrimanja) > 
				    DATEDIFF(day, t2.datumSlanja, t2.datumPrimanja)
			 )
group by t1.sifraPosteSlanja


select k1.sifraPosteSlanja, k1.paketa_kasni
from kasne k1 left join kasne k2
    on k1.paketa_kasni < k2.paketa_kasni
where k2.paketa_kasni is null




--4


create view zadrzano as
select t1.sifraPostePrimanja, count(*) as zadrzali
from transferi t1 join transferi t2
    on t1.sifraPaketa=t2.sifraPaketa and
	   t1.sifraPostePrimanja=t2.sifraPosteSlanja and
	   t1.datumSlanja<t2.datumSlanja and
	   t1.datumPrimanja<t2.datumSlanja
group by t1.sifraPostePrimanja
		


select z1.sifraPostePrimanja, z1.zadrzali
from zadrzano z1 left join zadrzano z2
   on z1.zadrzali<z2.zadrzali
where z2.zadrzali is null


--5


select sifraPosiljaoca, count(*) as cena
from paketi
--where DATEDIFF(MONTH, datumSlanja, DATEADD(MONTH,-1,getdate())) = 0 
where DATEDIFF(MONTH, datumSlanja, DATEADD(MONTH, -1, '2014-03-15'))=0 and realizovaniDatumPrimanja is not null
group by sifraPosiljaoca


--6

create view poslato as
select sifraPosteSlanja, datumSlanja, count(*) as poslato
from transferi
group by sifraPosteSlanja, datumSlanja

create view primljeno as
select sifraPostePrimanja, datumSlanja, count(*) as primljeno
from transferi
group by sifraPostePrimanja, datumSlanja

select case 
       when po.datumSlanja is null then pr.datumSlanja
	   else po.datumSlanja
	   end as datum, case
					 when po.sifraPosteSlanja is null then pr.sifraPostePrimanja
					 else po.sifraPosteSlanja
				     end as sifraPoste, case
										when poslato is null then primljeno
										when primljeno is null then poslato
										else poslato+primljeno
										end as ukupno
from poslato po full outer join primljeno pr
   on po.datumSlanja=pr.datumSlanja and
      po.sifraPosteSlanja=pr.sifraPostePrimanja
