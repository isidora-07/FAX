--MULTI-STATEMENT nacin kreiranja tabele 

alter FUNCTION polozeniIspiti2 (@indeks int, @upisan int)
RETURNS 
@polozeni  TABLE 
(
	sifraPredmeta int,
	ocena int
)
AS
BEGIN
	-- Fill the table variable with the rows for your result set
	insert into @polozeni --values (1, 10), (2, 9)
	select Spred, Ocena
	from Prijave
	where Ocena > 5 and Indeks = @indeks and Upisan = @upisan

	RETURN 
END
GO

select *
from dbo.polozeniIspiti2(1, 2000)