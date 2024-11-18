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
    [Route("api/artist")]
    [ApiController]
    public class ArtistController : ControllerBase
    {
        private readonly IArtistBLL _artistBLL;

        public ArtistController(IArtistBLL artistBLL)
        {
            _artistBLL = artistBLL;
        }

        [Authorize(Roles = "user, admin")]
        [HttpGet]
        public List<dynamic> Get()
        {
            return _artistBLL.Select();
        }

        [Authorize(Roles = "user, admin")]
        [HttpGet("getArtist")]
        public List<dynamic> GetArtist(int id)
        {
            return _artistBLL.GetArtistByID(id);
        }

        [Authorize(Roles = "admin")]
        [HttpPost]
        public string AddArtist(Artist aArtist)
        {
            bool isNull = aArtist.GetType().GetProperties().All(x => x.GetValue(aArtist) == null);

            if (isNull)
            {
                return "Došlo je do greške.";
            }
            else
            {
                if (_artistBLL.Insert(aArtist) == false)
                {
                    return "Neuspešno dodavanje.";
                }
                return "Uspešno dodavanje!";
            }
        }


        [Authorize(Roles = "admin")]
        [HttpDelete("deleteArtist")]
        public string DeleteArtist(int id)
        {
            if (_artistBLL.DeleteArtistByID(id) == false)
            {
                return "Neuspešno brisanje.";
            }
            return "Uspešno brisanje!";
        }

        [Authorize(Roles = "admin")]
        [HttpGet("updateArtist")]
        public string UpdateArtist(int id, string name, string date)
        {
            if (_artistBLL.UpdateArtist(id, name, date) == false)
            {
                return "Neuspešna izmena podataka.";
            }
            return "Uspešno ste izmenili podatke!";
        }

    }
}
