
create procedure getTempLessThan @tempC int
as
begin
select * from dbo.dm_weather where temperatureC < @tempC
end;

