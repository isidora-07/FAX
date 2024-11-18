--1.

--Ugnjezdeni upit sluzi da odredi clanove koji trenutno sviraju u njemu.
--Neki clan i dalje svira u bendu ukoliko mu je vrednost za datum2 null
--Spoljasnji upit sluzi samo da se od idMuzicara dobije njegovo ime i prezime, 
--kao i da se od idBenda dobije naziv benda

select b.naziv, m.ime, m.prezime
from bendovi b join (
						select *
						from sastav_benda sb
						where sb.datum2 is null) t on b.idBenda = t.idBenda join muzicari m on m.idMuzicara = t.idMuzicara

--2.

--Ugnjezdeni upit sluzi za racunanje koliko je svaki posetilac potrosio novca na kupovinu karata
--Spoljasnji sluzi da se od idPosetioca dobije njegovo ime

select p.ime,p2.Novac
from posetioci p join (
						select idPosetioca, sum(cena) as 'Novac'
						from karte
						group by idPosetioca) p2 on p.idPosetioca = p2.idPosetioca

--3.

--Ugnjezdeni upit trazi maksimalnu cenu za svaki festival
--Spoljasnji upit, od svih cena karata ne ukljucujuci maksimalnu cenu, uzima maksimalnu cenu
--To znaci da je to druga po redu maksimalna cena karte
select idFestivala, max(cena) as 'drugaCena' 
from karte k1 
where cena not in (
					select max(cena)
					from karte k4
					where k4.idFestivala = k1.idFestivala
					)
group by idFestivala



--4.

--View ispravni_datumi sluzi da ukoliko kolona datum2 ima null vrednost, nju pretvori u trenutni datum
--To se radi da bi kasnije mogli da se oduzimaju datumi
create view ispravni_datumi as
select idMuzicara, idBenda, datum1, case when datum2 is null then getdate() else datum2 end as 'datum2'
from sastav_benda

--View staz se nastavlja na prethodni view
--Ima dodatu kolonu staz koja predstavlja koliko dugo je neki clan benda bio u njemu
create view staz as
select *,DATEDIFF(year, datum1, datum2) as 'staz'
from ispravni_datumi id1

--Sada se staz svakog muzicara pretvara u odgovarajuci string
select idMuzicara, idBenda, case when staz < 5 then 'Guster' 
								when staz >=5 and staz<=10 then 'Dzomba' 
								else 'Stara kajla' 
								end as 'Status'
from staz

--5.

--U view-u peti se racuna ukupna zarada i broj posetilaca za svaki festival po godinama
--iskljucujuci one festivale koji su trajali manje od 2 dana
--Ti festivali se iskljucuju u where delu
alter view peti as
select lf.idFestivala, lf.godina, sum(cena) as 'ukupnaZarada', count(*) as 'brojPosetilaca'
from lineup_festivala lf left join karte k on lf.idFestivala = k.idFestivala and lf.godina = k.godina and lf.dan = k.dan
where exists (select * from karte where idFestivala = lf.idFestivala and godina = lf.godina and dan >= 2)
group by lf.idFestivala, lf.godina

--Ovaj upit sluzi za rangiranje po zaradi i broju posetilaca
--Ugnjzdeni upit u select-u broji koliko ima festivala odredjene godine su imali vecu zaradu od trenutnog festivala
--U slucaju da su imali istu zaradu, gleda se broj posetilaca
select idFestivala, godina, (select count(*) + 1 
							from peti p2 
							where (p1.ukupnaZarada < p2.ukupnaZarada or 
									(p1.ukupnaZarada = p2.ukupnaZarada and p1.brojPosetilaca < p2.brojPosetilaca)) and 
									p1.godina = p2.godina) as 'rang'
from peti p1
order by godina desc, rang


--6.

--Za svaki datum1 se gleda koliko je muzicara iz trenutnog benda sviralo u tom trenutku(datum1)
--Kada se nadju ti muzicari, potrebno je samo da se izbroji koliko ih ima
--To radi ugnjezdeni upit u select-u
create view sesti as
select *, (select count(*) from sastav_benda sb2 where sb2.idBenda = sb1.idBenda and ((sb1.datum1 between sb2.datum1 and sb2.datum2) or (sb1.datum1 >= sb2.datum1 and sb2.datum2 is null))) as 'brojClanova'
from sastav_benda sb1

--Ovaj upit sluzi za nalazenje benda sa najvecim brojem clanova
select distinct s1.idBenda
from sesti s1 left join sesti s2 on s1.brojClanova < s2.brojClanova
where s2.brojClanova is null

--7. 

--Left join sluzi da odredi da li je odredjeni clan benda imao nekog kolegu u bendu u trenutku kada je napustio bend
--Ukoliko nije imao nikog, bilo koja kolona sb2 ce imati null vrednost
--Nije potrebno gledati one redove koji trenutno imaju aktivnog clana u bendu
--Ugnjezdeni upit sluzi da nadje datum kada je neki clan u buducnosti ponovo usao u odredjeni bend
--Spoljasnji upit gleda datum2, a unutrasnji datum1
--Taj datum2 je trenutak kada je poslednji clan benda napustio bend
--datum1 je trenutak kada je odredjeni muzicara ponovo krenuo da svira u bendu
--Bend u periodu datum2-datum1 nije imao clanove
select sb1.idBenda, sb1.datum2 as 'datum1', (select min(datum1) from sastav_benda sb3 where sb1.idBenda = sb3.idBenda and sb3.datum1 > sb1.datum2) as 'datum2'
from sastav_benda sb1 left join sastav_benda sb2 on sb1.idMuzicara != sb2.idMuzicara and sb1.idBenda = sb2.idBenda and ((sb1.datum2 between sb2.datum1 and sb2.datum2) or (sb1.datum2 >= sb2.datum1 and sb2.datum2 is null))
where sb2.datum1 is null and sb1.datum2 is not null