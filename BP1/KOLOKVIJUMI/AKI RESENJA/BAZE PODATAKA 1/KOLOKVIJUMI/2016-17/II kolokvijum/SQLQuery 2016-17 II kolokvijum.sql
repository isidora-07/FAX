--1

select *
from ocene
where ocena > 4.5 and komentar not like ''

--2

select distinct po.idClanaPonudjaca, c.ime 
from proizvodi pr join ponude po
   on pr.id = po.idProizvoda and po.datumPonude <= pr.krajLicitacije
                             and po.datumPonude >= pr.pocetakLicitacije
	join clanovi c
   on po.idClanaPonudjaca = c.id


--3

select distinct idClana, c.ime
from proizvodi p join clanovi c
    on p.idClana = c.id
where idClana not in (
					  select distinct idClanaPonudjaca
				      from ponude
					 )


--4

--a

alter view dobri_korisnici as
select id, idProizvoda, idClanaPonudjaca, novcaniIznos, datumponude
from Ponude
where idClanaPonudjaca in (
							 select distinct idClana2
							 from Ocene
							 where ocena > 4.5
						   )


alter view najbolje_ponude as
select dk1.id, dk1.idProizvoda, dk1.idClanaPonudjaca, dk1.novcaniIznos, dk1.datumponude
from dobri_korisnici dk1 left join dobri_korisnici dk2
    on dk1.idProizvoda=dk2.idProizvoda and
	   dk1.novcaniIznos < dk2.novcaniIznos
where dk2.novcaniIznos is null
	  

select *
from Proizvodi pr left join najbolje_ponude np
    on pr.id=np.idProizvoda 

--b


create view ponude_dobrih_korisnika as
select *
from Ponude
where idClanaPonudjaca in (
							select idClana2
							from Ocene
							group by idClana2
							having avg(ocena*1.0) > 4.5
					      )

create view ispravne_ponude as
select pdk.id, pdk.idProizvoda, pdk.idClanaPonudjaca, pdk.novcaniIznos, pr.id as id_Proizvoda, pr.idClana
from ponude_dobrih_korisnika pdk join Proizvodi pr
   on pdk.idProizvoda = pr.id and pdk.datumponude >= pr.pocetakLicitacije
                              and pdk.datumponude <= pr.krajLicitacije
						
create view bruto as
select ip1.idClana, sum(ip1.novcaniIznos) as zaradjeno
from ispravne_ponude ip1 left join ispravne_ponude ip2
  on ip1.id_Proizvoda=ip2.id_Proizvoda and ip1.novcaniIznos < ip2.novcaniIznos
where ip2.novcaniIznos is null  
group by ip1.idClana	

create view propale as
select pr.idClana, count (*) as neuspelo
from Proizvodi pr
where pr.id not in (
					select distinct ip.id_Proizvoda
					from ispravne_ponude ip
				   )
group by pr.idClana


select *, case
		  when
			  (
				select zaradjeno
				from bruto b
				where b.idClana=c.id
			   ) is null				then 0
			else 
				(
				 select zaradjeno
				 from bruto b
				 where b.idClana=c.id
				 )
		   end as zaradio, case
						   when
						      (
							   select neuspelo
							   from propale p
							   where c.id=p.idClana
							  ) is null then 0
						   else 
						      (
							   select neuspelo
							   from propale p
							   where c.id=p.idClana
							  )
						   end as propale_licitacije
from Clanovi c			


--5

