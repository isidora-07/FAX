--zadatak 1

select id_etape,sum(duzina_staze) as duzina_etape
from etape
group by id_etape

--zadatak 2

create view vreme as
select *
from prolazna_vremena 
where DATEDIFF(hour,vreme_starta,vreme_prolaska_kroz_cilj)<1

select vo.ime
from vreme v join vozaci vo on
	v.id_vozaca=vo.id_vozaca

--zadatk 3

select ime
from vozaci
where id_vozaca not in(
						select id_vozaca
						from prolazna_vremena
					  )

--zadatak 4

select ime
from vozaci
where id_vozaca in (
					select id_vozaca
					from prolazna_vremena
					where vreme_prolaska_kroz_cilj is null
					)

--zadatak 5

create view vreme_po_svakoj_stazi as
select  pv.id_vozaca,
		pv.id_etape,
		pv.redni_broj_staze,
		datediff(MILLISECOND, pv.vreme_starta, pv.vreme_prolaska_kroz_cilj) as vreme,
		v1.id_tipa
from prolazna_vremena pv join vozaci v
	on pv.id_vozaca=v.id_vozaca	
		join vozila v1
	on v.id_vozila=v1.id_vozila
where pv.vreme_prolaska_kroz_cilj is not null

create view najbolja_vremena_po_stazama as
select v.id_vozaca,v.id_etape,v.redni_broj_staze,v.id_tipa
from vreme_po_svakoj_stazi v left join vreme_po_svakoj_stazi v1
	on  v.vreme>v1.vreme and
		v.id_tipa=v1.id_tipa and
		v.id_etape=v1.id_etape and
		v.redni_broj_staze=v1.redni_broj_staze
where v1.id_etape is null

create view broj_pobeda_po_stazi as
select id_vozaca,id_tipa,count(*) as broj_pobeda
from najbolja_vremena_po_stazama
group by id_vozaca,id_tipa

select v.ime,tv.naziv
from broj_pobeda_po_stazi bp join vozaci v
	on bp.id_vozaca=v.id_vozaca
		join tip_vozila tv
	on tv.id_tipa=bp.id_tipa
where broj_pobeda = (
						select count(*)
						from etape 
						)

--zadatak 6

alter view vremePostazi as
select distinct v.ime,t.id_tipa,e.id_etape,e.redni_broj_staze,datediff(MILLISECOND,pv.vreme_starta,pv.vreme_prolaska_kroz_cilj) as vreme
from prolazna_vremena pv 
			join etape e 
	on pv.id_etape=e.id_etape and
		pv.redni_broj_staze=e.redni_broj_staze
			join vozaci v
	on v.id_vozaca=pv.id_vozaca
			join vozila v1
	on v.id_vozila=v1.id_vozila
			join tip_vozila t
	on t.id_tipa=v1.id_tipa
where pv.vreme_prolaska_kroz_cilj is not null
order by t.id_tipa,e.id_etape,e.redni_broj_staze

alter view generalni_plasman as
select v.ime,v.id_tipa,v.id_etape,v.redni_broj_staze,(
														select count(*)+1
														from vremePostazi v1
															where v.id_etape=v1.id_etape and
																v.redni_broj_staze=v1.redni_broj_staze and
																v.vreme>v1.vreme
													) as plasman
from vremePostazi v
order by v.id_tipa,v.id_etape,v.redni_broj_staze,v.vreme







	

