using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApplication1.Controllers;

namespace WebApplication1.BLL
{
    public interface IWeatherBLL
    {
        public List<WeatherForecast> Select();
        public void Insert(WeatherForecast weatherForecast);
        public List<WeatherForecast> SelectProc();
        public List<WeatherForecast> GetTempLessThan(int temp);
        public string Hash(byte[] json);
    }
}
