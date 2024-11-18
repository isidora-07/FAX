--zadatak 1

select ime,prezime
from VLASNIK
where datepart(year,getdate())-datepart(YEAR,datumRodjenja)<21

--zadatak 2

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
			 
--zadatak 3
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
			 								