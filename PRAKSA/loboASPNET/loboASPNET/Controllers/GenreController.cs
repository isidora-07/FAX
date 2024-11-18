using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using loboASPNET.BLL;
using loboASPNET.Models;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace loboASPNET.Controllers
{
    [Authorize]
    [Route("api/genre")]
    [ApiController]
    public class GenreController : ControllerBase
    {
        private readonly IGenreBLL _genreBLL;

        public GenreController(IGenreBLL genreBLL)
        {
            _genreBLL = genreBLL;
        }

        [Authorize(Roles = "user, admin")]
        [HttpGet]
        public List<Genre> Get()
        {
            return _genreBLL.Select();
        }

        [Authorize(Roles = "user, admin")]
        [HttpGet("getGenre")]
        public Genre GetGenre(int id)
        {
            return _genreBLL.GetGenreByID(id);            
        }

        [Authorize(Roles = "admin")]
        [HttpPost]
        public string AddGenre([FromBody] Genre aGenre)
        {
            bool isNull = aGenre.GetType().GetProperties().All(x => x.GetValue(aGenre) == null);
           
            if (isNull)
            {
                return "Došlo je do greške.";
            }
            else
            {
                if(_genreBLL.Insert(aGenre) == false)
                {
                    return "Neuspes+šno dodavanje.";
                }
                return "Uspešno dodavanje!";
            }
        }


        [Authorize(Roles = "admin")]
        [HttpDelete("deleteGenre")]
        public string DeleteGenre(int id)
        {
            if(_genreBLL.DeleteGenreByID(id) == false)
            {
                return "Neuspešno brisanje.";
            }
            return "Uspešno brisanje!";
        }


        [Authorize(Roles = "admin")]
        [HttpGet("updateGenre")]
        public string UpdateGenre(int id, string name)
        {
            if (_genreBLL.UpdateGenre(id, name) == false)
            {
                return "Neuspešna izmena podataka.";
            }
            return "Uspešno ste izmenili podatke!";
        }

    }
}
