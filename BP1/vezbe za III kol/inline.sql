--INLINE

CREATE FUNCTION polozeniIspiti (@indeks int, @upisan int)
RETURNS TABLE 
AS
RETURN 
(
	select Spred, Ocena
	from Prijave
	where Ocena > 5 and Indeks = @indeks and Upisan = @upisan
)
GO


select *
from dbo.polozeniIspiti(1, 2000)