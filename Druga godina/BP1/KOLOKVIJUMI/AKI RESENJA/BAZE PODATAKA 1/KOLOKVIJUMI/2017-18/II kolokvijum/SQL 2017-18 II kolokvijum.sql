-- 1

select id_etape, sum(duzina_staze)
from etape
group by id_etape

--2

select v.ime
from prolazna_vremena pv join vozaci v
   on pv.id_vozaca = v.id_vozaca
where datediff(MINUTE, pv.vreme_starta, pv.vreme_prolaska_kroz_cilj) < 60

--3

create view svi_vozili_sve as
select id_vozaca, id_etape, redni_broj_staze
from vozaci,etape

select distinct v.ime
from svi_vozili_sve svs left join prolazna_vremena pv 
   on pv.id_vozaca = svs.id_vozaca and pv.id_etape = svs.id_etape
                                   and pv.redni_broj_staze = svs.redni_broj_staze
	join vozaci v
	on svs.id_vozaca = v.id_vozaca
where pv.id_etape is null

--4

select ime
from vozaci
where id_vozaca in (
					select id_vozaca
					from prolazna_vremena
					where vreme_prolaska_kroz_cilj is null
				    )


--5

create view vreme_po_stazi as
select pv.id_vozaca, pv.id_etape, pv.redni_broj_staze, datediff(MILLISECOND, pv.vreme_starta, pv.vreme_prolaska_kroz_cilj) as vreme, v2.id_tipa
from prolazna_vremena pv join vozaci v
    on pv.id_vozaca = v.id_vozaca
	join vozila v2
	on v.id_vozila = v2.id_vozila
where pv.vreme_prolaska_kroz_cilj is not null


create view pobede as
select v1.id_vozaca, v1.id_etape, v1.redni_broj_staze, v1.vreme, v1.id_tipa 
from vreme_po_stazi v1 left join vreme_po_stazi v2
    on v1.vreme > v2.vreme and
	   v1.id_etape = v2.id_etape and
	   v1.redni_broj_staze = v2.redni_broj_staze and
	   v1.id_tipa=v2.id_tipa
where v2.vreme is null
    

create view brojPobedaPoTipu as
select id_vozaca, id_tipa, count (*) as brojPobeda
from pobede
group by id_tipa, id_vozaca


select v.ime, t.naziv
from brojPobedaPoTipu bpt join vozaci v 
    on bpt.id_vozaca = v.id_vozaca
	join tip_vozila t
	on bpt.id_tipa=t.id_tipa
where brojPobeda = (
					select count(*)
					from etape
				   )


--6

alter view vreme_staze as
select v.ime, v.id_vozaca, v2.id_tipa, pv.id_etape, pv.redni_broj_staze, datediff(MILLISECOND, pv.vreme_starta, pv.vreme_prolaska_kroz_cilj) as vreme
from prolazna_vremena pv join vozaci v
    on pv.id_vozaca = v.id_vozaca
	join vozila v2
	on v.id_vozila = v2.id_vozila
where pv.vreme_prolaska_kroz_cilj is not null
order by id_tipa,id_etape, redni_broj_staze, vreme


alter view plasmani as
select ime, id_vozaca, id_tipa, id_etape, redni_broj_staze, (
												  select count(*)+1
												  from vreme_staze vs2
												  where vs1.id_tipa=vs2.id_tipa and
												        vs1.id_etape=vs2.id_etape and
														vs1.redni_broj_staze=vs2.redni_broj_staze and
														vs1.vreme > vs2.vreme
												 ) as plasman
from vreme_staze vs1
order by id_tipa,id_etape, redni_broj_staze, vreme

--7

create view staza_datum as
select v.ime, v.id_vozaca, v2.id_tipa, pv.id_etape, pv.redni_broj_staze, pv.vreme_prolaska_kroz_cilj,datediff(MILLISECOND, pv.vreme_starta, pv.vreme_prolaska_kroz_cilj) as vreme
from prolazna_vremena pv join vozaci v
    on pv.id_vozaca = v.id_vozaca
	join vozila v2
	on v.id_vozila = v2.id_vozila
where pv.vreme_prolaska_kroz_cilj is not null
order by id_tipa,id_etape, redni_broj_staze, vreme


create view plasmani_datum as
select ime, id_vozaca, id_tipa, id_etape, redni_broj_staze, vreme_prolaska_kroz_cilj,(
																					  select count(*)+1
																					  from staza_datum vs2
																					  where vs1.id_tipa=vs2.id_tipa and
																							vs1.id_etape=vs2.id_etape and
																							vs1.redni_broj_staze=vs2.redni_broj_staze and
																							vs1.vreme > vs2.vreme
																					 ) as plasman
from staza_datum vs1
order by id_tipa,id_etape, redni_broj_staze, vreme


select *
from vozaci
where id_vozaca not in (
						select distinct id_vozaca
						from plasmani_datum pd1
						where exists(
										select *
										from plasmani_datum pd2
										where pd1.id_vozaca=pd2.id_vozaca and
											  pd1.vreme_prolaska_kroz_cilj > pd2.vreme_prolaska_kroz_cilj and
											  pd1.plasman > pd2.plasman
									)
						)



--8

alter view bodovi as
select p.ime, p.id_vozaca, 
						  case
						  when p.plasman=1 then 10
						  when p.plasman=2 then 7
						  when p.plasman=3 then 5
						  when p.plasman=4 then 3
						  end as poeni
from plasmani p join tip_vozila tv
    on p.id_tipa = tv.id_tipa and tv.naziv='automobil'


select ime, sum(poeni) as ukupan_broj_poena
from bodovi
group by ime, id_vozaca