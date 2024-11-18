using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplication1.DAL
{
    public interface IWeatherDAL
    {
        public List<WeatherForecast> Select();
        public void Insert(WeatherForecast weatherForecast);
        public List<WeatherForecast> SelectProc();
        public List<WeatherForecast> GetTempLessThan(int temp);
    }
}
