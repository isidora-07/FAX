--zadatak 1

select *
from ocene
where ocena>4.5 and komentar not like ''

--zadatak 2

select distinct po.idClanaPonudjaca,c.ime
from ponude po join proizvodi pr
	on po.datumPonude>=pr.pocetakLicitacije and po.datumPonude<=pr.krajLicitacije
											and po.idProizvoda=pr.id
			join clanovi c on po.idClanaPonudjaca=c.id

--zadatak 3


select distinct p.idClana,c.ime
from proizvodi p join clanovi c on p.idClana=c.id
where idClana not in (
						select idClanaPonudjaca
						from ponude 

					) 