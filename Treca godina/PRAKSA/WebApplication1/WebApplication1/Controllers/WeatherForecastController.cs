using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using WebApplication1.BLL;

namespace WebApplication1.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class WeatherForecastController : ControllerBase
    {
        private static readonly string[] Summaries = new[]
        {
            "Freezing", "Bracing", "Chilly", "Cool", "Mild", "Warm", "Balmy", "Hot", "Sweltering", "Scorching"
        };

        private readonly ILogger<WeatherForecastController> _logger;
        private readonly IWeatherBLL _weatherBLL;

        public WeatherForecastController(IWeatherBLL weatherBll, ILogger<WeatherForecastController> logger)
        {
            _logger = logger;
            _weatherBLL = weatherBll;
        }

        [HttpGet]
        public IEnumerable<WeatherForecast> Get()
        {
            var rng = new Random();
            return Enumerable.Range(1, 5).Select(index => new WeatherForecast
            {
                Date = DateTime.Now.AddDays(index),
                TemperatureC = rng.Next(-20, 55),
                Summary = Summaries[rng.Next(Summaries.Length)]
            })
            .ToArray();
        }


        [HttpGet]
        [Route("select")]
        public List<WeatherForecast> Select()
        {
            return _weatherBLL.Select();
        }

        [HttpPost]
        [Route("insert")]
        public void Insert([FromBody] WeatherForecast weatherForecast)
        {
            _weatherBLL.Insert(weatherForecast);
        }

        [HttpGet]
        [Route("selectProc")]
        public List<WeatherForecast> SelectProc()
        {
            return _weatherBLL.SelectProc();
        }

        [Authorize]
        [HttpGet]
        [Route("getTempLessThan")]
        public List<WeatherForecast> GetTempLessThan(int temp)
        {
            return _weatherBLL.GetTempLessThan(temp);
        }

        [HttpPost]
        [Route("getHash")]
        public string GetHash()
        {
            byte[] buffer = new byte[Convert.ToInt32(Request.ContentLength)];
            Request.Body.ReadAsync(buffer, 0, buffer.Length);

            return _weatherBLL.Hash(buffer);
        }

    }
}
