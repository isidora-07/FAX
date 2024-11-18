--1

select *
from VLASNIK
where datepart(year, getdate())-datepart(year,datumRodjenja)<21

--2

select *
from PARCELA p join TIP t
    on p.sifraTipa = t.sifra and t.naziv='Gradjevinsko zemljiste'
where exists (
			 select *
			 from POSED pos
             where pos.datumPocetka<='2013-05-24' and pos.datumZavrsetka>='2013-05-24'
			       and p.sifra=pos.sifraParcele	
             group by pos.sifraParcele
             having count(pos.sifraVlasnika) <= 2	
			 )

--3

create view vlasnistvo as
select sifraParcele, sifraVlasnika, 
								  case
								  when datumZavrsetka is null then datediff(MONTH, datumPocetka, getdate())
								  else datediff(MONTH, datumPocetka, datumZavrsetka)
								  end as meseci
from POSED


select v1.sifraParcele, p.naziv, v1.meseci
from vlasnistvo v1 left join vlasnistvo v2
   on v1.sifraParcele = v2.sifraParcele and
      v1.meseci < v2.meseci 
	  join PARCELA p
   on v1.sifraParcele=p.sifra
where v2.meseci is null




--4

select *
from posed p1
where not exists (
				  select *
				  from posed p2
				  where p1.sifraParcele=p2.sifraParcele and
						p1.sifraVlasnika!=p2.sifraVlasnika
				 )

--5

create view svoje_parcele as
select p1.sifraVlasnika, p1.sifraParcele
from posed p1
where p1.datumZavrsetka is null and not exists (
												select *
											    from posed p2
												where p1.sifraParcele=p2.sifraParcele and
												p1.sifraVlasnika!=p2.sifraVlasnika
												)

create view povrsina_svih as
select sp.sifraVlasnika, sum(povrsina) as povrsina
from svoje_parcele sp join PARCELA p
    on sp.sifraParcele=p.sifra
group by sp.sifraVlasnika



select p1.sifraVlasnika, p1.povrsina
from povrsina_svih p1 left join povrsina_svih p2
    on p1.sifraVlasnika=p2.sifraVlasnika and
	   p1.povrsina < p2.povrsina
where p2.povrsina is null


--6

create view n_POSED as
select sifraParcele, sifraVlasnika, datumPocetka, case
												  when datumZavrsetka is null then getdate() 
												  else datumZavrsetka
												  end as datumZavrsetka
from POSED

alter view potencijalne_rupe as
select p1.sifraParcele, p1.datumZavrsetka as pocetak, p2.datumPocetka as kraj
from n_POSED p1 join n_POSED p2
    on p1.sifraParcele=p2.sifraParcele and
	   p1.datumZavrsetka<p2.datumPocetka


select pr.sifraParcele
from potencijalne_rupe pr
where not exists (
				  select *
				  from POSED p
				  where pr.sifraParcele=p.sifraParcele and
				        p.datumPocetka<=pr.pocetak and
						p.datumZavrsetka>=pr.kraj
				 )