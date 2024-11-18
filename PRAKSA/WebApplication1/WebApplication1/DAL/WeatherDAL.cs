using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc.Razor;
using Microsoft.Extensions.Configuration;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Threading.Tasks;
using WebApplication1.Helper;

namespace WebApplication1.DAL
{
    public class WeatherDAL : IWeatherDAL
    {
        //private static string ConnectionString = @"Data Source=DESKTOP-B7V8RGC;Initial Catalog=weather_database;User ID=user1;Password=user1";
        private readonly IConfiguration _configuration;
        private string ConnectionString;
        public WeatherDAL(IConfiguration configuration)
        {
            _configuration = configuration;
            ConnectionString = ProtectionHelper.Singleton.GetSectionValue("ConnectionStrings:Connection1");
        }

        public void Insert(WeatherForecast weatherForecast)
        {
            using (SqlConnection connection = new SqlConnection(ConnectionString))
            {
                try
                {
                    connection.Open();
                    SqlCommand command = connection.CreateCommand();
                    command.CommandText = "INSERT INTO dm_weather VALUES (@date, @temperatureC, @temperatureF, @summary)";

                    SqlParameter parameter = command.CreateParameter();
                    parameter.DbType = System.Data.DbType.DateTime;
                    parameter.ParameterName = "@date";
                    parameter.Value = weatherForecast.Date;
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

            using(SqlConnection connection = new SqlConnection(ConnectionString))
            {

                try
                {
                    connection.Open();
                    SqlCommand command = connection.CreateCommand();
                    command.CommandText = $"SELECT * FROM dm_weather";

                    SqlDataReader reader = command.ExecuteReader();
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

            using(SqlConnection connection = new SqlConnection(ConnectionString))
            {
                try
                {
                    connection.Open();
                    SqlCommand command = new SqlCommand("getAllTemeratures", connection);
                    command.CommandType = System.Data.CommandType.StoredProcedure;

                    SqlDataReader reader = command.ExecuteReader();

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
                catch(Exception ex)
                {
                    throw new Exception(ex.Message);
                }
            }
            return weatherForecasts;
            
        }

        public List<WeatherForecast> GetTempLessThan(int temp)
        {
            List<WeatherForecast> weatherForecasts = new List<WeatherForecast>();

            using (SqlConnection connection = new SqlConnection(ConnectionString))
            {
                try
                {
                    connection.Open();
                    SqlCommand command = new SqlCommand("getTempLessThan", connection);
                    command.CommandType = System.Data.CommandType.StoredProcedure;

                    SqlParameter parameter = command.CreateParameter();
                    parameter.DbType = System.Data.DbType.Int32;
                    parameter.Value = temp;
                    parameter.ParameterName = "@tempC";
                    command.Parameters.Add(parameter);

                    SqlDataReader reader = command.ExecuteReader();
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
