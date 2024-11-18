SELECT * FROM bend
select * from genre
select * from song
select * from artist
select * from user
select * from login_t

delete from  login_t

insert into genre values(null, 'Rok')
insert into genre values(null, 'Alternativni rok')
insert into genre values(null, 'Fank')
insert into genre values(null, 'Tehno')

insert into bend values(null, 'EKV', 1) 
insert into bend values(null, 'Bijelo dugme', 2)
insert into bend values(null, 'Azra', 1)
insert into bend values(null, 'Dino Dvornik', 3)

insert into artist values(null ,'Margita Stefanovi', '2020-07-27 20:23:32', 1)
insert into artist values(null,'Bojan Peca', '2020-07-27 20:23:32', 1)
insert into artist values(null, 'Srdjan Todorovic', '2020-07-27 20:23:32', 1)
insert into artist values(null, 'Ivica Vdovic', '2020-07-27 20:23:32', 1)
insert into artist values(null, 'Zeljko Bebek', '2020-07-27 20:23:32', 2)
insert into artist values(null, 'Tifa', '2020-07-27 20:23:32', 2)
insert into artist values(null, 'Dino Dvornik', '2020-07-27 20:23:32', 3)

insert into song values(null, 'Par godina za nas', 1)
insert into song values(null, 'Oci boje meda', 1)
insert into song values(null, 'Srce', 1)
insert into song values(null, 'Iznad grada', 1)
insert into song values(null, 'Budi sam na ulici', 1)

insert into [user] values(null, 'isidoriska','isi123', 'user')
insert into [user] values(null, 'nikola','nikola123', 'admin')

insert into login_t values(null, 'isi123', 'isidoriska')
insert into login_t values(null, 'nikola123', 'nikola')


select song.nameSong, bend.nameBend from song join bend on song.idBend = bend.idBend and song.idSong = 3

select a.nameArtist, a.dateArtist, b.nameBend from artist a join bend b on b.idBend = a.idBend and a.idArtist = 4

select s.idSong, s.nameSong, b.nameBend from song s join bend b on s.idBend = b.idBend

select b.nameBend, g.nameGenre from bend b join genre g on b.idBend=1 and g.idGenre = b.idGenre

