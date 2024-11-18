CREATE TABLE [dbo].[dm_weather](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[date] [datetime] NULL,
	[temperatureC] [int] NULL,
	[temperatureF] [int] NULL,
	[sumarry] [nchar](100) NULL,
 CONSTRAINT [PK_dm_weather] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

select * from dm_weather

create procedure getTemperature
AS
begin
select * from dbo.dm_weather
end

