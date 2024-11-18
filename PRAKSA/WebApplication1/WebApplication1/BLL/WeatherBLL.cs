using Microsoft.Extensions.Configuration;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Cryptography;
using System.Text;
using System.Threading.Tasks;
using WebApplication1.DAL;

namespace WebApplication1.BLL
{
    public class WeatherBLL : IWeatherBLL
    {
        private readonly IWeatherDAL _weatherDAL;
        private readonly IConfiguration _configuration;

        public WeatherBLL(IWeatherDAL weatherDAL, IConfiguration configuration)
        {
            _weatherDAL = weatherDAL;
            _configuration = configuration;
        }

        public void Insert(WeatherForecast weatherForecast)
        {
            _weatherDAL.Insert(weatherForecast);
        }

        public List<WeatherForecast> Select()
        {
           return  _weatherDAL.Select();
        }

        public List<WeatherForecast> SelectProc()
        {
            return _weatherDAL.SelectProc();
        }
        public List<WeatherForecast> GetTempLessThan(int temp)
        {
            return _weatherDAL.GetTempLessThan(temp);
        }

        public string Hash(byte[] json)
        {
            byte[] keyBytes = Encoding.ASCII.GetBytes(_configuration["Hash:Secret"]);
            HMACSHA256 hMACSHA256 = new HMACSHA256(keyBytes);
            byte[] bytes = hMACSHA256.ComputeHash(json);
            return Convert.ToBase64String(bytes);
        }
    }
}
