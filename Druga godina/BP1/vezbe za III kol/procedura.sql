--PROCEDURE

alter PROCEDURE prosek_polozeni
	@indeks int,
	@upisan int, 
	@prosek float output, --izlazni parametar koji treba da se izracuna, kao referenca
	@brPolozenih int output
AS
BEGIN
	SELECT @prosek = avg(ocena*1.0), @brPolozenih = count(*)
	from Prijave
	where Indeks = @indeks and Upisan = @upisan and ocena > 5
END
GO

--primer poziva
declare @prosek_izlaz float
declare @polozeni_izlaz int
exec prosek_polozeni 1, 2000, 
	@prosek = @prosek_izlaz output, 
	@brPolozenih = @polozeni_izlaz output

select 1, 2000, @polozeni_izlaz, @prosek_izlaz