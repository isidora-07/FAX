CREATE FUNCTION prosek (@indeks int, @upisan int )
RETURNS float
AS
BEGIN
	-- Declare the return variable here
	DECLARE @prosekStudenta float

	-- Add the T-SQL statements to compute the return value here
	SELECT @prosekStudenta = avg(ocena*1.0)
	from Prijave
	where Indeks = @indeks and Upisan = @upisan and ocena > 5

	-- Return the result of the function
	RETURN @prosekStudenta
END
GO

select getdate()

select dbo.prosek(1, 2000)

select s.Imes, s.indeks, s.upisan, avg(p.ocena*1.0) prosecnaOcena
from Studenti s join Prijave p on
	s.indeks = p.Indeks and s.upisan = p.Upisan and p.Ocena > 5
group by s.upisan, s.indeks, s.Imes
having avg(p.ocena*1.0) > 8

select imes, dbo.prosek(indeks, upisan) prosecnaOcena
from Studenti
where dbo.prosek(indeks, upisan) > 8

