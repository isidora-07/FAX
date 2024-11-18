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
    [Route("api/song")]
    [ApiController]
    public class SongsController : ControllerBase
    {
        private readonly ISongBLL _songBLL;

        public SongsController(ISongBLL songBLL)
        {
            _songBLL = songBLL;
        }


        [Authorize(Roles = "user, admin")]
        [HttpGet]
        public List<dynamic> Get()
        {
            return _songBLL.Select();   
        }

        [Authorize(Roles = "user, admin")]
        [HttpGet("getSong")]
        public List<dynamic> GetSong(int id)
        {
            return _songBLL.GetSongByID(id);
        }

        [Authorize(Roles = "admin")]
        [HttpPost]
        public string AddSong(Song aSong)
        {
            bool isNull = aSong.GetType().GetProperties().All(x => x.GetValue(aSong) == null);

            if (isNull)
            {
                return "Došlo je do greške.";
            }
            else
            {
                if (_songBLL.Insert(aSong) == false)
                {
                    return "Neuspešno dodavanje.";
                }
                return "Uspešno dodavanje!";
            }
        }


        [Authorize(Roles = "admin")]
        [HttpDelete("deleteSong")]
        public string DeleteSong(int id)
        {
            if (_songBLL.DeleteSongByID(id) == false)
            {
                return "Neuspešno brisanje.";
            }
            return "Uspešno brisanje!";
        }

        [Authorize(Roles = "admin")]
        [HttpGet("updateSong")]
        public string UpdateSong(int id, string name)
        {
            if (_songBLL.UpdateSong(id, name) == false)
            {
                return "Neuspešna izmena podataka.";
            }
            return "Uspešno ste izmenili podatke!";
        }

    }
}
