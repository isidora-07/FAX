
create table t1
(
	id int primary key,
	naziv varchar(20)
)


create table t2
(
	id int primary key identity(10,5),
	naziv varchar(20)
)

insert into t1(id, naziv) values (1, 'n1')
insert into t1(naziv, id) values ('n2', 2)
insert into t1 values (3, 'n3'), (4, 'n4')

select * from t1


insert into t2 values ('n1'), ('n2'), ('n3')
select * from t2


