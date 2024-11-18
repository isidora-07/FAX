using Microsoft.Extensions.Configuration;
using System;
using System.Collections.Generic;
using System.Data.SQLite;
using System.Linq;
using System.Threading.Tasks;
using WebApplication1.Helper;

namespace WebApplication1.DAL
{
    public class SecondWeatherDAL : IWeatherDAL
    {
        private readonly IConfiguration _configuration;
        private string ConnectionString;
        public SecondWeatherDAL(IConfiguration configuration)
        {
            _configuration = configuration;
            ConnectionString = ProtectionHelper.Singleton.GetSectionValue("ConnectionStrings:Connection2");
        }

        public void Insert(WeatherForecast weatherForecast)
        {
            using (SQLiteConnection connection = new SQLiteConnection(ConnectionString))
            {
                try
                {
                    connection.Open();
                    SQLiteCommand command = connection.CreateCommand();
                    command.CommandText = "INSERT INTO weather VALUES (NULL, @date, @temperatureC, @temperatureF, @summary)";

                    SQLiteParameter parameter = command.CreateParameter();
                    parameter.DbType = System.Data.DbType.String;
                    parameter.ParameterName = "@date";
                    parameter.Value = weatherForecast.Date.ToString();
                    command.Parameters.Add(parameter);

                    parameter = command.CreateParameter();
                    parameter.DbType = System.Data.DbType.Int32;
                    parameter.ParameterName = "@temperatureC";
                    parameter.Value = weatherForecast.TemperatureC;
                    command.Parameters.Add(parameter);

                    parameter = command.CreateParameter();
                    parameter.DbType = System.Data.DbType.Int32;
                    parameter.ParameterName = "@temperatureF";
                    parameter.Value = weatherForecast.TemperatureF;
                    command.Parameters.Add(parameter);

                    parameter = command.CreateParameter();
                    parameter.DbType = System.Data.DbType.String;
                    parameter.ParameterName = "@summary";
                    parameter.Value = weatherForecast.Summary;
                    command.Parameters.Add(parameter);

                    command.ExecuteNonQuery();
                    connection.Close();

                }
                catch (Exception ex)
                {
                    throw new Exception(ex.Message);
                }
            }
        }

        public List<WeatherForecast> Select()
        {
            List<WeatherForecast> weatherForecasts = new List<WeatherForecast>();

            using (SQLiteConnection connection = new SQLiteConnection(ConnectionString))
            {

                try
                {
                    connection.Open();
                    SQLiteCommand command = connection.CreateCommand();
                    command.CommandText = $"SELECT * FROM weather";

                    SQLiteDataReader reader = command.ExecuteReader();
                    while (reader.Read())
                    {
                        weatherForecasts.Add(new WeatherForecast()
                        {
                            Date = DateTime.Parse(reader[1].ToString()),
                            TemperatureC = Int32.Parse(reader[2].ToString()),
                            Summary = reader[4].ToString()
                        });
                    }
                    reader.Close();
                    connection.Close();
                }
                catch (Exception ex)
                {
                    throw new Exception(ex.Message);
                }
                return weatherForecasts;
            }
        }

        public List<WeatherForecast> SelectProc()
        {
            List<WeatherForecast> weatherForecasts = new List<WeatherForecast>();

            using (SQLiteConnection connection = new SQLiteConnection(ConnectionString))
            {
                try
                {
                    connection.Open();
                    SQLiteCommand command = connection.CreateCommand();
                    command.CommandText = "SELECT * FROM weather";

                    SQLiteDataReader reader = command.ExecuteReader();

                    while (reader.Read())
                    {
                        weatherForecasts.Add(new WeatherForecast()
                        {
                            Date = DateTime.Parse(reader[1].ToString()),
                            TemperatureC = Int32.Parse(reader[2].ToString()),
                            Summary = reader[4].ToString()
                        });
                    }
                    reader.Close();
                    connection.Close();

                }
                catch (Exception ex)
                {
                    throw new Exception(ex.Message);
                }
            }
            return weatherForecasts;

        }

        public List<WeatherForecast> GetTempLessThan(int temp)
        {
            List<WeatherForecast> weatherForecasts = new List<WeatherForecast>();

            using (SQLiteConnection connection = new SQLiteConnection(ConnectionString))
            {
                try
                {
                    connection.Open();
                    SQLiteCommand command = connection.CreateCommand();
                    command.CommandText = ("SELECT * FROM weather WHERE temperatureC < @tempC");

                    SQLiteParameter parameter = command.CreateParameter();
                    parameter.DbType = System.Data.DbType.Int32;
                    parameter.Value = temp;
                    parameter.ParameterName = "@tempC";
                    command.Parameters.Add(parameter);

                    SQLiteDataReader reader = command.ExecuteReader();
                    while (reader.Read())
                    {
                        weatherForecasts.Add(new WeatherForecast()
                        {
                            Date = DateTime.Parse(reader[1].ToString()),
                            TemperatureC = Int32.Parse(reader[2].ToString()),
                            Summary = reader[4].ToString()
                        });
                    }
                    reader.Close();
                    connection.Close();

                }
                catch (Exception ex)
                {
                    throw new Exception(ex.Message);
                }
            }
            return weatherForecasts;
        }
    }
}
